DROP VIEW IF EXISTS Cancelacion;

CREATE VIEW Cancelacion AS
SELECT
    DNI,
    idHabitacion,
    fechaLlegada,
    fechaSalida,
    (fechaLlegada - INTERVAL '7 days') AS tiempoMaximoCancelacion
FROM Reserva;


DELETE FROM Reserva
WHERE DNI IN (
    SELECT DNI FROM Cancelacion
    WHERE DNI = 1001 AND CURRENT_TIMESTAMP <= tiempoMaximoCancelacion
); -- no se puede eliminar, su tiempo maximo para cancelar ya paso 


DELETE FROM Reserva
WHERE DNI IN (
    SELECT DNI FROM Cancelacion
    WHERE DNI = 1010 AND CURRENT_TIMESTAMP <= tiempoMaximoCancelacion
); -- se elimina


SELECT * FROM Reserva