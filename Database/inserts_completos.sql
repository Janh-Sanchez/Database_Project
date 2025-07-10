-- Area table
INSERT INTO Area (idArea, nombreArea) VALUES
(1, 'Recepcion'),
(2, 'Limpieza'),
(3, 'Servicios Generales'),
(4, 'Restaurante'),
(5, 'Piscina y Spa'),
(6, 'Seguridad');

-- Categoria table
INSERT INTO Categoria (idCategoria, nombreCategoria, descripcionCategoria, precioCategoria) VALUES
(1, 'Sencilla', 'Habitación con una cama individual', 100000),
(2, 'Doble', 'Habitación con cama doble', 140000),
(3, 'Familiar', 'Habitación amplia con dos camas dobles y una individual', 200000),
(4, 'Suite', 'Habitación de lujo, cama doble y jacuzzi', 250000);

-- Habitacion table
INSERT INTO Habitacion (idHabitacion, idCategoria) VALUES
(101, 1),
(102, 1),
(103, 1),
(201, 2),
(202, 2),
(203, 2),
(301, 3),
(302, 3),
(303, 3),
(401, 4),
(402, 4);

-- Servicio table
INSERT INTO Servicio (idServicio, nombreServicio, detalleServicio, precioServicio) VALUES
(1, 'Desayuno', 'Desayuno tipo buffet', 30000),
(2, 'Spa', 'Acceso al spa por 2 horas', 50000),
(3, 'Lavandería', 'Lavado y planchado de ropa', 15000),
(4, 'Gimnasio', 'Acceso ilimitado al gimnasio', 50000),
(5, 'Parqueadero', 'Uso de parqueadero por 24 horas', 40000),
(6, 'Piscina', 'Acceso a la piscina', 20000);

-- Cliente table
INSERT INTO Cliente (DNI, primerNombre, segundoNombre, primerApellido, segundoApellido, calle, carrera, numero) VALUES
(1001, 'Gabriela', NULL, 'Castro', 'Granados', 'Calle 10', 'Carrera 5', '23-45'),
(1002, 'Laura', NULL, 'Rodriguez', 'Salazar', 'Calle 45', 'Carrera 8', '45-67'),
(1003, 'Santiago', 'Miguel', 'Martinez', 'Herrera', 'Calle 12', 'Carrera 6', '115-3'),
(1004, 'Valentina', 'Isabel', 'Garcia', 'Ramirez', 'Calle 30', 'Carrera 14', '30-55'),
(1005, 'Paola', NULL, 'Vargas', 'Torres', 'Calle 18', 'Carrera 9', '18-33'),
(1006, 'Paula', 'Juliana', 'Moreno', 'Ortega', 'Calle 22', 'Carrera 7', '22-88'),
(1007, 'Juan', NULL, 'Felipe', 'Castillo', 'Calle 5', 'Carrera 3', '19-34'),
(1008, 'Milena', 'Camila', 'Gomez', 'Rivera', 'Calle 16', 'Carrera 12', '16-20'),
(1009, 'Diego', 'Armando', 'Rojas', 'Mendoza', 'Calle 8', 'Carrera 4', '90-100'),
(1010, 'Natalia', NULL, 'Cardenas', 'Pineda', 'Calle 25', 'Carrera 15', '25-90');

-- EmailCliente table
INSERT INTO EmailCliente (DNI, Email) VALUES
(1001, 'gabriela.castro@example.com'),
(1001, 'g.castrog@example.com'),
(1002, 'laura.rodriguez@example.com'),
(1003, 'santiago.martinez@example.com'),
(1004, 'valentina.garcia@example.com'),
(1004, 'v.garcia.r@example.com'),
(1005, 'paola.vargas@example.com'),
(1006, 'paula.moreno@example.com'),
(1007, 'juan.castillo@example.com'),
(1007, 'j.felipe@example.com'),
(1008, 'milena.gomez@example.com'),
(1009, 'diego.rojas@example.com'),
(1010, 'natalia.cardenas@example.com'),
(1010, 'n.cardenas.p@example.com');

-- TelefonoCliente table
INSERT INTO TelefonoCliente (DNI, Telefono) VALUES
(1001, '3101234567'),
(1001, '3129876543'),
(1002, '3157654321'),
(1003, '3115556677'),
(1003, '3109988776'),
(1004, '3171234567'),
(1005, '3191122334'),
(1006, '3105566778'),
(1007, '3189988776'),
(1008, '3161237890'),
(1008, '3139876543'),
(1009, '3152345678'),
(1010, '3118765432'),
(1010, '3107654321'),
(1010, '3107574321');

-- Empleado table
INSERT INTO Empleado (DNIEmpleado, primerNombre, segundoNombre, primerApellido, segundoApellido, calle, carrera, numero, cargo, idArea) VALUES
(2001, 'Ana', 'Lucia', 'Martinez', 'Lopez', 'Calle 12', 'Carrera 3', '23-54', 'Limpieza zonas comunes', 2),
(2002, 'Javier', NULL, 'Gonzalez', 'Torres', 'Calle 20', 'Carrera 10', '29-56', 'Limpieza habitaciones', 2),
(2003, 'Jan', 'Henrik', 'Sanchez', 'Jerez', 'Calle 15', 'Carrera 7', '15-50', 'Recepcionista', 1),
(2004, 'Carlos', NULL, 'Salazar', NULL, 'Calle 8', 'Carrera 5', '67-2', 'Chef', 3),
(2005, 'Mariana', NULL, 'Fernandez', 'Ortega', 'Calle 30', 'Carrera 12', '23-45', 'Chef', 3),
(2006, 'Sergio', NULL, 'Mendi', NULL, 'Calle 25', 'Carrera 15', '25-60', 'Mesero', 3),
(2007, 'Daniela', NULL, 'Pineda', NULL, 'Calle 18', 'Carrera 4', '18-45', 'Salvavidas y primeros auxilios', 4),
(2008, 'Luis', 'Fernando', 'Ortiz', 'Rivera', 'Calle 5', 'Carrera 2', '76-1', 'Vigilante camaras', 5),
(2009, 'Valeria', NULL, 'Jimenez', 'Mendoza', 'Calle 22', 'Carrera 9', '22-99', 'Guardia de seguridad', 5);

