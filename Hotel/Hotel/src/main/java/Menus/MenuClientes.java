package Menus;

import DAO.ClienteDAO;
import Modelos.Cliente;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuClientes {
    private final Scanner scanner;
    private final ClienteDAO clienteDAO;

    public MenuClientes(Scanner scanner, ClienteDAO clienteDAO) {
        this.scanner = scanner;
        this.clienteDAO = clienteDAO;
    }

    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente por DNI");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            opcion = Input.leerEntero(scanner);

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> listarClientes();
                case 3 -> buscarCliente();
                case 4 -> actualizarCliente();
                case 5 -> eliminarCliente();
                case 0 -> {}
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private void crearCliente() throws SQLException {
        System.out.println("\n--- NUEVO CLIENTE ---");
        System.out.print("DNI: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Primer nombre: ");
        String primerNombre = scanner.nextLine();
        
        System.out.print("Segundo nombre (ENTER si no tiene): ");
        String segundoNombre = scanner.nextLine();
        
        System.out.print("Primer apellido: ");
        String primerApellido = scanner.nextLine();
        
        System.out.print("Segundo apellido (ENTER si no tiene): ");
        String segundoApellido = scanner.nextLine();
        
        System.out.print("Calle: ");
        String calle = scanner.nextLine();
        
        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();
        
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        
        Cliente nuevo = new Cliente(dni, primerNombre, segundoNombre, primerApellido, 
                                  segundoApellido, calle, carrera, numero);
        if (clienteDAO.agregarCliente(nuevo)) {
            System.out.println("Cliente creado exitosamente!");
        } else {
            System.out.println("Error al crear cliente");
        }
    }

    private void listarClientes() throws SQLException {
        System.out.println("\n--- LISTADO DE CLIENTES ---");
        List<Cliente> clientes = clienteDAO.obtenerTodosClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private void buscarCliente() throws SQLException {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        System.out.print("DNI del cliente a buscar: ");
        int dni = Input.leerEntero(scanner);
        
        Cliente cliente = clienteDAO.obtenerClientePorDNI(dni);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    private void actualizarCliente() throws SQLException {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        System.out.print("DNI del cliente a actualizar: ");
        int dni = Input.leerEntero(scanner);
        
        Cliente existente = clienteDAO.obtenerClientePorDNI(dni);
        if (existente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        
        System.out.println("Datos actuales:");
        System.out.println(existente);
        
        System.out.print("Nuevo primer nombre (ENTER para mantener): ");
        String primerNombre = scanner.nextLine();
        if (!primerNombre.isEmpty()) existente.setPrimerNombre(primerNombre);
        
        System.out.print("Nuevo segundo nombre (ENTER para mantener): ");
        String segundoNombre = scanner.nextLine();
        existente.setSegundoNombre(segundoNombre.isEmpty() ? null : segundoNombre);
        
        System.out.print("Nuevo primer apellido (ENTER para mantener): ");
        String primerApellido = scanner.nextLine();
        if (!primerApellido.isEmpty()) existente.setPrimerApellido(primerApellido);
        
        System.out.print("Nuevo segundo apellido (ENTER para mantener): ");
        String segundoApellido = scanner.nextLine();
        existente.setSegundoApellido(segundoApellido.isEmpty() ? null : segundoApellido);
        
        System.out.print("Nueva calle (ENTER para mantener): ");
        String calle = scanner.nextLine();
        if (!calle.isEmpty()) existente.setCalle(calle);
        
        System.out.print("Nueva carrera (ENTER para mantener): ");
        String carrera = scanner.nextLine();
        if (!carrera.isEmpty()) existente.setCarrera(carrera);
        
        System.out.print("Nuevo número (ENTER para mantener): ");
        String numero = scanner.nextLine();
        if (!numero.isEmpty()) existente.setNumero(numero);
        
        if (clienteDAO.actualizarCliente(existente)) {
            System.out.println("Cliente actualizado!");
        } else {
            System.out.println("Error al actualizar cliente");
        }
    }

    private void eliminarCliente() throws SQLException {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("DNI del cliente a eliminar: ");
        int dni = Input.leerEntero(scanner);
        
        // Confirmar eliminación
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (clienteDAO.eliminarCliente(dni)) {
                System.out.println("Cliente eliminado!");
            } else {
                System.out.println("Error al eliminar cliente");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
}