DROP SCHEMA IF EXISTS Hotel CASCADE;
CREATE EXTENSION IF NOT EXISTS btree_gist;

CREATE SCHEMA Hotel;
SET search_path TO Hotel;

CREATE TABLE Categoria(
    idCategoria NUMERIC(10),
    nombreCategoria VARCHAR(255) NOT NULL, 
    descripcionCategoria TEXT,
    precioCategoria FLOAT NOT NULL
);

CREATE TABLE Area(
    idArea NUMERIC(10),
    nombreArea VARCHAR(255) NOT NULL
);

CREATE TABLE Habitacion(
    idHabitacion NUMERIC(10),
    idCategoria NUMERIC(10)
);

CREATE TABLE Servicio(
    idServicio NUMERIC(10),
    nombreServicio VARCHAR(255) NOT NULL,
    detalleServicio TEXT,
    precioServicio FLOAT NOT NULL
);

CREATE TABLE Cliente(
    DNI NUMERIC(10),
    primerNombre VARCHAR(255) NOT NULL,
    segundoNombre VARCHAR(255),
    primerApellido VARCHAR(255) NOT NULL,
    segundoApellido VARCHAR(255),
    calle VARCHAR(255) NOT NULL,
    carrera VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL
);

CREATE TABLE Empleado(
    DNIEmpleado NUMERIC(10),
    primerNombre VARCHAR(255) NOT NULL,
    segundoNombre VARCHAR(255),
    primerApellido VARCHAR(255) NOT NULL,
    segundoApellido VARCHAR(255),
    calle VARCHAR(255) NOT NULL,
    carrera VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    idArea NUMERIC(10)
);

CREATE TABLE Reserva(
    DNI NUMERIC(10),
    idHabitacion NUMERIC(10),
    fechaLlegada TIMESTAMP,
    fechaSalida TIMESTAMP
);

CREATE TABLE TelefonoCliente(
    DNI NUMERIC(10),
    telefono VARCHAR(255) NOT NULL
);

CREATE TABLE EmailCliente(
    DNI NUMERIC(10),
    email VARCHAR(255) NOT NULL
);

CREATE TABLE TelefonoEmpleado(
    DNIEmpleado NUMERIC(10),
    telefonoEmpleado VARCHAR(255) NOT NULL
);

CREATE TABLE EmailEmpleado(
    DNIEmpleado NUMERIC(10),
    emailEmpleado VARCHAR(255) NOT NULL
);

CREATE TABLE Consume(
    idServicio NUMERIC(10),
    fechaLlegada TIMESTAMP,
    DNI NUMERIC(10),
    idHabitacion NUMERIC(10),
    fechaUso TIMESTAMP
);

CREATE TABLE Atiende(
    DNI NUMERIC(10),
    DNIEmpleado NUMERIC(10),
    idArea NUMERIC(10),
    idHabitacion NUMERIC(10),
    idServicio NUMERIC(10),
    fechaLlegada TIMESTAMP,
    fechaUso TIMESTAMP
);

-- Llaves primarias

ALTER TABLE Categoria
    ADD CONSTRAINT PK_Categoria PRIMARY KEY (idCategoria);

ALTER TABLE Area
    ADD CONSTRAINT PK_Area PRIMARY KEY (idArea);

ALTER TABLE Habitacion
    ADD CONSTRAINT PK_Habitacion PRIMARY KEY (idHabitacion);

ALTER TABLE Servicio
    ADD CONSTRAINT PK_Servicio PRIMARY KEY (idServicio);

ALTER TABLE Cliente
    ADD CONSTRAINT PK_Cliente PRIMARY KEY (DNI);

ALTER TABLE Empleado
    ADD CONSTRAINT PK_Empleado PRIMARY KEY (DNIEmpleado);

ALTER TABLE Reserva
    ADD CONSTRAINT PK_Reserva PRIMARY KEY (DNI, idHabitacion, fechaLlegada);

ALTER TABLE TelefonoCliente
    ADD CONSTRAINT PK_TelefonoCliente PRIMARY KEY (DNI, telefono);

