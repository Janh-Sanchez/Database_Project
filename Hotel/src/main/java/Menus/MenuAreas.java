package Menus;

import DAO.AreaDAO;
import Modelos.Area;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuAreas {
    private final Scanner scanner;
    private final AreaDAO areaDAO;
    
    public MenuAreas(Scanner scanner, AreaDAO areaDAO) {
        this.scanner = scanner;
        this.areaDAO = areaDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE AREAS ---");
            System.out.println("1. Crear area");
            System.out.println("2. Listar areas");
            System.out.println("3. Buscar area por ID");
            System.out.println("4. Actualizar area");
            System.out.println("5. Eliminar area");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> crearArea();
                case 2 -> listarAreas();
                case 3 -> buscarArea();
                case 4 -> actualizarArea();
                case 5 -> eliminarArea();
                case 0 -> {}
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void crearArea() throws SQLException {
        System.out.println("\n--- NUEVA AREA ---");
        System.out.print("ID: ");
        int id = Input.leerEntero(scanner);
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        Area nueva = new Area(id, nombre);
        if (areaDAO.agregarArea(nueva)) {
            System.out.println("Area creada exitosamente!");
        } else {
            System.out.println("Error al crear area");
        }
    }
    
    private void listarAreas() throws SQLException {
        System.out.println("\n--- LISTADO DE AREAS ---");
        List<Area> areas = areaDAO.obtenerTodasAreas();
        if (areas.isEmpty()) {
            System.out.println("No hay areas registradas");
        } else {
            areas.forEach(System.out::println);
        }
    }
    
    private void buscarArea() throws SQLException {
        System.out.println("\n--- BUSCAR AREA ---");
        System.out.print("ID de area a buscar: ");
        int id = Input.leerEntero(scanner);
        
        Area area = areaDAO.obtenerAreaPorId(id);
        if (area != null) {
            System.out.println("Area encontrada:");
            System.out.println(area);
        } else {
            System.out.println("Area no encontrada");
        }
    }
    
    private void actualizarArea() throws SQLException {
        System.out.println("\n--- ACTUALIZAR AREA ---");
        System.out.print("ID de area a actualizar: ");
        int id = Input.leerEntero(scanner);
        
        Area existente = areaDAO.obtenerAreaPorId(id);
        if (existente == null) {
            System.out.println("Area no encontrada");
            return;
        }
        
        System.out.println("Datos actuales:");
        System.out.println(existente);
        
        System.out.print("Nuevo nombre (ENTER para mantener): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) existente.setNombreArea(nombre);
        
        if (areaDAO.actualizarArea(existente)) {
            System.out.println("Area actualizada!");
        } else {
            System.out.println("Error al actualizar area");
        }
    }
    
    private void eliminarArea() throws SQLException {
        System.out.println("\n--- ELIMINAR AREA ---");
        System.out.print("ID de area a eliminar: ");
        int id = Input.leerEntero(scanner);
        
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (areaDAO.eliminarArea(id)) {
                System.out.println("Area eliminada!");
            } else {
                System.out.println("Error al eliminar area");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
}