package Hotel;

import Util.Conexion;
import Util.Input;
import DAO.*;
import Menus.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Hotel {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            Conexion.getConnection();
            
            // Inicializar DAOs
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            AreaDAO areaDAO = new AreaDAO();
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            ServicioDAO servicioDAO = new ServicioDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            ReservaDAO reservaDAO = new ReservaDAO();
            TelefonoClienteDAO telefonoClienteDAO = new TelefonoClienteDAO();
            EmailClienteDAO emailClienteDAO = new EmailClienteDAO();
            TelefonoEmpleadoDAO telefonoEmpleadoDAO = new TelefonoEmpleadoDAO();
            EmailEmpleadoDAO emailEmpleadoDAO = new EmailEmpleadoDAO();
            ConsumeDAO consumeDAO = new ConsumeDAO();
            AtiendeDAO atiendeDAO = new AtiendeDAO();

            // Inicializar Menus
            MenuCategorias menuCategorias = new MenuCategorias(scanner, categoriaDAO);
            MenuAreas menuAreas = new MenuAreas(scanner, areaDAO);
            MenuHabitaciones menuHabitaciones = new MenuHabitaciones(scanner, habitacionDAO, categoriaDAO);
            MenuServicios menuServicios = new MenuServicios(scanner, servicioDAO);
            MenuClientes menuClientes = new MenuClientes(scanner, clienteDAO);
            MenuEmpleados menuEmpleados = new MenuEmpleados(scanner, empleadoDAO, areaDAO);
            MenuReservas menuReservas = new MenuReservas(scanner, reservaDAO);
            MenuTelefonosCliente menuTelefonosCliente = new MenuTelefonosCliente(scanner, telefonoClienteDAO);
            MenuEmailsCliente menuEmailsCliente = new MenuEmailsCliente(scanner, emailClienteDAO);
            MenuTelefonosEmpleado menuTelefonosEmpleado = new MenuTelefonosEmpleado(scanner, telefonoEmpleadoDAO);
            MenuEmailsEmpleado menuEmailsEmpleado = new MenuEmailsEmpleado(scanner, emailEmpleadoDAO);
            MenuConsumos menuConsumos = new MenuConsumos(scanner, consumeDAO);
            MenuAtenciones menuAtenciones = new MenuAtenciones(scanner, atiendeDAO);
            MenuConsultas menuConsultas = new MenuConsultas(scanner);

            int opcion;
            do {
                System.out.println("\n=== HOTEL ===");
                System.out.println("1. Gestion de Categorias");
                System.out.println("2. Gestion de Areas");
                System.out.println("3. Gestion de Habitaciones");
                System.out.println("4. Gestion de Servicios");
                System.out.println("5. Gestion de Clientes");
                System.out.println("6. Gestion de Empleados");
                System.out.println("7. Gestion de Reservas");
                System.out.println("8. Gestion de Contactos");
                System.out.println("9. Gestion de Consumos");
                System.out.println("10. Gestion de Atenciones");
                System.out.println("11. Consultas del Sistema");  // Nueva opción
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = Input.leerEntero(scanner);

                switch (opcion) {
                    case 1 -> menuCategorias.mostrarMenu();
                    case 2 -> menuAreas.mostrarMenu();
                    case 3 -> menuHabitaciones.mostrarMenu();
                    case 4 -> menuServicios.mostrarMenu();
                    case 5 -> menuClientes.mostrarMenu();
                    case 6 -> menuEmpleados.mostrarMenu();
                    case 7 -> menuReservas.mostrarMenu();
                    case 8 -> mostrarMenuContactos(menuTelefonosCliente, menuEmailsCliente, 
                                                  menuTelefonosEmpleado, menuEmailsEmpleado);
                    case 9 -> menuConsumos.mostrarMenu();
                    case 10 -> menuAtenciones.mostrarMenu();
                    case 11 -> menuConsultas.mostrarMenu();  
                    case 0 -> System.out.println("Saliendo");
                    default -> System.out.println("Opción no valida");
                }
            } while (opcion != 0);
            
        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion();
            scanner.close();
        }
    }
    
    private static void mostrarMenuContactos(MenuTelefonosCliente menuTelefonosCliente,
                                           MenuEmailsCliente menuEmailsCliente,
                                           MenuTelefonosEmpleado menuTelefonosEmpleado,
                                           MenuEmailsEmpleado menuEmailsEmpleado) throws SQLException {
        int opcion;
        do {
            System.out.println("\n=== GESTION DE CONTACTOS ===");
            System.out.println("1. Telefonos de Clientes");
            System.out.println("2. Emails de Clientes");
            System.out.println("3. Telefonos de Empleados");
            System.out.println("4. Emails de Empleados");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> menuTelefonosCliente.mostrarMenu();
                case 2 -> menuEmailsCliente.mostrarMenu();
                case 3 -> menuTelefonosEmpleado.mostrarMenu();
                case 4 -> menuEmailsEmpleado.mostrarMenu();
                case 0 -> {}
                default -> System.out.println("Opción no valida");
            }
        } while (opcion != 0);
    }
}