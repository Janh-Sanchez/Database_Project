package DAO;

import Util.Conexion;
import Modelos.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO Hotel.Cliente (dni, primerNombre, segundoNombre, primerApellido, segundoApellido, calle, carrera, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, cliente.getDNI());
            pstmt.setString(2, cliente.getPrimerNombre());
            pstmt.setString(3, cliente.getSegundoNombre());
            pstmt.setString(4, cliente.getPrimerApellido());
            pstmt.setString(5, cliente.getSegundoApellido());
            pstmt.setString(6, cliente.getCalle());
            pstmt.setString(7, cliente.getCarrera());
            pstmt.setString(8, cliente.getNumero());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Cliente ORDER BY DNI";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("DNI"),
                    rs.getString("primerNombre"),
                    rs.getString("segundoNombre"),
                    rs.getString("primerApellido"),
                    rs.getString("segundoApellido"),
                    rs.getString("calle"),
                    rs.getString("carrera"),
                    rs.getString("numero")
                ));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    public Cliente obtenerClientePorDNI(int dni) {
        String sql = "SELECT * FROM Hotel.Cliente WHERE DNI = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("DNI"),
                    rs.getString("primerNombre"),
                    rs.getString("segundoNombre"),
                    rs.getString("primerApellido"),
                    rs.getString("segundoApellido"),
                    rs.getString("calle"),
                    rs.getString("carrera"),
                    rs.getString("numero")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Hotel.Cliente SET primerNombre = ?, segundoNombre = ?, primerApellido = ?, segundoApellido = ?, calle = ?, carrera = ?, numero = ? WHERE DNI = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, cliente.getPrimerNombre());
            pstmt.setString(2, cliente.getSegundoNombre());
            pstmt.setString(3, cliente.getPrimerApellido());
            pstmt.setString(4, cliente.getSegundoApellido());
            pstmt.setString(5, cliente.getCalle());
            pstmt.setString(6, cliente.getCarrera());
            pstmt.setString(7, cliente.getNumero());
            pstmt.setInt(8, cliente.getDNI());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(int dni) {
        String sql = "DELETE FROM Hotel.Cliente WHERE DNI = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, dni);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}