package DAO;

import Util.Conexion;
import Modelos.TelefonoCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonoClienteDAO {
    
    public boolean agregarTelefono(TelefonoCliente telefono) {
        String sql = "INSERT INTO Hotel.TelefonoCliente (DNI, telefono) VALUES (?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, telefono.getDNI());
            pstmt.setString(2, telefono.getTelefono());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar telefono: " + e.getMessage());
            return false;
        }
    }
    
    public List<TelefonoCliente> obtenerTelefonosPorDNI(int DNI) {
        List<TelefonoCliente> telefonos = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.TelefonoCliente WHERE DNI = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, DNI);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                telefonos.add(new TelefonoCliente(
                    rs.getInt("DNI"),
                    rs.getString("telefono")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener telefonos: " + e.getMessage());
        }
        return telefonos;
    }
    
    public boolean eliminarTelefono(int DNI, String telefono) {
        String sql = "DELETE FROM Hotel.TelefonoCliente WHERE DNI = ? AND telefono = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, DNI);
            pstmt.setString(2, telefono);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar telefono: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarTelefono(int DNI, String telefonoViejo, String telefonoNuevo) {
        String sql = "UPDATE Hotel.TelefonoCliente SET telefono = ? WHERE DNI = ? AND telefono = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, telefonoNuevo);
            pstmt.setInt(2, DNI);
            pstmt.setString(3, telefonoViejo);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar telefono: " + e.getMessage());
            return false;
        }
    }
}