-- EmailEmpleado table
INSERT INTO EmailEmpleado (DNIEmpleado, EmailEmpleado) VALUES
(2001, 'ana.martinez@hotelstaff.com'),
(2002, 'javier.gonzalez@hotelstaff.com'),
(2002, 'j.gonzalez.t@hotelstaff.com'),
(2003, 'jan.sanchez@hotelstaff.com'),
(2004, 'carlos.salazar@hotelstaff.com'),
(2005, 'mariana.fernandez@hotelstaff.com'),
(2006, 'sergio.mendi@hotelstaff.com'),
(2007, 'daniela.pineda@hotelstaff.com'),
(2007, 'd.pineda@hotelstaff.com'),
(2008, 'luis.ortiz@hotelstaff.com'),
(2009, 'valeria.jimenez@hotelstaff.com'),
(2009, 'v.jimenez.mendoza@hotelstaff.com');

-- TelefonoEmpleado table
INSERT INTO TelefonoEmpleado (DNIEmpleado, TelefonoEmpleado) VALUES
(2001, '3115551234'),
(2002, '3126667890'),
(2003, '3134445678'),
(2004, '3141239876'),
(2005, '3158765432'),
(2006, '3165556789'),
(2007, '3179998887'),
(2007, '3123334444'),
(2008, '3182223333'),
(2009, '3191112223');

-- Reserva table
INSERT INTO Reserva (DNI, idHabitacion, fechaLlegada, fechaSalida) VALUES
(1001, 101, '2025-07-01 14:00:00', '2025-07-03 12:00:00'),
(1002, 102, '2025-06-05 15:00:00', '2025-07-08 12:00:00'),
(1003, 201, '2025-07-10 15:00:00', '2025-07-14 12:00:00'),
(1004, 202, '2025-07-15 16:00:00', '2025-07-19 12:00:00'),
(1005, 301, '2025-06-20 14:00:00', '2025-07-10 12:00:00'),
(1006, 302, '2025-07-28 13:00:00', '2025-07-31 12:00:00'),
(1007, 401, '2025-08-01 15:00:00', '2025-08-04 12:00:00'),
(1008, 402, '2025-08-05 16:00:00', '2025-08-08 12:00:00'),
(1009, 103, '2025-07-03 14:00:00', '2025-07-13 12:00:00'),
(1010, 203, '2025-08-15 15:00:00', '2025-09-18 12:00:00');

-- Consume table
INSERT INTO Consume (idServicio, fechaLlegada, DNI, idHabitacion, fechaUso) VALUES
(1, '2025-07-01 14:00:00', 1001, 101, '2025-07-02 08:00:00'),
(2, '2025-06-05 15:00:00', 1002, 102, '2025-06-10 17:00:00'),
(3, '2025-07-10 15:00:00', 1003, 201, '2025-07-12 09:00:00'),
(4, '2025-07-15 16:00:00', 1004, 202, '2025-07-17 10:00:00'),
(5, '2025-06-20 14:00:00', 1005, 301, '2025-06-22 14:00:00'),
(6, '2025-07-28 13:00:00', 1006, 302, '2025-07-28 15:00:00'),
(1, '2025-08-01 15:00:00', 1007, 401, '2025-08-02 07:30:00'),
(4, '2025-08-05 16:00:00', 1008, 402, '2025-08-06 18:00:00'),
(5, '2025-07-03 14:00:00', 1009, 103, '2025-07-11 13:30:00'),
(6, '2025-08-15 15:00:00', 1010, 203, '2025-08-16 10:00:00');

-- Atiende table
INSERT INTO Atiende (DNI, DNIEmpleado, idArea, idHabitacion, idServicio, fechaLlegada, fechaUso) VALUES
(1001, 2006, 3, 101, 1, '2025-07-01 14:00:00', '2025-07-02 08:00:00'),
(1002, 2001, 2, 102, 2, '2025-06-05 15:00:00', '2025-06-10 17:00:00'),
(1003, 2002, 2, 201, 3, '2025-07-10 15:00:00', '2025-07-12 09:00:00'),
(1004, 2009, 5, 202, 4, '2025-07-15 16:00:00', '2025-07-17 10:00:00'),
(1005, 2008, 5, 301, 5, '2025-06-20 14:00:00', '2025-06-22 14:00:00'),
(1006, 2007, 4, 302, 6, '2025-07-28 13:00:00', '2025-07-28 15:00:00'),
(1007, 2006, 3, 401, 1, '2025-08-01 15:00:00', '2025-08-02 07:30:00'),
(1008, 2009, 5, 402, 4, '2025-08-05 16:00:00', '2025-08-06 18:00:00'),
(1009, 2008, 5, 103, 5, '2025-07-03 14:00:00', '2025-07-11 13:30:00'),
(1010, 2007, 4, 203, 6, '2025-08-15 15:00:00', '2025-08-16 10:00:00');