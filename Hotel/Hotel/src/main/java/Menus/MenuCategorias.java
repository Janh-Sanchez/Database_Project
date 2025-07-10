package Menus;

import DAO.CategoriaDAO;
import Modelos.Categoria;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuCategorias {
    private final Scanner scanner;
    private final CategoriaDAO categoriaDAO;
    
    public MenuCategorias(Scanner scanner, CategoriaDAO categoriaDAO) {
        this.scanner = scanner;
        this.categoriaDAO = categoriaDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE CATEGORIAS ---");
            System.out.println("1. Crear categoria");
            System.out.println("2. Listar categorias");
            System.out.println("3. Buscar categoria por ID");
            System.out.println("4. Actualizar categoria");
            System.out.println("5. Eliminar categoria");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> crearCategoria();
                case 2 -> listarCategorias();
                case 3 -> buscarCategoria();
                case 4 -> actualizarCategoria();
                case 5 -> eliminarCategoria();
                case 0 -> {}
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
    
    private void crearCategoria() throws SQLException {
        System.out.println("\n--- NUEVA CATEGORIA ---");
        System.out.print("ID: ");
        int id = Input.leerEntero(scanner);
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = Input.leerDouble(scanner);
        
        Categoria nueva = new Categoria(id, nombre, descripcion, precio);
        if (categoriaDAO.agregarCategoria(nueva)) {
            System.out.println("Categoria creada exitosamente!");
        } else {
            System.out.println("Error al crear categoria");
        }
    }
    
    void listarCategorias() throws SQLException {
        System.out.println("\n--- LISTADO DE CATEGORIAS ---");
        List<Categoria> categorias = categoriaDAO.obtenerTodasCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias registradas");
        } else {
            categorias.forEach(System.out::println);
        }
    }
    
    private void buscarCategoria() throws SQLException {
        System.out.println("\n--- BUSCAR CATEGORIA ---");
        System.out.print("ID de categoria a buscar: ");
        int id = Input.leerEntero(scanner);
        
        Categoria categoria = categoriaDAO.obtenerCategoriaPorId(id);
        if (categoria != null) {
            System.out.println("Categoria encontrada:");
            System.out.println(categoria);
        } else {
            System.out.println("Categoria no encontrada");
        }
    }
    
    private void actualizarCategoria() throws SQLException {
        System.out.println("\n--- ACTUALIZAR CATEGORIA ---");
        System.out.print("ID de categoria a actualizar: ");
        int id = Input.leerEntero(scanner);
        
        Categoria existente = categoriaDAO.obtenerCategoriaPorId(id);
        if (existente == null) {
            System.out.println("Categoria no encontrada");
            return;
        }
        
        System.out.println("Datos actuales:");
        System.out.println(existente);
        
        System.out.print("Nuevo nombre (ENTER para mantener): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) existente.setNombreCategoria(nombre);
        
        System.out.print("Nueva descripcion (ENTER para mantener): ");
        String descripcion = scanner.nextLine();
        if (!descripcion.isEmpty()) existente.setDescripcionCategoria(descripcion);
        
        System.out.print("Nuevo precio (0 para mantener): ");
        double precio = Input.leerDouble(scanner);
        if (precio != 0) existente.setPrecioCategoria(precio);
        
        if (categoriaDAO.actualizarCategoria(existente)) {
            System.out.println("Categoria actualizada!");
        } else {
            System.out.println("Error al actualizar categoria");
        }
    }
    
    private void eliminarCategoria() throws SQLException {
        System.out.println("\n--- ELIMINAR CATEGORIA ---");
        System.out.print("ID de categoria a eliminar: ");
        int id = Input.leerEntero(scanner);
        
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (categoriaDAO.eliminarCategoria(id)) {
                System.out.println("Categoria eliminada!");
            } else {
                System.out.println("Error al eliminar categoria");
            }
        } else {
            System.out.println("Operacion cancelada");
        }
    }
}