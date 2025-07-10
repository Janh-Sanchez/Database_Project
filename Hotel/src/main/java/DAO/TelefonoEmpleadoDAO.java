package DAO;

import Modelos.TelefonoEmpleado;
import Util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonoEmpleadoDAO {
    
    public boolean agregarTelefono(TelefonoEmpleado telefono) {
        String sql = "INSERT INTO Hotel.TelefonoEmpleado (DNIEmpleado, telefonoEmpleado) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, telefono.getDniEmpleado());
            pstmt.setString(2, telefono.getTelefono());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar teléfono: " + e.getMessage());
            return false;
        }
    }
    
    public List<TelefonoEmpleado> obtenerTelefonosPorDNI(int dniEmpleado) {
        List<TelefonoEmpleado> telefonos = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.TelefonoEmpleado WHERE DNIEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dniEmpleado);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                telefonos.add(new TelefonoEmpleado(
                    rs.getInt("DNIEmpleado"),
                    rs.getString("telefonoEmpleado")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener teléfonos: " + e.getMessage());
        }
        return telefonos;
    }
    
    public boolean eliminarTelefono(int dniEmpleado, String telefono) {
        String sql = "DELETE FROM Hotel.TelefonoEmpleado WHERE DNIEmpleado = ? AND telefonoEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dniEmpleado);
            pstmt.setString(2, telefono);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar teléfono: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarTelefono(int dniEmpleado, String telefonoViejo, String telefonoNuevo) {
        String sql = "UPDATE Hotel.TelefonoEmpleado SET telefonoEmpleado = ? WHERE DNIEmpleado = ? AND telefonoEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, telefonoNuevo);
            pstmt.setInt(2, dniEmpleado);
            pstmt.setString(3, telefonoViejo);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar teléfono: " + e.getMessage());
            return false;
        }
    }
}