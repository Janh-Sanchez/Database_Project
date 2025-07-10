package DAO;

import Util.Conexion;
import Modelos.Area;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {
    
    public boolean agregarArea(Area area) {
        String sql = "INSERT INTO Hotel.Area (idArea, nombreArea) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, area.getIdArea());
            pstmt.setString(2, area.getNombreArea());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar area: " + e.getMessage());
            return false;
        }
    }
    
    public List<Area> obtenerTodasAreas() {
        List<Area> areas = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Area ORDER BY idArea";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                areas.add(new Area(
                    rs.getInt("idArea"),
                    rs.getString("nombreArea")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener areas: " + e.getMessage());
        }
        return areas;
    }
    
    public Area obtenerAreaPorId(int id) {
        String sql = "SELECT * FROM Hotel.Area WHERE idArea = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Area(
                    rs.getInt("idArea"),
                    rs.getString("nombreArea")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener area: " + e.getMessage());
        }
        return null;
    }
    
    public boolean actualizarArea(Area area) {
        String sql = "UPDATE Hotel.Area SET nombreArea = ? WHERE idArea = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, area.getNombreArea());
            pstmt.setInt(2, area.getIdArea());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar area: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarArea(int id) {
        String sql = "DELETE FROM Hotel.Area WHERE idArea = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar area: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarConexion() {
        String sql = "SELECT COUNT(*) FROM Hotel.Area";
        
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