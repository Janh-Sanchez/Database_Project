package Menus;

import DAO.ServicioDAO;
import Modelos.Servicio;
import Util.Input;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lynna
 */
public class MenuServicios {
    
    private ServicioDAO servicioDAO;
    private Scanner scanner;
    
    public MenuServicios(Scanner scanner, ServicioDAO servicioDAO) {
        this.scanner = scanner;
        this.servicioDAO = servicioDAO;
    }
    
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE SERVICIOS ---");
            System.out.println("1. Crear servicio");
            System.out.println("2. Listar servicios");
            System.out.println("3. Buscar servicio por ID");
            System.out.println("4. Actualizar servicio");
            System.out.println("5. Eliminar servicio");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            opcion = Input.leerEntero(scanner);
            
            switch (opcion) {
                case 1 -> crearServicio();
                case 2 -> listarServicios();
                case 3 -> buscarServicio();
                case 4 -> actualizarServicio();
                case 5 -> eliminarServicio();
                case 0 -> {}
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
    
    private void crearServicio() throws SQLException {
        System.out.println("\n--- NUEVO SERVICIO ---");
        System.out.print("ID: ");
        int id = Input.leerEntero(scanner);
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Detalle: ");
        String detalle = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = Input.leerDouble(scanner);
        
        Servicio nuevo = new Servicio(id, nombre, detalle, precio);
        if (servicioDAO.agregarServicio(nuevo)) {
            System.out.println("Servicio creado exitosamente!");
        } else {
            System.out.println("Error al crear servicio");
        }
    }

    private void listarServicios() throws SQLException {
        System.out.println("\n--- LISTADO DE SERVICIOS ---");
        List<Servicio> servicios = servicioDAO.obtenerTodosServicios();
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios registrados");
        } else {
            servicios.forEach(System.out::println);
        }
    }

    private void buscarServicio() throws SQLException {
        System.out.println("\n--- BUSCAR SERVICIO ---");
        System.out.print("ID de servicio a buscar: ");
        int id = Input.leerEntero(scanner);
        
        Servicio servicio = servicioDAO.obtenerServicioPorId(id);
        if (servicio != null) {
            System.out.println("Servicio encontrado:");
            System.out.println(servicio);
        } else {
            System.out.println("Servicio no encontrado");
        }
    }

    private void actualizarServicio() throws SQLException {
        System.out.println("\n--- ACTUALIZAR SERVICIO ---");
        System.out.print("ID de servicio a actualizar: ");
        int id = Input.leerEntero(scanner);
        
        Servicio existente = servicioDAO.obtenerServicioPorId(id);
        if (existente == null) {
            System.out.println("Servicio no encontrado");
            return;
        }
        
        System.out.println("Datos actuales:");
        System.out.println(existente);
        
        System.out.print("Nuevo nombre (ENTER para mantener): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) existente.setNombreServicio(nombre);
        
        System.out.print("Nuevo detalle (ENTER para mantener): ");
        String detalle = scanner.nextLine();
        if (!detalle.isEmpty()) existente.setDetalleServicio(detalle);
        
        System.out.print("Nuevo precio (0 para mantener): ");
        double precio = Input.leerDouble(scanner);
        if (precio != 0) existente.setPrecioServicio(precio);
        
        if (servicioDAO.actualizarServicio(existente)) {
            System.out.println("Servicio actualizado!");
        } else {
            System.out.println("Error al actualizar servicio");
        }
    }

    private void eliminarServicio() throws SQLException {
        System.out.println("\n--- ELIMINAR SERVICIO ---");
        System.out.print("ID de servicio a eliminar: ");
        int id = Input.leerEntero(scanner);
        
        // Confirmar eliminación
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            if (servicioDAO.eliminarServicio(id)) {
                System.out.println("Servicio eliminado!");
            } else {
                System.out.println("Error al eliminar servicio");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
}