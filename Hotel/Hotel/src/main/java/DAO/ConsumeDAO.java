package DAO;

import Modelos.Consume;
import Util.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsumeDAO {
    
    public boolean agregarConsumo(Consume consumo) {
        String sql = "INSERT INTO Hotel.Consume (idServicio, fechaLlegada, DNI, idHabitacion, fechaUso) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, consumo.getIdServicio());
            pstmt.setTimestamp(2, Timestamp.valueOf(consumo.getFechaLlegada()));
            pstmt.setInt(3, consumo.getDni());
            pstmt.setInt(4, consumo.getIdHabitacion());
            pstmt.setTimestamp(5, Timestamp.valueOf(consumo.getFechaUso()));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar consumo: " + e.getMessage());
            return false;
        }
    }
    
    public List<Consume> obtenerConsumosPorReserva(int dni, int idHabitacion, LocalDateTime fechaLlegada) {
        List<Consume> consumos = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Consume WHERE DNI = ? AND idHabitacion = ? AND fechaLlegada = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            pstmt.setInt(2, idHabitacion);
            pstmt.setTimestamp(3, Timestamp.valueOf(fechaLlegada));
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                consumos.add(new Consume(
                    rs.getInt("idServicio"),
                    rs.getTimestamp("fechaLlegada").toLocalDateTime(),
                    rs.getInt("DNI"),
                    rs.getInt("idHabitacion"),
                    rs.getTimestamp("fechaUso").toLocalDateTime()
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener consumos: " + e.getMessage());
        }
        return consumos;
    }
    
    public boolean eliminarConsumo(int idServicio, int dni, int idHabitacion, LocalDateTime fechaLlegada) {
        String sql = "DELETE FROM Hotel.Consume WHERE idServicio = ? AND DNI = ? AND idHabitacion = ? AND fechaLlegada = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idServicio);
            pstmt.setInt(2, dni);
            pstmt.setInt(3, idHabitacion);
            pstmt.setTimestamp(4, Timestamp.valueOf(fechaLlegada));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar consumo: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarServicioConsumo(int nuevoIdServicio, int dni, int idHabitacion, LocalDateTime fechaLlegada, int idServicioActual) {
    String sql = "UPDATE Hotel.Consume SET idServicio = ? WHERE DNI = ? AND idHabitacion = ? AND fechaLlegada = ? AND idServicio = ?";
    
    try (Connection conn = Conexion.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, nuevoIdServicio);
        pstmt.setInt(2, dni);
        pstmt.setInt(3, idHabitacion);
        pstmt.setTimestamp(4, Timestamp.valueOf(fechaLlegada));
        pstmt.setInt(5, idServicioActual);
        
        return pstmt.executeUpdate() > 0;
        
    } catch (SQLException e) {
        System.err.println("Error al actualizar servicio del consumo: " + e.getMessage());
        return false;
        }
    }
}