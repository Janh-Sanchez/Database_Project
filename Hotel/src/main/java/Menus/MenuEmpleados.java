package Menus;

import DAO.AreaDAO;
import DAO.EmpleadoDAO;
import Modelos.Empleado;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuEmpleados {
    private final Scanner scanner;
    private final EmpleadoDAO empleadoDAO;
    private final AreaDAO areaDAO;
    
    public MenuEmpleados(Scanner scanner, EmpleadoDAO empleadoDAO, AreaDAO areaDAO) {
        this.scanner = scanner;
        this.empleadoDAO = empleadoDAO;
        this.areaDAO = areaDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar nuevo empleado");
            System.out.println("2. Listar todos los empleados");
            System.out.println("3. Buscar empleado por DNI");
            System.out.println("4. Actualizar datos de empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("6. Mostrar áreas disponibles");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> registrarEmpleado();
                case 2 -> listarEmpleados();
                case 3 -> buscarEmpleado();
                case 4 -> actualizarEmpleado();
                case 5 -> eliminarEmpleado();
                case 6 -> mostrarAreasDisponibles();
                case 0 -> {}
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
    
    private void mostrarAreasDisponibles() throws SQLException {
        System.out.println("\n--- ÁREAS DISPONIBLES ---");
        areaDAO.obtenerTodasAreas().forEach(area -> 
            System.out.println("ID: " + area.getIdArea() + " - " + area.getNombreArea())
        );
    }
    
    private void registrarEmpleado() throws SQLException {
        System.out.println("\n--- NUEVO EMPLEADO ---");
        
        // Mostrar áreas disponibles primero
        mostrarAreasDisponibles();
        
        System.out.print("\nDNI: ");
        int dni = Input.leerEntero(scanner);
        
        // Validar si el empleado ya existe
        if (empleadoDAO.obtenerEmpleadoPorDNI(dni) != null) {
            System.out.println("Error: Ya existe un empleado con este DNI");
            return;
        }
        
        System.out.print("Primer nombre: ");
        String primerNombre = scanner.nextLine();
        
        System.out.print("Segundo nombre (opcional, ENTER para omitir): ");
        String segundoNombre = scanner.nextLine();
        
        System.out.print("Primer apellido: ");
        String primerApellido = scanner.nextLine();
        
        System.out.print("Segundo apellido (opcional, ENTER para omitir): ");
        String segundoApellido = scanner.nextLine();
        
        System.out.print("Calle: ");
        String calle = scanner.nextLine();
        
        System.out.print("Carrera: ");
        String carrera = scanner.nextLine();
        
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        
        System.out.print("ID de área: ");
        int idArea = Input.leerEntero(scanner);
        
        // Validar que el área existe
        if (areaDAO.obtenerAreaPorId(idArea) == null) {
            System.out.println("Error: El área especificada no existe");
            return;
        }
        
        Empleado nuevoEmpleado = new Empleado(dni, primerNombre, primerApellido, calle, carrera, numero, cargo, idArea);
        nuevoEmpleado.setSegundoNombre(segundoNombre.isEmpty() ? null : segundoNombre);
        nuevoEmpleado.setSegundoApellido(segundoApellido.isEmpty() ? null : segundoApellido);
        
        if (empleadoDAO.agregarEmpleado(nuevoEmpleado)) {
            System.out.println("Empleado registrado exitosamente!");
        } else {
            System.out.println("Error al registrar empleado");
        }
    }
    
    private void listarEmpleados() throws SQLException {
        System.out.println("\n--- LISTADO DE EMPLEADOS ---");
        List<Empleado> empleados = empleadoDAO.obtenerTodosEmpleados();
        
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.printf("%-10s %-30s %-20s %-10s\n", "DNI", "Nombre Completo", "Cargo", "Área");
            System.out.println("------------------------------------------------------------");
            
            for (Empleado e : empleados) {
                String nombreCompleto = e.getPrimerNombre() + 
                    (e.getSegundoNombre() != null ? " " + e.getSegundoNombre() : "") + 
                    " " + e.getPrimerApellido() + 
                    (e.getSegundoApellido() != null ? " " + e.getSegundoApellido() : "");
                
                String nombreArea = areaDAO.obtenerAreaPorId(e.getIdArea()).getNombreArea();
                
                System.out.printf("%-10d %-30s %-20s %-10s\n", 
                    e.getDNIEmpleado(), nombreCompleto, e.getCargo(), nombreArea);
            }
        }
    }
    private void buscarEmpleado() throws SQLException {
        System.out.println("\n--- BUSCAR EMPLEADO ---");
        System.out.print("DNI del empleado: ");
        int dni = Input.leerEntero(scanner);
        
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorDNI(dni);
        
        if (empleado != null) {
            System.out.println("\nEmpleado encontrado:");
            System.out.println("DNI: " + empleado.getDNIEmpleado());
            System.out.println("Nombre completo: " + empleado.getPrimerNombre() + 
                             (empleado.getSegundoNombre() != null ? " " + empleado.getSegundoNombre() : "") + 
                             " " + empleado.getPrimerApellido() + 
                             (empleado.getSegundoApellido() != null ? " " + empleado.getSegundoApellido() : ""));
            System.out.println("Dirección: " + empleado.getCalle() + " " + empleado.getCarrera() + " #" + empleado.getNumero());
            System.out.println("Cargo: " + empleado.getCargo());
            System.out.println("ID de área: " + empleado.getIdArea());
        } else {
            System.out.println("Empleado no encontrado");
        }
    }
    
    private void actualizarEmpleado() throws SQLException {
        System.out.println("\n--- ACTUALIZAR EMPLEADO ---");
        System.out.print("DNI del empleado a actualizar: ");
        int dni = Input.leerEntero(scanner);
        
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorDNI(dni);
        if (empleado == null) {
            System.out.println("Empleado no encontrado");
            return;
        }
        
        System.out.println("\nDatos actuales:");
        System.out.println("1. Primer nombre: " + empleado.getPrimerNombre());
        System.out.println("2. Segundo nombre: " + (empleado.getSegundoNombre() != null ? empleado.getSegundoNombre() : "No tiene"));
        System.out.println("3. Primer apellido: " + empleado.getPrimerApellido());
        System.out.println("4. Segundo apellido: " + (empleado.getSegundoApellido() != null ? empleado.getSegundoApellido() : "No tiene"));
        System.out.println("5. Calle: " + empleado.getCalle());
        System.out.println("6. Carrera: " + empleado.getCarrera());
        System.out.println("7. Número: " + empleado.getNumero());
        System.out.println("8. Cargo: " + empleado.getCargo());
        System.out.println("9. ID de área: " + empleado.getIdArea());
        
        System.out.println("\nIngrese el número del campo a actualizar (ENTER para terminar):");
        
        boolean continuar = true;
        while (continuar) {
            System.out.print("Campo a modificar (1-9, 0 para terminar): ");
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                continuar = false;
            } else {
                try {
                    int campo = Integer.parseInt(input);
                    
                    switch (campo) {
                        case 1 -> {
                            System.out.print("Nuevo primer nombre: ");
                            empleado.setPrimerNombre(scanner.nextLine());
                        }
                        case 2 -> {
                            System.out.print("Nuevo segundo nombre (ENTER para eliminar): ");
                            String segundoNombre = scanner.nextLine();
                            empleado.setSegundoNombre(segundoNombre.isEmpty() ? null : segundoNombre);
                        }
                        case 3 -> {
                            System.out.print("Nuevo primer apellido: ");
                            empleado.setPrimerApellido(scanner.nextLine());
                        }
                        case 4 -> {
                            System.out.print("Nuevo segundo apellido (ENTER para eliminar): ");
                            String segundoApellido = scanner.nextLine();
                            empleado.setSegundoApellido(segundoApellido.isEmpty() ? null : segundoApellido);
                        }
                        case 5 -> {
                            System.out.print("Nueva calle: ");
                            empleado.setCalle(scanner.nextLine());
                        }
                        case 6 -> {
                            System.out.print("Nueva carrera: ");
                            empleado.setCarrera(scanner.nextLine());
                        }
                        case 7 -> {
                            System.out.print("Nuevo número: ");
                            empleado.setNumero(scanner.nextLine());
                        }
                        case 8 -> {
                            System.out.print("Nuevo cargo: ");
                            empleado.setCargo(scanner.nextLine());
                        }
                        case 9 -> {
                            System.out.print("Nuevo ID de área: ");
                            empleado.setIdArea(Input.leerEntero(scanner));
                        }
                        case 0 -> continuar = false;
                        default -> System.out.println("Opción no válida");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un número válido");
                }
            }
        }
        
        if (empleadoDAO.actualizarEmpleado(empleado)) {
            System.out.println("Empleado actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar empleado");
        }
    }
    
    private void eliminarEmpleado() throws SQLException {
        System.out.println("\n--- ELIMINAR EMPLEADO ---");
        System.out.print("DNI del empleado a eliminar: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("¿Está seguro que desea eliminar este empleado? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (empleadoDAO.eliminarEmpleado(dni)) {
                System.out.println("Empleado eliminado exitosamente!");
            } else {
                System.out.println("Error al eliminar empleado");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
}