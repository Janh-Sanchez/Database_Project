package Menus;

import DAO.ConsumeDAO;
import Modelos.Consume;
import Util.Input;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuConsumos {
    private final Scanner scanner;
    private final ConsumeDAO consumeDAO;
    private final DateTimeFormatter dateFormatter;
    
    public MenuConsumos(Scanner scanner, ConsumeDAO consumeDAO) {
        this.scanner = scanner;
        this.consumeDAO = consumeDAO; 
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CONSUMOS ===");
            System.out.println("1. Registrar nuevo consumo");
            System.out.println("2. Ver consumos de una reserva");
            System.out.println("3. Eliminar consumo");
            System.out.println("4. Actualizar servicio de consumo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> registrarConsumo();
                case 2 -> listarConsumosPorReserva();
                case 3 -> eliminarConsumo();
                case 4 -> actualizarServicioConsumo();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }
    
    private void registrarConsumo() {
        System.out.println("\n--- REGISTRAR NUEVO CONSUMO ---");
        
        try {
            System.out.print("ID del servicio: ");
            int idServicio = Input.leerEntero(scanner);
            
            System.out.print("DNI del cliente: ");
            int dni = Input.leerEntero(scanner);
            
            System.out.print("ID de habitación: ");
            int idHabitacion = Input.leerEntero(scanner);
            
            LocalDateTime fechaLlegada = leerFecha("Fecha de llegada de la reserva (yyyy-MM-dd HH:mm): ");
            LocalDateTime fechaUso = leerFecha("Fecha de uso del servicio (yyyy-MM-dd HH:mm): ");
            
            Consume nuevoConsumo = new Consume(idServicio, fechaLlegada, dni, idHabitacion, fechaUso);
            
            if (consumeDAO.agregarConsumo(nuevoConsumo)) {
                System.out.println("\n✅ Consumo registrado exitosamente!");
            } else {
                System.out.println("\n❌ Error al registrar el consumo");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private void listarConsumosPorReserva() {
        System.out.println("\n--- CONSUMOS POR RESERVA ---");
        
        try {
            System.out.print("DNI del cliente: ");
            int dni = Input.leerEntero(scanner);
            
            System.out.print("ID de habitación: ");
            int idHabitacion = Input.leerEntero(scanner);
            
            LocalDateTime fechaLlegada = leerFecha("Fecha de llegada de la reserva (yyyy-MM-dd HH:mm): ");
            
            List<Consume> consumos = consumeDAO.obtenerConsumosPorReserva(dni, idHabitacion, fechaLlegada);
            
            if (consumos.isEmpty()) {
                System.out.println("\nNo se encontraron consumos para esta reserva");
            } else {
                System.out.println("\n--- DETALLE DE CONSUMOS ---");
                System.out.printf("%-10s %-15s %-10s %-15s %-20s %-20s%n", 
                                 "ID", "ID Servicio", "DNI", "Habitación", "Fecha Llegada", "Fecha Uso");
                System.out.println("--------------------------------------------------------------------------------");
                
                for (Consume c : consumos) {
                    System.out.printf("%-10d %-15d %-10d %-15d %-20s %-20s%n",
                                     consumos.indexOf(c)+1,
                                     c.getIdServicio(),
                                     c.getDni(),
                                     c.getIdHabitacion(),
                                     c.getFechaLlegada().format(dateFormatter),
                                     c.getFechaUso().format(dateFormatter));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private void eliminarConsumo() {
        System.out.println("\n--- ELIMINAR CONSUMO ---");
        
        try {
            System.out.print("ID del servicio: ");
            int idServicio = Input.leerEntero(scanner);
            
            System.out.print("DNI del cliente: ");
            int dni = Input.leerEntero(scanner);
            
            System.out.print("ID de habitación: ");
            int idHabitacion = Input.leerEntero(scanner);
            
            LocalDateTime fechaLlegada = leerFecha("Fecha de llegada de la reserva (yyyy-MM-dd HH:mm): ");
            
            System.out.print("\n⚠️ ¿Está seguro que desea eliminar este consumo? (S/N): ");
            String confirmacion = scanner.nextLine().trim();
            
            if (confirmacion.equalsIgnoreCase("S")) {
                if (consumeDAO.eliminarConsumo(idServicio, dni, idHabitacion, fechaLlegada)) {
                    System.out.println("\n✅ Consumo eliminado exitosamente!");
                } else {
                    System.out.println("\n❌ No se pudo eliminar el consumo o no existe");
                }
            } else {
                System.out.println("\nOperación cancelada");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private void actualizarServicioConsumo() {
        System.out.println("\n--- ACTUALIZAR SERVICIO DE CONSUMO ---");
        
        try {
            System.out.print("DNI del cliente: ");
            int dni = Input.leerEntero(scanner);
            
            System.out.print("ID de habitación: ");
            int idHabitacion = Input.leerEntero(scanner);
            
            LocalDateTime fechaLlegada = leerFecha("Fecha de llegada de la reserva (yyyy-MM-dd HH:mm): ");
            
            System.out.print("ID del servicio actual: ");
            int idServicioActual = Input.leerEntero(scanner);
            
            System.out.print("Nuevo ID del servicio: ");
            int nuevoIdServicio = Input.leerEntero(scanner);
            
            System.out.print("\n⚠️ ¿Está seguro que desea actualizar este consumo? (S/N): ");
            String confirmacion = scanner.nextLine().trim();
            
            if (confirmacion.equalsIgnoreCase("S")) {
                if (consumeDAO.actualizarServicioConsumo(nuevoIdServicio, dni, idHabitacion, fechaLlegada, idServicioActual)) {
                    System.out.println("\n✅ Servicio de consumo actualizado exitosamente!");
                } else {
                    System.out.println("\n❌ No se pudo actualizar el servicio o el consumo no existe");
                }
            } else {
                System.out.println("\nOperación cancelada");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private LocalDateTime leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String fechaStr = scanner.nextLine().trim();
                return LocalDateTime.parse(fechaStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.err.println("Formato de fecha inválido. Use yyyy-MM-dd HH:mm (ej: 2023-12-31 14:30)");
            }
        }
    }
}