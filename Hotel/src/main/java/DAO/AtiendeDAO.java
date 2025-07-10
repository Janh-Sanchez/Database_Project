package DAO;

import Modelos.Atiende;
import Util.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AtiendeDAO {
    
    public boolean agregarAtencion(Atiende atencion) {
        String sql = "INSERT INTO Hotel.Atiende (DNI, DNIEmpleado, idArea, idHabitacion, idServicio, fechaLlegada, fechaUso) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, atencion.getDni());
            pstmt.setInt(2, atencion.getDniEmpleado());
            pstmt.setInt(3, atencion.getIdArea());
            pstmt.setInt(4, atencion.getIdHabitacion());
            pstmt.setInt(5, atencion.getIdServicio());
            pstmt.setTimestamp(6, Timestamp.valueOf(atencion.getFechaLlegada()));
            pstmt.setTimestamp(7, Timestamp.valueOf(atencion.getFechaUso()));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar atención: " + e.getMessage());
            return false;
        }
    }
    
    public List<Atiende> obtenerAtencionesPorEmpleado(int dniEmpleado) {
        List<Atiende> atenciones = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Atiende WHERE DNIEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dniEmpleado);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                atenciones.add(new Atiende(
                    rs.getInt("DNI"),
                    rs.getInt("DNIEmpleado"),
                    rs.getInt("idArea"),
                    rs.getInt("idHabitacion"),
                    rs.getInt("idServicio"),
                    rs.getTimestamp("fechaLlegada").toLocalDateTime(),
                    rs.getTimestamp("fechaUso").toLocalDateTime()
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener atenciones: " + e.getMessage());
        }
        return atenciones;
    }
    
    public boolean eliminarAtencion(int dni, int dniEmpleado, LocalDateTime fechaLlegada) {
        String sql = "DELETE FROM Hotel.Atiende WHERE DNI = ? AND DNIEmpleado = ? AND fechaLlegada = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            pstmt.setInt(2, dniEmpleado);
            pstmt.setTimestamp(3, Timestamp.valueOf(fechaLlegada));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar atención: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarAtencion(Atiende atencion) throws SQLException {
        String sql = "UPDATE Hotel.Atiende SET idArea = ?, idHabitacion = ?, idServicio = ?, fechaUso = ? " +
                    "WHERE DNI = ? AND DNIEmpleado = ? AND fechaLlegada = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, atencion.getIdArea());
            stmt.setInt(2, atencion.getIdHabitacion());
            stmt.setInt(3, atencion.getIdServicio());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(atencion.getFechaUso()));
            stmt.setInt(5, atencion.getDni());
            stmt.setInt(6, atencion.getDniEmpleado());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(atencion.getFechaLlegada()));
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Atiende> obtenerAtencionesPorCliente(int dniCliente) {
        List<Atiende> atenciones = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Atiende WHERE DNI = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dniCliente);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                atenciones.add(new Atiende(
                    rs.getInt("DNI"),
                    rs.getInt("DNIEmpleado"),
                    rs.getInt("idArea"),
                    rs.getInt("idHabitacion"),
                    rs.getInt("idServicio"),
                    rs.getTimestamp("fechaLlegada").toLocalDateTime(),
                    rs.getTimestamp("fechaUso").toLocalDateTime()
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener atenciones: " + e.getMessage());
        }
        return atenciones;
    }
}