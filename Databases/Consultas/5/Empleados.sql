--Actualizar
UPDATE Empleado
SET segundoNombre = 'Tommm',
    segundoApellido = 'Jones'
WHERE DNIEmpleado = '2004';

--Eliminar
DELETE FROM Empleado 
    WHERE DNIEmpleado = '2001';

SELECT * FROM Empleado
