    -- Insertar datos csv

    COPY Categoria FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Categoria.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Area FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Area.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Servicio FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Servicio.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Habitacion FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Habitacion.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Cliente FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Cliente.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Empleado FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Empleado.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Reserva FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Reserva.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY TelefonoCliente FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\TelefonoCliente.csv'
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY EmailCliente FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\EmailCliente.csv'
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY TelefonoEmpleado FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\TelefonoEmpleado.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY EmailEmpleado FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\EmailEmpleado.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Consume FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Consume.csv'
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';

    COPY Atiende FROM 'C:\Users\lynna\Desktop\Bases de datos\Segunda entrega\Datos Hotel CSV\Atiende.csv' 
    DELIMITER ';' CSV HEADER ENCODING 'UTF8';
