package Menus;

import DAO.EmailClienteDAO;
import Modelos.EmailCliente;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuEmailsCliente {
    private final Scanner scanner;
    private final EmailClienteDAO emailDAO;
    
    public MenuEmailsCliente(Scanner scanner, EmailClienteDAO emailDAO) {
        this.scanner = scanner;
        this.emailDAO = emailDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE EMAILS DE CLIENTES ---");
            System.out.println("1. Agregar email a cliente");
            System.out.println("2. Ver emails de cliente");
            System.out.println("3. Actualizar email de cliente");
            System.out.println("4. Eliminar email de cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> agregarEmail();
                case 2 -> listarEmails();
                case 3 -> actualizarEmail();
                case 4 -> eliminarEmail();
                case 0 -> {}
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void agregarEmail() throws SQLException {
        System.out.println("\n--- AGREGAR EMAIL ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        EmailCliente nuevo = new EmailCliente(dni, email);
        if (emailDAO.agregarEmail(nuevo)) {
            System.out.println("Email agregado exitosamente!");
        } else {
            System.out.println("Error al agregar email");
        }
    }
    
    private void listarEmails() throws SQLException {
        System.out.println("\n--- EMAILS DE CLIENTE ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        List<EmailCliente> emails = emailDAO.obtenerEmailsPorDNI(dni);
        if (emails.isEmpty()) {
            System.out.println("No hay emails registrados para este cliente");
        } else {
            emails.forEach(System.out::println);
        }
    }
    
    private void actualizarEmail() throws SQLException {
        System.out.println("\n--- ACTUALIZAR EMAIL ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Email actual: ");
        String emailActual = scanner.nextLine();
        
        System.out.print("Nuevo email: ");
        String emailNuevo = scanner.nextLine();
        
        if (emailDAO.actualizarEmail(dni, emailActual, emailNuevo)) {
            System.out.println("Email actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar email");
        }
    }
    
    private void eliminarEmail() throws SQLException {
        System.out.println("\n--- ELIMINAR EMAIL ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Email a eliminar: ");
        String email = scanner.nextLine();
        
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (emailDAO.eliminarEmail(dni, email)) {
                System.out.println("Email eliminado exitosamente!");
            } else {
                System.out.println("Error al eliminar email");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
}