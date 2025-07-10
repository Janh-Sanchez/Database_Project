package Menus;

import DAO.TelefonoClienteDAO;
import Modelos.TelefonoCliente;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuTelefonosCliente {
    private final Scanner scanner;
    private final TelefonoClienteDAO telefonoDAO;
    
    public MenuTelefonosCliente(Scanner scanner, TelefonoClienteDAO telefonoDAO) {
        this.scanner = scanner;
        this.telefonoDAO = telefonoDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE TELEFONOS DE CLIENTES ---");
            System.out.println("1. Agregar telefono a cliente");
            System.out.println("2. Ver telefonos de cliente");
            System.out.println("3. Actualizar telefono de cliente");
            System.out.println("4. Eliminar telefono de cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> agregarTelefono();
                case 2 -> listarTelefonos();
                case 3 -> actualizarTelefono();
                case 4 -> eliminarTelefono();
                case 0 -> {}
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void agregarTelefono() throws SQLException {
        System.out.println("\n--- AGREGAR TELEFONO ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Numero de telefono: ");
        String telefono = scanner.nextLine();
        
        TelefonoCliente nuevo = new TelefonoCliente(dni, telefono);
        if (telefonoDAO.agregarTelefono(nuevo)) {
            System.out.println("Telefono agregado exitosamente!");
        } else {
            System.out.println("Error al agregar telefono");
        }
    }
    
    private void listarTelefonos() throws SQLException {
        System.out.println("\n--- TELEFONOS DE CLIENTE ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        List<TelefonoCliente> telefonos = telefonoDAO.obtenerTelefonosPorDNI(dni);
        if (telefonos.isEmpty()) {
            System.out.println("No hay telefonos registrados para este cliente");
        } else {
            telefonos.forEach(System.out::println);
        }
    }
    
    private void actualizarTelefono() throws SQLException {
        System.out.println("\n--- ACTUALIZAR TELEFONO ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Telefono actual: ");
        String telefonoActual = scanner.nextLine();
        
        System.out.print("Nuevo telefono: ");
        String telefonoNuevo = scanner.nextLine();
        
        if (telefonoDAO.actualizarTelefono(dni, telefonoActual, telefonoNuevo)) {
            System.out.println("Telefono actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar telefono");
        }
    }
    
    private void eliminarTelefono() throws SQLException {
        System.out.println("\n--- ELIMINAR TELEFONO ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("Telefono a eliminar: ");
        String telefono = scanner.nextLine();
        
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (telefonoDAO.eliminarTelefono(dni, telefono)) {
                System.out.println("Telefono eliminado exitosamente!");
            } else {
                System.out.println("Error al eliminar telefono");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
}