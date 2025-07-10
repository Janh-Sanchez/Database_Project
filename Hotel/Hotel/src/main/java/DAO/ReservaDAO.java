package DAO;

import Modelos.Reserva;
import Util.Conexion;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    
    public boolean agregarReserva(Reserva reserva) {
        String sql = "INSERT INTO Hotel.Reserva (DNI, idHabitacion, fechaLlegada, fechaSalida) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, reserva.getDni());
            pstmt.setInt(2, reserva.getIdHabitacion());
            pstmt.setTimestamp(3, Timestamp.valueOf(reserva.getFechaLlegada()));
            pstmt.setTimestamp(4, Timestamp.valueOf(reserva.getFechaSalida()));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar reserva: " + e.getMessage());
            return false;
        }
    }
    
    public List<Reserva> obtenerTodasReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Reserva ORDER BY fechaLlegada";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                reservas.add(new Reserva(
                    rs.getInt("DNI"),
                    rs.getInt("idHabitacion"),
                    rs.getTimestamp("fechaLlegada").toLocalDateTime(),
                    rs.getTimestamp("fechaSalida").toLocalDateTime()
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener reservas: " + e.getMessage());
        }
        return reservas;
    }
    
    public List<Reserva> obtenerReservasPorCliente(int dni) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Reserva WHERE DNI = ? ORDER BY fechaLlegada";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                reservas.add(new Reserva(
                    rs.getInt("DNI"),
                    rs.getInt("idHabitacion"),
                    rs.getTimestamp("fechaLlegada").toLocalDateTime(),
                    rs.getTimestamp("fechaSalida").toLocalDateTime()
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener reservas del cliente: " + e.getMessage());
        }
        return reservas;
    }
    
    public boolean actualizarReserva(Reserva reserva) {
        String sql = "UPDATE Hotel.Reserva SET fechaSalida = ? WHERE DNI = ? AND idHabitacion = ? AND fechaLlegada = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setTimestamp(1, Timestamp.valueOf(reserva.getFechaSalida()));
            pstmt.setInt(2, reserva.getDni());
            pstmt.setInt(3, reserva.getIdHabitacion());
            pstmt.setTimestamp(4, Timestamp.valueOf(reserva.getFechaLlegada()));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarReserva(int dni, int idHabitacion, LocalDateTime fechaLlegada) {
        String sql = "DELETE FROM Hotel.Reserva WHERE DNI = ? AND idHabitacion = ? AND fechaLlegada = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            pstmt.setInt(2, idHabitacion);
            pstmt.setTimestamp(3, Timestamp.valueOf(fechaLlegada));
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }
}