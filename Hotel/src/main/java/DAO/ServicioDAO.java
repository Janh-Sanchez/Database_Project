package DAO;

import Util.Conexion;
import Modelos.Servicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    public boolean agregarServicio(Servicio servicio) {
        String sql = "INSERT INTO Hotel.Servicio (idServicio, nombreServicio, detalleServicio, precioServicio) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, servicio.getIdServicio());
            pstmt.setString(2, servicio.getNombreServicio());
            pstmt.setString(3, servicio.getDetalleServicio());
            pstmt.setDouble(4, servicio.getPrecioServicio());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar servicio: " + e.getMessage());
            return false;
        }
    }

    public List<Servicio> obtenerTodosServicios() {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Servicio ORDER BY idServicio";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                servicios.add(new Servicio(
                    rs.getInt("idServicio"),
                    rs.getString("nombreServicio"),
                    rs.getString("detalleServicio"),
                    rs.getDouble("precioServicio")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener servicios: " + e.getMessage());
        }
        return servicios;
    }

    public Servicio obtenerServicioPorId(int id) {
        String sql = "SELECT * FROM Hotel.Servicio WHERE idServicio = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Servicio(
                    rs.getInt("idServicio"),
                    rs.getString("nombreServicio"),
                    rs.getString("detalleServicio"),
                    rs.getDouble("precioServicio")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener servicio: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarServicio(Servicio servicio) {
        String sql = "UPDATE Hotel.Servicio SET nombreServicio = ?, detalleServicio = ?, precioServicio = ? WHERE idServicio = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, servicio.getNombreServicio());
            pstmt.setString(2, servicio.getDetalleServicio());
            pstmt.setDouble(3, servicio.getPrecioServicio());
            pstmt.setInt(4, servicio.getIdServicio());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar servicio: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarServicio(int id) {
        String sql = "DELETE FROM Hotel.Servicio WHERE idServicio = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio: " + e.getMessage());
            return false;
        }
    }
}