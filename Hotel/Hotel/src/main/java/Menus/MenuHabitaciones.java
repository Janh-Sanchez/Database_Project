package Menus;

import DAO.HabitacionDAO;
import DAO.CategoriaDAO;
import Modelos.Habitacion;
import Modelos.Categoria;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lynna
 */
public class MenuHabitaciones {
    
    private HabitacionDAO habitacionDAO;
    private CategoriaDAO categoriaDAO;
    private Scanner scanner;
    
    public MenuHabitaciones(Scanner scanner, HabitacionDAO habitacionDAO, CategoriaDAO categoriaDAO) {
        this.scanner = scanner;
        this.habitacionDAO = habitacionDAO;
        this.categoriaDAO = categoriaDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE HABITACIONES ---");
            System.out.println("1. Crear habitacion");
            System.out.println("2. Listar habitaciones");
            System.out.println("3. Buscar habitacion por ID");
            System.out.println("4. Actualizar habitacion");
            System.out.println("5. Eliminar habitacion");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> crearHabitacion();
                case 2 -> listarHabitaciones();
                case 3 -> buscarHabitacion();
                case 4 -> actualizarHabitacion();
                case 5 -> eliminarHabitacion();
                case 0 -> {}
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void crearHabitacion() throws SQLException {
        System.out.println("\n--- NUEVA HABITACION ---");
        System.out.print("Numero de habitacion: ");
        int id = Input.leerEntero(scanner);
        
        // Mostrar categorias disponibles
        System.out.println("\nCategorias disponibles:");
        listarCategorias();
        
        System.out.print("ID de categoria: ");
        int idCategoria = Input.leerEntero(scanner);
        Categoria categoria = categoriaDAO.obtenerCategoriaPorId(idCategoria);
        
        if (categoria != null) {
            Habitacion nueva = new Habitacion(id, categoria);
            if (habitacionDAO.agregarHabitacion(nueva)) {
                System.out.println("Habitacion creada exitosamente!");
            } else {
                System.out.println("Error al crear habitacion");
            }
        } else {
            System.out.println("Categoria no encontrada");
        }
    }
    
    private void listarHabitaciones() throws SQLException {
        System.out.println("\n--- LISTADO DE HABITACIONES ---");
        List<Habitacion> habitaciones = habitacionDAO.obtenerTodasHabitaciones();
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas");
        } else {
            habitaciones.forEach(System.out::println);
        }
    }
    
    private void buscarHabitacion() throws SQLException {
        System.out.println("\n--- BUSCAR HABITACION ---");
        System.out.print("ID de habitacion a buscar: ");
        int id = Input.leerEntero(scanner);
        
        Habitacion habitacion = habitacionDAO.obtenerHabitacionPorId(id);
        if (habitacion != null) {
            System.out.println("Habitacion encontrada:");
            System.out.println(habitacion);
        } else {
            System.out.println("Habitacion no encontrada");
        }
    }
    
    private void actualizarHabitacion() throws SQLException {
        System.out.println("\n--- ACTUALIZAR HABITACION ---");
        System.out.print("ID de habitacion a actualizar: ");
        int id = Input.leerEntero(scanner);
        
        Habitacion existente = habitacionDAO.obtenerHabitacionPorId(id);
        if (existente == null) {
            System.out.println("Habitacion no encontrada");
            return;
        }
        
        System.out.println("Datos actuales:");
        System.out.println(existente);
        
        System.out.print("¿Cambiar categoria? (S/N): ");
        String cambiar = scanner.nextLine();
        
        if (cambiar.equalsIgnoreCase("S")) {
            // Mostrar categorias disponibles
            System.out.println("\nCategorias disponibles:");
            listarCategorias();
            
            System.out.print("Nuevo ID de categoria: ");
            int idCategoria = Input.leerEntero(scanner);
            Categoria categoria = categoriaDAO.obtenerCategoriaPorId(idCategoria);
            
            if (categoria != null) {
                existente.setCategoria(categoria);
                
                if (habitacionDAO.actualizarHabitacion(existente)) {
                    System.out.println("Habitacion actualizada!");
                } else {
                    System.out.println("Error al actualizar habitacion");
                }
            } else {
                System.out.println("Categoria no encontrada");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
    
    private void eliminarHabitacion() throws SQLException {
        System.out.println("\n--- ELIMINAR HABITACION ---");
        System.out.print("ID de habitacion a eliminar: ");
        int id = Input.leerEntero(scanner);
        
        // Confirmar eliminacion
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (habitacionDAO.eliminarHabitacion(id)) {
                System.out.println("Habitacion eliminada!");
            } else {
                System.out.println("Error al eliminar habitacion");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
    
    private void listarCategorias() throws SQLException {
        List<Categoria> categorias = categoriaDAO.obtenerTodasCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias registradas");
        } else {
            categorias.forEach(System.out::println);
        }
    }
}