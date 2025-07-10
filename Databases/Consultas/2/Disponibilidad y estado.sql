DROP VIEW IF EXISTS HabitacionConEstado;

CREATE VIEW HabitacionConEstado AS
SELECT H.idHabitacion,
       C.nombreCategoria,
       C.idCategoria,
       CASE
           WHEN EXISTS (
               SELECT 1
               FROM Reserva R
               WHERE R.idHabitacion = H.idHabitacion
                 AND CURRENT_TIMESTAMP BETWEEN R.fechaLlegada AND R.fechaSalida
           ) THEN FALSE  -- ocupada
           ELSE TRUE     -- disponible
       END AS disponible
FROM Habitacion H
JOIN Categoria C ON H.idCategoria = C.idCategoria;


--SELECT * FROM HabitacionConEstado WHERE disponible = TRUE;

--SELECT * FROM HabitacionConEstado WHERE disponible = False;
