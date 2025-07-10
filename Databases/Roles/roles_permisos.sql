-- Revocacion de privilegios y permisos
DO $$
DECLARE
    r RECORD;
    db_name TEXT := current_database(); -- O especifica tu nombre de BD: 'Hotel'
    schema_rec RECORD;
BEGIN
    -- 1. Revocar todos los privilegios de todos los roles no esenciales
    RAISE NOTICE 'Revocando todos los privilegios...';
    
    FOR r IN SELECT rolname FROM pg_roles 
             WHERE rolname NOT IN ('postgres') 
             AND rolname NOT LIKE 'pg_%'
    LOOP
        -- Revocar privilegios en la base de datos
        EXECUTE format('REVOKE ALL PRIVILEGES ON DATABASE %I FROM %I', db_name, r.rolname);
        
        -- Revocar privilegios en cada esquema individualmente
        FOR schema_rec IN SELECT nspname FROM pg_namespace 
                         WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema'
        LOOP
            EXECUTE format('REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA %I FROM %I', 
                          schema_rec.nspname, r.rolname);
            EXECUTE format('REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA %I FROM %I', 
                          schema_rec.nspname, r.rolname);
            EXECUTE format('REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA %I FROM %I', 
                          schema_rec.nspname, r.rolname);
            EXECUTE format('REVOKE ALL PRIVILEGES ON SCHEMA %I FROM %I', 
                          schema_rec.nspname, r.rolname);
        END LOOP;
        
        RAISE NOTICE 'Privilegios revocados para el rol: %', r.rolname;
    END LOOP;

    -- 2. Eliminar todos los roles no esenciales
    RAISE NOTICE 'Eliminando roles...';
    
    FOR r IN SELECT rolname FROM pg_roles 
             WHERE rolname NOT IN ('postgres') 
             AND rolname NOT LIKE 'pg_%'
    LOOP
        BEGIN
            EXECUTE format('DROP ROLE IF EXISTS %I', r.rolname);
            RAISE NOTICE 'Rol eliminado: %', r.rolname;
        EXCEPTION WHEN OTHERS THEN
            RAISE WARNING 'No se pudo eliminar el rol %: %', r.rolname, SQLERRM;
        END;
    END LOOP;
    
    RAISE NOTICE 'Proceso completado. Todos los privilegios revocados y roles no esenciales eliminados.';
END $$;

-- Creamos roles sin grupos
-- Roles principales
CREATE ROLE administracion;
CREATE ROLE recepcion;
CREATE ROLE personal_servicio;

-- Roles de apoyo
CREATE ROLE lectura_completa;
CREATE ROLE escritura_completa;

-- Rol de administracion
CREATE ROLE db_admin LOGIN PASSWORD 'adminHotel';
ALTER ROLE db_admin CREATEDB CREATEROLE;

-- Construimos la Jerarquia
GRANT lectura_completa TO recepcion, personal_servicio, administracion;
GRANT escritura_completa TO recepcion, personal_servicio, administracion;
GRANT lectura_completa, escritura_completa TO db_admin;

-- Asignamos usuarios
CREATE ROLE maria LOGIN PASSWORD 'pwd_maria123';
CREATE ROLE juan LOGIN PASSWORD 'pwd_juan456';
CREATE ROLE luisa LOGIN PASSWORD 'pwd_luisa789';
CREATE ROLE pedro LOGIN PASSWORD 'pwd_pedro012';

GRANT administracion TO maria;
GRANT recepcion TO juan;
GRANT personal_servicio TO luisa;
GRANT recepcion TO pedro;

-- Garantizamos privilegios sobre el esquema
GRANT USAGE ON SCHEMA Hotel TO lectura_completa;
GRANT CREATE ON SCHEMA Hotel TO administracion, db_admin;

-- Privilegios sobre tablas
GRANT SELECT ON ALL TABLES IN SCHEMA Hotel TO lectura_completa;
ALTER DEFAULT PRIVILEGES IN SCHEMA Hotel
 GRANT SELECT ON TABLES TO lectura_completa;

GRANT INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA Hotel TO
escritura_completa;

/*
Recepción:

Gestiona información de clientes y reservas (insertar, actualizar, consultar).

Consulta disponibilidad y estado de habitaciones.

*/

GRANT INSERT, UPDATE ON cliente, reserva TO recepcion;
GRANT SELECT ON habitacionConEstado TO recepcion, personal_servicio;

/*
Personal de Servicio:

Consulta y actualiza el estado de las habitaciones asignadas.

Registra consumos adicionales de los huéspedes.

*/

GRANT INSERT, UPDATE ON habitacionConEstado TO personal_servicio;
GRANT INSERT, UPDATE ON consume TO personal_servicio;

/*

Administración:

Gestiona información de empleados (insertar, actualizar, eliminar, consultar).

Administra la información relacionada con los servicios ofrecidos por el hotel.

Tiene acceso a consultas generales sobre clientes, reservas y consumos para análisis administrativo.*/

GRANT SELECT, INSERT, UPDATE, DELETE ON servicio TO administracion;
GRANT INSERT, UPDATE, DELETE ON servicio TO administracion;
GRANT SELECT ON cliente, reserva, consume TO administracion;

-- Privilegios sobre Columnas 

-- Recepción necesita ver datos básicos pero no sensibles
GRANT SELECT (DNI, primerNombre, primerApellido) 
ON cliente TO recepcion;

GRANT SELECT (DNI, email) 
ON Emailcliente TO recepcion;

GRANT SELECT (DNI, telefono) 
ON TelefonoCliente TO recepcion;

-- Personal de servicio solo necesita ID y nombre
GRANT SELECT (DNI, primerNombre, primerApellido) 
ON cliente TO personal_servicio;

-- Verificacion de privilegios
SELECT grantee, privilege_type
FROM information_schema.role_table_grants
WHERE table_name = 'reserva';

-- Revocación y buenas prácticas
REVOKE UPDATE ON reserva FROM recepcion;

-- 1. Revoke all privileges on existing objects
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA hotel FROM lectura_completa;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA hotel FROM lectura_completa;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA hotel FROM lectura_completa;

-- Revocamos el uso en el esquema
REVOKE USAGE ON SCHEMA hotel FROM lectura_completa;

-- Removemos privilegios por default
ALTER DEFAULT PRIVILEGES IN SCHEMA hotel 
REVOKE SELECT ON TABLES FROM lectura_completa;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA hotel
REVOKE SELECT ON TABLES FROM lectura_completa;

-- Removemos el rol de los demas miembros
REVOKE lectura_completa FROM recepcion, personal_servicio, administracion;

DROP ROLE lectura_completa;
