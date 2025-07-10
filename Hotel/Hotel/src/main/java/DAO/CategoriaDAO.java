package DAO;

import Util.Conexion;
import Modelos.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public boolean agregarCategoria(Categoria categoria) {
        String sql = "INSERT INTO Hotel.Categoria (idCategoria, nombreCategoria, descripcionCategoria, precioCategoria) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, categoria.getIdCategoria());
            pstmt.setString(2, categoria.getNombreCategoria());
            pstmt.setString(3, categoria.getDescripcionCategoria());
            pstmt.setDouble(4, categoria.getPrecioCategoria());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al agregar categoria: " + e.getMessage());
            return false;
        }
    }

    public List<Categoria> obtenerTodasCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Hotel.Categoria ORDER BY idCategoria";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("idCategoria"),
                    rs.getString("nombreCategoria"),
                    rs.getString("descripcionCategoria"),
                    rs.getDouble("precioCategoria")
                );
                categorias.add(categoria);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener categorias: " + e.getMessage());
        }
        return categorias;
    }

    public Categoria obtenerCategoriaPorId(int id) {
        String sql = "SELECT * FROM Hotel.Categoria WHERE idCategoria = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Categoria(
                    rs.getInt("idCategoria"),
                    rs.getString("nombreCategoria"),
                    rs.getString("descripcionCategoria"),
                    rs.getDouble("precioCategoria")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener categoria: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE Hotel.Categoria SET nombreCategoria = ?, descripcionCategoria = ?, precioCategoria = ? WHERE idCategoria = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, categoria.getNombreCategoria());
            pstmt.setString(2, categoria.getDescripcionCategoria());
            pstmt.setDouble(3, categoria.getPrecioCategoria());
            pstmt.setInt(4, categoria.getIdCategoria());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCategoria(int id) {
        String sql = "DELETE FROM Hotel.Categoria WHERE idCategoria = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarConexion() {
        String sql = "SELECT COUNT(*) FROM Hotel.Categoria";
        
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }
}