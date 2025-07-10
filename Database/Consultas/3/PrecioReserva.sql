DROP VIEW IF EXISTS PrecioReserva;
CREATE VIEW PrecioReserva AS
SELECT DNI, idHabitacion, fechaLlegada, fechaSalida, CantidadDeNoches,
precioCategoria * CantidadDeNoches AS precioReserva
FROM cantidadNoches NATURAL JOIN Categoria NATURAL JOIN Habitacion;

SELECT * FROM PrecioReserva