ALTER TABLE EmailCliente
    ADD CONSTRAINT PK_EmailCliente PRIMARY KEY (DNI, email);

ALTER TABLE TelefonoEmpleado
    ADD CONSTRAINT PK_TelefonoEmpleado PRIMARY KEY (DNIEmpleado, telefonoEmpleado);

ALTER TABLE EmailEmpleado
    ADD CONSTRAINT PK_EmailEmpleado PRIMARY KEY (DNIEmpleado, emailEmpleado);

ALTER TABLE Consume
    ADD CONSTRAINT PK_Consume PRIMARY KEY (fechaUso, fechaLlegada, DNI, idHabitacion, idServicio);

ALTER TABLE Atiende
    ADD CONSTRAINT PK_Atiende PRIMARY KEY (DNIEmpleado, DNI, fechaUso, fechaLlegada, idHabitacion, idServicio);    

-- Llaves foráneas

ALTER TABLE Habitacion
ADD CONSTRAINT FK_Habitacion_Categoria 
    FOREIGN KEY (idCategoria) 
    REFERENCES Categoria(idCategoria)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Empleado
ADD CONSTRAINT FK_Empleado_Area
    FOREIGN KEY (idArea) 
    REFERENCES Area(idArea)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Reserva
ADD CONSTRAINT FK_Reserva_Cliente
    FOREIGN KEY (DNI) 
    REFERENCES Cliente(DNI)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Reserva
ADD CONSTRAINT FK_Reserva_Habitacion
    FOREIGN KEY (idHabitacion) 
    REFERENCES Habitacion(idHabitacion)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE TelefonoCliente
ADD CONSTRAINT FK_TelefonoCliente_Cliente
    FOREIGN KEY (DNI) 
    REFERENCES Cliente(DNI)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE EmailCliente
ADD CONSTRAINT FK_EmailCliente_Cliente
    FOREIGN KEY (DNI) 
    REFERENCES Cliente(DNI)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE TelefonoEmpleado
ADD CONSTRAINT FK_TelefonoEmpleado_Empleado
    FOREIGN KEY (DNIEmpleado) 
    REFERENCES Empleado(DNIEmpleado)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE EmailEmpleado
ADD CONSTRAINT FK_EmailEmpleado_Empleado
    FOREIGN KEY (DNIEmpleado) 
    REFERENCES Empleado(DNIEmpleado)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Consume
ADD CONSTRAINT FK_Consume_Servicio 
    FOREIGN KEY (idServicio) 
    REFERENCES Servicio(idServicio)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Consume
ADD CONSTRAINT FK_Consume_Reserva 
    FOREIGN KEY (DNI, idHabitacion, fechaLlegada) 
    REFERENCES Reserva(DNI, idHabitacion, fechaLlegada)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Atiende
ADD CONSTRAINT FK_Atiende_Consume 
    FOREIGN KEY (fechaUso, fechaLlegada, DNI, idHabitacion, idServicio) 
    REFERENCES Consume(fechaUso, fechaLlegada, DNI, idHabitacion, idServicio)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Atiende
ADD CONSTRAINT FK_Atiende_Empleado
    FOREIGN KEY (DNIEmpleado) 
    REFERENCES Empleado(DNIEmpleado)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Atiende
ADD CONSTRAINT FK_Atiende_Area
    FOREIGN KEY (idArea) 
    REFERENCES Area(idArea)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Atiende
ADD CONSTRAINT FK_Atiende_Servicio
    FOREIGN KEY (idServicio) 
    REFERENCES Servicio(idServicio)
    ON UPDATE CASCADE ON DELETE CASCADE;

-- Restricciones CHECK

