-- Consultar tipo de cliente
CREATE OR REPLACE VIEW VistaTipoCliente AS
SELECT 
    C.*,
    CASE 
        WHEN COUNT(R.DNI) >= 10 THEN 'VIP'
        WHEN COUNT(R.DNI) >= 5 THEN 'Recurrente'
        WHEN COUNT(R.DNI) >= 1 THEN 'Ocasional'
        ELSE 'Nuevo'
    END AS tipoCliente
FROM Cliente C
LEFT JOIN Reserva R ON C.DNI = R.DNI
GROUP BY C.DNI, C.primerNombre, C.segundoNombre, C.primerApellido, C.segundoApellido, 
         C.calle, C.carrera, C.numero;

SELECT * FROM VistaTipoCliente;
