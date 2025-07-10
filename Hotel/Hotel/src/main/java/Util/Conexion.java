package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Connection conexion = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/Hotel";
    private static final Properties PROPS = new Properties();

    static {
        PROPS.setProperty("user", "postgres");
        PROPS.setProperty("password", "Takara");
        PROPS.setProperty("ssl", "false");
        PROPS.setProperty("connectTimeout", "5");
    }

    private Conexion() {}

    public static Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(URL, PROPS);
                conexion.setAutoCommit(true);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver PostgreSQL no encontrado", e);
            } catch (SQLException e) {
                throw new SQLException("Error al conectar a la BD", e);
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            } finally {
                conexion = null;
            }
        }
    }
}