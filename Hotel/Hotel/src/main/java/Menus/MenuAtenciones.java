package Menus;

import DAO.AtiendeDAO;
import Modelos.Atiende;
import Util.Input;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAtenciones {
    private final Scanner scanner;
    private final AtiendeDAO atiendeDAO;
    private final DateTimeFormatter dateFormatter;
    
    public MenuAtenciones(Scanner scanner, AtiendeDAO atiendeDAO) {
        this.scanner = scanner;
        this.atiendeDAO = atiendeDAO;
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE ATENCIONES ---");
            System.out.println("1. Registrar nueva atencion");
            System.out.println("2. Ver atenciones por empleado");
            System.out.println("3. Ver atenciones por cliente");
            System.out.println("4. Eliminar atencion");
            System.out.println("5. Actualizar atencion"); // <-- Nueva opción
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> registrarAtencion();
                case 2 -> listarAtencionesEmpleado();
                case 3 -> listarAtencionesCliente();
                case 4 -> eliminarAtencion();
                case 5 -> actualizarAtencion(); // <-- Nuevo método
                case 0 -> {}
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void registrarAtencion() throws SQLException {
        System.out.println("\n--- NUEVA ATENCION ---");
        System.out.print("DNI del cliente: ");
        int dniCliente = Input.leerEntero(scanner);
        
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        System.out.print("ID de área: ");
        int idArea = Input.leerEntero(scanner);
        
        System.out.print("ID de habitación: ");
        int idHabitacion = Input.leerEntero(scanner);
        
        System.out.print("ID de servicio: ");
        int idServicio = Input.leerEntero(scanner);
        
        LocalDateTime fechaLlegada = leerFecha("Fecha de llegada (yyyy-MM-dd HH:mm): ");
        LocalDateTime fechaUso = leerFecha("Fecha de uso (yyyy-MM-dd HH:mm): ");
        
        Atiende nuevaAtencion = new Atiende(dniCliente, dniEmpleado, idArea, idHabitacion, idServicio, fechaLlegada, fechaUso);
        
        if (atiendeDAO.agregarAtencion(nuevaAtencion)) {
            System.out.println("Atencion registrada!");
        } else {
            System.out.println("Error al registrar atencion");
        }
    }
    
    private void listarAtencionesEmpleado() throws SQLException {
        System.out.println("\n--- ATENCIONES POR EMPLEADO ---");
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        List<Atiende> atenciones = atiendeDAO.obtenerAtencionesPorEmpleado(dniEmpleado);
        
        if (atenciones.isEmpty()) {
            System.out.println("No se encontraron atenciones para este empleado");
        } else {
            System.out.println("\nAtenciones encontradas:");
            for (Atiende a : atenciones) {
                System.out.println("Cliente DNI: " + a.getDni() + 
                                 " | Area: " + a.getIdArea() +
                                 " | Habitación: " + a.getIdHabitacion() +
                                 " | Servicio: " + a.getIdServicio() +
                                 " | Llegada: " + a.getFechaLlegada().format(dateFormatter) +
                                 " | Uso: " + a.getFechaUso().format(dateFormatter));
            }
        }
    }
    
    private void listarAtencionesCliente() throws SQLException {
        System.out.println("\n--- ATENCIONES POR CLIENTE ---");
        System.out.print("DNI del cliente: ");
        int dniCliente = Input.leerEntero(scanner);
        
        List<Atiende> atenciones = atiendeDAO.obtenerAtencionesPorCliente(dniCliente);
        
        if (atenciones.isEmpty()) {
            System.out.println("No se encontraron atenciones para este cliente");
        } else {
            System.out.println("\nAtenciones encontradas:");
            for (Atiende a : atenciones) {
                System.out.println("Empleado DNI: " + a.getDniEmpleado() + 
                                 " | Área: " + a.getIdArea() +
                                 " | Habitación: " + a.getIdHabitacion() +
                                 " | Servicio: " + a.getIdServicio() +
                                 " | Llegada: " + a.getFechaLlegada().format(dateFormatter) +
                                 " | Uso: " + a.getFechaUso().format(dateFormatter));
            }
        }
    }
    
    private void eliminarAtencion() throws SQLException {
        System.out.println("\n--- ELIMINAR ATENCION ---");
        System.out.print("DNI del cliente: ");
        int dni = Input.leerEntero(scanner);
        
        System.out.print("DNI del empleado: ");
        int dniEmpleado = Input.leerEntero(scanner);
        
        LocalDateTime fechaLlegada = leerFecha("Fecha de llegada (yyyy-MM-dd HH:mm): ");
        
        System.out.print("Está seguro de eliminar esta atenciOn? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (atiendeDAO.eliminarAtencion(dni, dniEmpleado, fechaLlegada)) {
                System.out.println("Atencion eliminada exitosamente!");
            } else {
                System.out.println("Error al eliminar atencion");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
    
    private LocalDateTime leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String fechaStr = scanner.nextLine();
                return LocalDateTime.parse(fechaStr, dateFormatter);
            } catch (Exception e) {
                System.out.println("Formato de fecha invalido. Use yyyy-MM-dd HH:mm");
            }
        }
    }

    private void actualizarAtencion() throws SQLException {
        System.out.println("\n--- ACTUALIZAR ATENCION ---");
        System.out.println("¿Desea buscar la atención por cliente o por empleado?");
        System.out.println("1. Por cliente");
        System.out.println("2. Por empleado");
        System.out.print("Seleccione: ");
        int opcionBusqueda = Input.leerEntero(scanner);

        List<Atiende> atenciones = new ArrayList<>();
        if (opcionBusqueda == 1) {
            System.out.print("DNI del cliente: ");
            int dniCliente = Input.leerEntero(scanner);
            atenciones = atiendeDAO.obtenerAtencionesPorCliente(dniCliente);
        } else if (opcionBusqueda == 2) {
            System.out.print("DNI del empleado: ");
            int dniEmpleado = Input.leerEntero(scanner);
            atenciones = atiendeDAO.obtenerAtencionesPorEmpleado(dniEmpleado);
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        if (atenciones.isEmpty()) {
            System.out.println("No se encontraron atenciones.");
            return;
        }

        System.out.println("\nAtenciones encontradas:");
        int idx = 1;
        for (Atiende a : atenciones) {
            System.out.println(idx + ". Cliente DNI: " + a.getDni() +
                    " | Empleado DNI: " + a.getDniEmpleado() +
                    " | Área: " + a.getIdArea() +
                    " | Habitación: " + a.getIdHabitacion() +
                    " | Servicio: " + a.getIdServicio() +
                    " | Llegada: " + a.getFechaLlegada().format(dateFormatter) +
                    " | Uso: " + a.getFechaUso().format(dateFormatter));
            idx++;
        }

        System.out.print("Seleccione el número de la atención a actualizar: ");
        int seleccion = Input.leerEntero(scanner);
        if (seleccion < 1 || seleccion > atenciones.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Atiende existente = atenciones.get(seleccion - 1);

        System.out.println("Datos actuales:");
        System.out.println("Área: " + existente.getIdArea());
        System.out.println("Habitación: " + existente.getIdHabitacion());
        System.out.println("Servicio: " + existente.getIdServicio());
        System.out.println("Fecha de uso: " + existente.getFechaUso().format(dateFormatter));

        System.out.print("Nuevo ID de área (ENTER para mantener): ");
        String areaStr = scanner.nextLine();
        if (!areaStr.isEmpty()) existente.setIdArea(Integer.parseInt(areaStr));

        System.out.print("Nuevo ID de habitación (ENTER para mantener): ");
        String habStr = scanner.nextLine();
        if (!habStr.isEmpty()) existente.setIdHabitacion(Integer.parseInt(habStr));

        System.out.print("Nuevo ID de servicio (ENTER para mantener): ");
        String servStr = scanner.nextLine();
        if (!servStr.isEmpty()) existente.setIdServicio(Integer.parseInt(servStr));

        System.out.print("Nueva fecha de uso (yyyy-MM-dd HH:mm, ENTER para mantener): ");
        String fechaUsoStr = scanner.nextLine();
        if (!fechaUsoStr.isEmpty()) {
            existente.setFechaUso(LocalDateTime.parse(fechaUsoStr, dateFormatter));
        }

        if (atiendeDAO.actualizarAtencion(existente)) {
            System.out.println("Atención actualizada exitosamente!");
        } else {
            System.out.println("Error al actualizar atención.");
        }
    }
}