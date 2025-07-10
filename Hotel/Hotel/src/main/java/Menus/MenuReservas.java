package Menus;

import DAO.ReservaDAO;
import Modelos.Reserva;
import Util.Input;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuReservas {
    private final Scanner scanner;
    private final ReservaDAO reservaDAO;
    private final DateTimeFormatter dateFormatter;
    
    public MenuReservas(Scanner scanner, ReservaDAO reservaDAO) {
        this.scanner = scanner;
        this.reservaDAO = reservaDAO;
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE RESERVAS ---");
            System.out.println("1. Crear nueva reserva");
            System.out.println("2. Listar todas las reservas");
            System.out.println("3. Ver reservas de un cliente");
            System.out.println("4. Modificar fecha de salida de reserva");
            System.out.println("5. Cancelar reserva");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> listarReservas();
                case 3 -> listarReservasCliente();
                case 4 -> modificarReserva();
                case 5 -> cancelarReserva();
                case 0 -> {}
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
    
    private void crearReserva() throws SQLException {
        System.out.println("\n--- NUEVA RESERVA ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("ID de habitación: ");
        int idHabitacion = Input.leerEntero(scanner);
        
        LocalDateTime fechaLlegada = leerFecha("Fecha de llegada (yyyy-MM-dd HH:mm): ");
        LocalDateTime fechaSalida = leerFecha("Fecha de salida (yyyy-MM-dd HH:mm): ");
        
        if (fechaSalida.isBefore(fechaLlegada)) {
            System.out.println("Error: La fecha de salida debe ser posterior a la de llegada");
            return;
        }
        
        Reserva nuevaReserva = new Reserva(dni, idHabitacion, fechaLlegada, fechaSalida);
        
        if (reservaDAO.agregarReserva(nuevaReserva)) {
            System.out.println("Reserva creada exitosamente!");
        } else {
            System.out.println("Error al crear reserva");
        }
    }
    
    private void listarReservas() throws SQLException {
        System.out.println("\n--- LISTADO DE RESERVAS ---");
        List<Reserva> reservas = reservaDAO.obtenerTodasReservas();
        
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas");
        } else {
            for (Reserva r : reservas) {
                System.out.println("Cliente DNI: " + r.getDni() + 
                                 " | Habitación: " + r.getIdHabitacion() +
                                 " | Llegada: " + r.getFechaLlegada().format(dateFormatter) +
                                 " | Salida: " + r.getFechaSalida().format(dateFormatter));
            }
        }
    }
    
    private void listarReservasCliente() throws SQLException {
        System.out.println("\n--- RESERVAS POR CLIENTE ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        List<Reserva> reservas = reservaDAO.obtenerReservasPorCliente(dni);
        
        if (reservas.isEmpty()) {
            System.out.println("No se encontraron reservas para este cliente");
        } else {
            System.out.println("\nReservas encontradas:");
            for (Reserva r : reservas) {
                System.out.println("Habitación: " + r.getIdHabitacion() + 
                                 " | Llegada: " + r.getFechaLlegada().format(dateFormatter) +
                                 " | Salida: " + r.getFechaSalida().format(dateFormatter));
            }
        }
    }
    
    private void modificarReserva() throws SQLException {
        System.out.println("\n--- MODIFICAR RESERVA ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        // Primero mostramos las reservas del cliente para que elija cuál modificar
        List<Reserva> reservas = reservaDAO.obtenerReservasPorCliente(dni);
        
        if (reservas.isEmpty()) {
            System.out.println("No se encontraron reservas para este cliente");
            return;
        }
        
        System.out.println("\nReservas del cliente:");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.println((i+1) + ". Habitación: " + r.getIdHabitacion() + 
                             " | Llegada: " + r.getFechaLlegada().format(dateFormatter) +
                             " | Salida: " + r.getFechaSalida().format(dateFormatter));
        }
        
        System.out.print("Seleccione el número de reserva a modificar: ");
        int seleccion = Input.leerEntero(scanner) - 1;
        
        if (seleccion < 0 || seleccion >= reservas.size()) {
            System.out.println("Selección inválida");
            return;
        }
        
        Reserva reserva = reservas.get(seleccion);
        
        System.out.println("\nDatos actuales:");
        System.out.println("Fecha salida actual: " + reserva.getFechaSalida().format(dateFormatter));
        
        LocalDateTime nuevaFechaSalida = leerFecha("Nueva fecha de salida (yyyy-MM-dd HH:mm): ");
        
        if (nuevaFechaSalida.isBefore(reserva.getFechaLlegada())) {
            System.out.println("Error: La fecha de salida debe ser posterior a la de llegada");
            return;
        }
        
        reserva.setFechaSalida(nuevaFechaSalida);
        
        if (reservaDAO.actualizarReserva(reserva)) {
            System.out.println("Reserva modificada exitosamente!");
        } else {
            System.out.println("Error al modificar reserva");
        }
    }
    
    private void cancelarReserva() throws SQLException {
        System.out.println("\n--- CANCELAR RESERVA ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        // Primero mostramos las reservas del cliente para que elija cuál cancelar
        List<Reserva> reservas = reservaDAO.obtenerReservasPorCliente(dni);
        
        if (reservas.isEmpty()) {
            System.out.println("No se encontraron reservas para este cliente");
            return;
        }
        
        System.out.println("\nReservas del cliente:");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.println((i+1) + ". Habitación: " + r.getIdHabitacion() + 
                             " | Llegada: " + r.getFechaLlegada().format(dateFormatter) +
                             " | Salida: " + r.getFechaSalida().format(dateFormatter));
        }
        
        System.out.print("Seleccione el número de reserva a cancelar: ");
        int seleccion = Input.leerEntero(scanner) - 1;
        
        if (seleccion < 0 || seleccion >= reservas.size()) {
            System.out.println("Selección inválida");
            return;
        }
        
        Reserva reserva = reservas.get(seleccion);
        
        System.out.print("¿Está seguro de cancelar esta reserva? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (reservaDAO.eliminarReserva(reserva.getDni(), reserva.getIdHabitacion(), reserva.getFechaLlegada())) {
                System.out.println("Reserva cancelada exitosamente!");
            } else {
                System.out.println("Error al cancelar reserva");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    private LocalDateTime leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String fechaStr = scanner.nextLine();
                return LocalDateTime.parse(fechaStr, dateFormatter);
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Use yyyy-MM-dd HH:mm");
            }
        }
    }
}