ALTER TABLE Empleado ADD CONSTRAINT CK_DNIEmpleado CHECK(DNIEmpleado > 0);
ALTER TABLE Empleado ADD CONSTRAINT CK_primerNombreEmp CHECK(primerNombre ~ '^[A-Za-z\s]+$');
ALTER TABLE Empleado ADD CONSTRAINT CK_segundoNombreEmp CHECK(segundoNombre IS NULL OR segundoNombre ~ '^[A-Za-z\s]+$');
ALTER TABLE Empleado ADD CONSTRAINT CK_primerApellidoEmp CHECK(primerApellido ~ '^[A-Za-z\s]+$');
ALTER TABLE Empleado ADD CONSTRAINT CK_segundoApellidoEmp CHECK(segundoApellido IS NULL OR segundoApellido ~ '^[A-Za-z\s]+$');
ALTER TABLE TelefonoEmpleado ADD CONSTRAINT CK_telefonoEmpleado CHECK(telefonoEmpleado ~ '^[0-9]+$');
ALTER TABLE EmailEmpleado ADD CONSTRAINT CK_emailEmpleado CHECK(emailEmpleado ~ '^[^@\s]+@[^@\s]+\.[^@\s]+$');

ALTER TABLE Cliente ADD CONSTRAINT CK_DNICliente CHECK(DNI > 0);
ALTER TABLE Cliente ADD CONSTRAINT CK_primerNombreCliente CHECK(primerNombre ~ '^[A-Za-z\s]+$');
ALTER TABLE Cliente ADD CONSTRAINT CK_segundoNombreCliente CHECK(segundoNombre IS NULL OR segundoNombre ~ '^[A-Za-z\s]+$');
ALTER TABLE Cliente ADD CONSTRAINT CK_primerApellidoCliente CHECK(primerApellido ~ '^[A-Za-z\s]+$');
ALTER TABLE Cliente ADD CONSTRAINT CK_segundoApellidoCliente CHECK(segundoApellido IS NULL OR segundoApellido ~ '^[A-Za-z\s]+$');
ALTER TABLE TelefonoCliente ADD CONSTRAINT CK_telefonoCliente CHECK(telefono ~ '^[0-9]+$');
ALTER TABLE EmailCliente ADD CONSTRAINT CK_emailCliente CHECK(email ~ '^[^@\s]+@[^@\s]+\.[^@\s]+$');

ALTER TABLE Habitacion ADD CONSTRAINT CK_idHabitacion CHECK(idHabitacion > 0);

ALTER TABLE Categoria ADD CONSTRAINT CK_idCategoria CHECK(idCategoria > 0);
ALTER TABLE Categoria ADD CONSTRAINT CK_precioCategoria CHECK(precioCategoria >= 0);

ALTER TABLE Area ADD CONSTRAINT CK_idArea CHECK(idArea > 0);

ALTER TABLE Servicio ADD CONSTRAINT CK_idServicio CHECK(idServicio > 0);
ALTER TABLE Servicio ADD CONSTRAINT CK_precioServicio CHECK(precioServicio >= 0);

ALTER TABLE Reserva ADD CONSTRAINT CK_fechaReserva CHECK(fechaLlegada < fechaSalida);
ALTER TABLE Reserva
    ADD CONSTRAINT reserva_sin_solapamientos
    EXCLUDE USING GIST (
        idHabitacion WITH =,
        tsrange(fechaLlegada, fechaSalida, '[)') WITH &&
    );

ALTER TABLE Consume ADD CONSTRAINT CK_fechaUsoConsume CHECK(fechaUso >= fechaLlegada);

ALTER TABLE Atiende ADD CONSTRAINT CK_fechaUsoAtiende CHECK(fechaUso >= fechaLlegada);

ALTER TABLE TelefonoEmpleado 
ADD CONSTRAINT UQ_TelefonoEmpleado UNIQUE (telefonoEmpleado);

ALTER TABLE EmailEmpleado
ADD CONSTRAINT UQ_EmailEmpleado UNIQUE (emailEmpleado);

ALTER TABLE TelefonoCliente
ADD CONSTRAINT UQ_TelefonoCliente UNIQUE (telefono);

ALTER TABLE EmailCliente
ADD CONSTRAINT UQ_EmailCliente UNIQUE (email);