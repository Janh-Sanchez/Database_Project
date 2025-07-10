package DAO;

import Util.Conexion;
import Modelos.EmailCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailClienteDAO {
    
    public boolean agregarEmail(EmailCliente email) {
        String sql = "INSERT INTO Hotel.EmailCliente (DNI, email) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, email.getDNI());
            pstmt.setString(2, email.getEmail());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar email: " + e.getMessage());
            return false;
        }
    }
    
    public List<EmailCliente> obtenerEmailsPorDNI(int DNI) {
        List<EmailCliente> emails = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.EmailCliente WHERE DNI = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, DNI);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                emails.add(new EmailCliente(
                    rs.getInt("DNI"),
                    rs.getString("email")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener emails: " + e.getMessage());
        }
        return emails;
    }
    
    public boolean eliminarEmail(int DNI, String email) {
        String sql = "DELETE FROM Hotel.EmailCliente WHERE DNI = ? AND email = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, DNI);
            pstmt.setString(2, email);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar email: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarEmail(int DNI, String emailViejo, String emailNuevo) {
        String sql = "UPDATE Hotel.EmailCliente SET email = ? WHERE DNI = ? AND email = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, emailNuevo);
            pstmt.setInt(2, DNI);
            pstmt.setString(3, emailViejo);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar email: " + e.getMessage());
            return false;
        }
    }
}