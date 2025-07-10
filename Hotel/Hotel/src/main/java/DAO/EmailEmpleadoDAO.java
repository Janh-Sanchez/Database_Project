package DAO;

import Modelos.EmailEmpleado;
import Util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailEmpleadoDAO {
    
    public boolean agregarEmail(EmailEmpleado email) {
        String sql = "INSERT INTO Hotel.EmailEmpleado (DNIEmpleado, emailEmpleado) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, email.getDniEmpleado());
            pstmt.setString(2, email.getEmail());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar email: " + e.getMessage());
            return false;
        }
    }
    
    public List<EmailEmpleado> obtenerEmailsPorDNI(int dniEmpleado) {
        List<EmailEmpleado> emails = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.EmailEmpleado WHERE DNIEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dniEmpleado);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                emails.add(new EmailEmpleado(
                    rs.getInt("DNIEmpleado"),
                    rs.getString("emailEmpleado")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener emails: " + e.getMessage());
        }
        return emails;
    }
    
    public boolean eliminarEmail(int dniEmpleado, String email) {
        String sql = "DELETE FROM Hotel.EmailEmpleado WHERE DNIEmpleado = ? AND emailEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dniEmpleado);
            pstmt.setString(2, email);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar email: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarEmail(int dniEmpleado, String emailViejo, String emailNuevo) {
        String sql = "UPDATE Hotel.EmailEmpleado SET emailEmpleado = ? WHERE DNIEmpleado = ? AND emailEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, emailNuevo);
            pstmt.setInt(2, dniEmpleado);
            pstmt.setString(3, emailViejo);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar email: " + e.getMessage());
            return false;
        }
    }
}