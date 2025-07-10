package DAO;

import Modelos.Empleado;
import Util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    
    public boolean agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Hotel.Empleado (dniempleado, primernombre, segundonombre, primerapellido, " +
                    "segundoapellido, calle, carrera, numero, cargo, idarea) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, empleado.getDNIEmpleado());
            pstmt.setString(2, empleado.getPrimerNombre());
            pstmt.setString(3, empleado.getSegundoNombre());
            pstmt.setString(4, empleado.getPrimerApellido());
            pstmt.setString(5, empleado.getSegundoApellido());
            pstmt.setString(6, empleado.getCalle());
            pstmt.setString(7, empleado.getCarrera());
            pstmt.setString(8, empleado.getNumero());
            pstmt.setString(9, empleado.getCargo());
            pstmt.setInt(10, empleado.getIdArea());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar empleado: " + e.getMessage());
            return false;
        }
    }

    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Empleado";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Empleado empleado = new Empleado(
                    rs.getInt("dniEmpleado"),
                    rs.getString("primerNombre"),
                    rs.getString("primerApellido"),
                    rs.getString("calle"),
                    rs.getString("carrera"),
                    rs.getString("numero"),
                    rs.getString("cargo"),
                    rs.getInt("idArea")
                );
                empleado.setSegundoNombre(rs.getString("segundoNombre"));
                empleado.setSegundoApellido(rs.getString("segundoApellido"));
                
                empleados.add(empleado);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener empleados: " + e.getMessage());
        }
        return empleados;
    }

    public Empleado obtenerEmpleadoPorDNI(int dni) {
        String sql = "SELECT * FROM Hotel.Empleado WHERE DNIEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Empleado empleado = new Empleado(
                    rs.getInt("dniEmpleado"),
                    rs.getString("primerNombre"),
                    rs.getString("primerApellido"),
                    rs.getString("calle"),
                    rs.getString("carrera"),
                    rs.getString("numero"),
                    rs.getString("cargo"),
                    rs.getInt("idArea")
                );
                empleado.setSegundoNombre(rs.getString("segundoNombre"));
                empleado.setSegundoApellido(rs.getString("segundoApellido"));
                
                return empleado;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener empleado: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE Hotel.Empleado SET primerNombre = ?, segundoNombre = ?, primerApellido = ?, " +
                    "segundoApellido = ?, calle = ?, carrera = ?, numero = ?, cargo = ?, idArea = ? " +
                    "WHERE DNIEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, empleado.getPrimerNombre());
            pstmt.setString(2, empleado.getSegundoNombre());
            pstmt.setString(3, empleado.getPrimerApellido());
            pstmt.setString(4, empleado.getSegundoApellido());
            pstmt.setString(5, empleado.getCalle());
            pstmt.setString(6, empleado.getCarrera());
            pstmt.setString(7, empleado.getNumero());
            pstmt.setString(8, empleado.getCargo());
            pstmt.setInt(9, empleado.getIdArea());
            pstmt.setInt(10, empleado.getDNIEmpleado());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarEmpleado(int dni) {
        String sql = "DELETE FROM Hotel.Empleado WHERE DNIEmpleado = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }
}