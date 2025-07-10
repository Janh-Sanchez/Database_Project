package DAO;

import Util.Conexion;
import Modelos.Categoria;
import Modelos.Habitacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    
    public boolean agregarHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO Hotel.Habitacion (idHabitacion, idCategoria) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, habitacion.getIdHabitacion());
            pstmt.setInt(2, habitacion.getCategoria().getIdCategoria());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar habitacion: " + e.getMessage());
            return false;
        }
    }
    
    public List<Habitacion> obtenerTodasHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT h.idHabitacion, h.idCategoria, c.nombreCategoria, c.descripcionCategoria, c.precioCategoria " +
                     "FROM Hotel.Habitacion h JOIN Hotel.Categoria c ON h.idCategoria = c.idCategoria " +
                     "ORDER BY h.idHabitacion";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("idCategoria"),
                    rs.getString("nombreCategoria"),
                    rs.getString("descripcionCategoria"),
                    rs.getDouble("precioCategoria")
                );
                
                habitaciones.add(new Habitacion(
                    rs.getInt("idHabitacion"),
                    categoria
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener habitaciones: " + e.getMessage());
        }
        return habitaciones;
    }
    
    public Habitacion obtenerHabitacionPorId(int id) {
        String sql = "SELECT h.idHabitacion, h.idCategoria, c.nombreCategoria, c.descripcionCategoria, c.precioCategoria " +
                     "FROM Hotel.Habitacion h JOIN Hotel.Categoria c ON h.idCategoria = c.idCategoria " +
                     "WHERE h.idHabitacion = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("idCategoria"),
                    rs.getString("nombreCategoria"),
                    rs.getString("descripcionCategoria"),
                    rs.getDouble("precioCategoria")
                );
                
                return new Habitacion(
                    rs.getInt("idHabitacion"),
                    categoria
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener habitacion: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizarHabitacion(Habitacion habitacion) {
        String sql = "UPDATE Hotel.Habitacion SET idCategoria = ? WHERE idHabitacion = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, habitacion.getCategoria().getIdCategoria());
            pstmt.setInt(2, habitacion.getIdHabitacion());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar habitacion: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarHabitacion(int id) {
        String sql = "DELETE FROM Hotel.Habitacion WHERE idHabitacion = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar habitacion: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarConexion() {
        String sql = "SELECT COUNT(*) FROM Hotel.Habitacion";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }
}