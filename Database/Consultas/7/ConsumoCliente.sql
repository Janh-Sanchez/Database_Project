DROP VIEW IF EXISTS ConsumoCliente;

CREATE VIEW ConsumoCliente AS
SELECT 
    DNI,
    CONCAT(primerNombre, ' ', primerApellido) AS nombreCliente,
    COALESCE(SUM(precioReserva), 0) AS totalReserva,
    COALESCE(SUM(precioServicio), 0) AS totalConsumos,
    COALESCE(SUM(precioReserva), 0) + COALESCE(SUM(precioServicio), 0) AS totalPagar
FROM 
    Cliente
NATURAL JOIN PrecioReserva
NATURAL LEFT JOIN Consume
NATURAL LEFT JOIN Servicio
GROUP BY DNI, primerNombre, primerApellido
ORDER BY totalPagar DESC;

SELECT * FROM ConsumoCliente