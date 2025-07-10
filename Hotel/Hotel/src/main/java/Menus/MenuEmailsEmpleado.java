package Menus;

import DAO.EmailEmpleadoDAO;
import Modelos.EmailEmpleado;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuEmailsEmpleado {
    private final Scanner scanner;
    private final EmailEmpleadoDAO emailDAO;
    
    public MenuEmailsEmpleado(Scanner scanner, EmailEmpleadoDAO emailDAO) {
        this.scanner = scanner;
        this.emailDAO = emailDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE EMAILS DE EMPLEADOS ---");
            System.out.println("1. Agregar email");
            System.out.println("2. Listar emails por empleado");
            System.out.println("3. Actualizar email");
            System.out.println("4. Eliminar email");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> agregarEmail();
                case 2 -> listarEmailsPorEmpleado();
                case 3 -> actualizarEmail();
                case 4 -> eliminarEmail();
                case 0 -> {}
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
    
    private void agregarEmail() {
        System.out.println("\n--- AGREGAR EMAIL ---");
        System.out.print("DNI del empleado: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        EmailEmpleado nuevoEmail = new EmailEmpleado(dni, email);
        
        if (emailDAO.agregarEmail(nuevoEmail)) {
            System.out.println("¡Email agregado exitosamente!");
        } else {
            System.out.println("Error al agregar el email");
        }
    }
    
    private void listarEmailsPorEmpleado() {
        System.out.println("\n--- LISTAR EMAILS POR EMPLEADO ---");
        System.out.print("DNI del empleado: ");
        int dni = Input.leerEntero(scanner);
        
        List<EmailEmpleado> emails = emailDAO.obtenerEmailsPorDNI(dni);
        
        if (emails.isEmpty()) {
            System.out.println("No se encontraron emails para este empleado");
        } else {
            System.out.println("\n--- EMAILS REGISTRADOS ---");
            emails.forEach(e -> System.out.println("• " + e.getEmail()));
        }
    }
    
    private void actualizarEmail() {
        System.out.println("\n--- ACTUALIZAR EMAIL ---");
        System.out.print("DNI del empleado: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Email actual: ");
        String emailViejo = scanner.nextLine();
        
        System.out.print("Nuevo email: ");
        String emailNuevo = scanner.nextLine();
        
        if (emailDAO.actualizarEmail(dni, emailViejo, emailNuevo)) {
            System.out.println("¡Email actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar el email");
        }
    }
    
    private void eliminarEmail() {
        System.out.println("\n--- ELIMINAR EMAIL ---");
        System.out.print("DNI del empleado: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Email a eliminar: ");
        String email = scanner.nextLine();
        
        System.out.print("¿Está seguro que desea eliminar este email? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (emailDAO.eliminarEmail(dni, email)) {
                System.out.println("¡Email eliminado exitosamente!");
            } else {
                System.out.println("Error al eliminar el email");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
}