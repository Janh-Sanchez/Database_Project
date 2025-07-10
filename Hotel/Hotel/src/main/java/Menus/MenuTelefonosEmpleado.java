package Menus;

import DAO.TelefonoEmpleadoDAO;
import Modelos.TelefonoEmpleado;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuTelefonosEmpleado {
    
    private TelefonoEmpleadoDAO telefonoDAO;
    private Scanner scanner;
    
    public MenuTelefonosEmpleado(Scanner scanner, TelefonoEmpleadoDAO telefonoDAO) {
        this.scanner = scanner;
        this.telefonoDAO = telefonoDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE TELÉFONOS DE EMPLEADOS ---");
            System.out.println("1. Agregar teléfono");
            System.out.println("2. Listar teléfonos por empleado");
            System.out.println("3. Actualizar teléfono");
            System.out.println("4. Eliminar teléfono");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> agregarTelefono();
                case 2 -> listarTelefonosPorEmpleado();
                case 3 -> actualizarTelefono();
                case 4 -> eliminarTelefono();
                case 0 -> {}
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
    
    private void agregarTelefono() throws SQLException {
        System.out.println("\n--- AGREGAR TELÉFONO ---");
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        TelefonoEmpleado nuevo = new TelefonoEmpleado(dniEmpleado, telefono);
        if (telefonoDAO.agregarTelefono(nuevo)) {
            System.out.println("Teléfono agregado exitosamente!");
        } else {
            System.out.println("Error al agregar teléfono");
        }
    }
    
    private void listarTelefonosPorEmpleado() throws SQLException {
        System.out.println("\n--- LISTAR TELÉFONOS POR EMPLEADO ---");
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        List<TelefonoEmpleado> telefonos = telefonoDAO.obtenerTelefonosPorDNI(dniEmpleado);
        if (telefonos.isEmpty()) {
            System.out.println("No hay teléfonos registrados para este empleado");
        } else {
            System.out.println("Teléfonos del empleado:");
            telefonos.forEach(t -> System.out.println("- " + t.getTelefono()));
        }
    }
    
    private void actualizarTelefono() throws SQLException {
        System.out.println("\n--- ACTUALIZAR TELÉFONO ---");
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        // Mostrar teléfonos actuales
        List<TelefonoEmpleado> telefonos = telefonoDAO.obtenerTelefonosPorDNI(dniEmpleado);
        if (telefonos.isEmpty()) {
            System.out.println("El empleado no tiene teléfonos registrados");
            return;
        }
        
        System.out.println("Teléfonos actuales:");
        telefonos.forEach(t -> System.out.println("- " + t.getTelefono()));
        
        System.out.print("Teléfono a actualizar: ");
        String telefonoViejo = scanner.nextLine();
        
        System.out.print("Nuevo teléfono: ");
        String telefonoNuevo = scanner.nextLine();
        
        if (telefonoDAO.actualizarTelefono(dniEmpleado, telefonoViejo, telefonoNuevo)) {
            System.out.println("Teléfono actualizado!");
        } else {
            System.out.println("Error al actualizar teléfono");
        }
    }
    
    private void eliminarTelefono() throws SQLException {
        System.out.println("\n--- ELIMINAR TELÉFONO ---");
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        // Mostrar teléfonos actuales
        List<TelefonoEmpleado> telefonos = telefonoDAO.obtenerTelefonosPorDNI(dniEmpleado);
        if (telefonos.isEmpty()) {
            System.out.println("El empleado no tiene teléfonos registrados");
            return;
        }
        
        System.out.println("Teléfonos actuales:");
        telefonos.forEach(t -> System.out.println("- " + t.getTelefono()));
        
        System.out.print("Teléfono a eliminar: ");
        String telefono = scanner.nextLine();
        
        // Confirmar eliminación
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (telefonoDAO.eliminarTelefono(dniEmpleado, telefono)) {
                System.out.println("Teléfono eliminado!");
            } else {
                System.out.println("Error al eliminar teléfono");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
}