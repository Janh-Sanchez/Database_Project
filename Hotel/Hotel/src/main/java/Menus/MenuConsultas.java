package Menus;

import Util.Conexion;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuConsultas {
    private final Scanner scanner;

    public MenuConsultas(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=== Consultas del Sistema ===");
            System.out.println("1. Ver reservas y su límite para cancelar (Cancelacion)");
            System.out.println("2. Ver tipo de cliente (VistaTipoCliente)");
            System.out.println("3. Ver estado y disponibilidad de habitaciones (HabitacionConEstado)");
            System.out.println("4. Ver datos precio y noches de la reserva");
            System.out.println("5. Ver consumos registrados (Ver consumos)");
            System.out.println("6. Actualizar y eliminar empleado");
            System.out.println("7. Actualizar y eliminar servicio");
            System.out.println("8. Ver consumo por cliente (ConsumoCliente)");
            System.out.println("0. Volver");
            System.out.println("=============================");
            int op = leerEntero("Seleccione una opción: ");

            switch (op) {
                case 1 -> mostrarVista("Hotel.Cancelacion");
                case 2 -> mostrarVista("Hotel.VistaTipoCliente");
                case 3 -> mostrarVista("Hotel.HabitacionConEstado");
                case 4 -> mostrarConsultaPrecioReserva();
                case 5 -> mostrarVista("Hotel.Consume");
                case 6 -> mostrarVista("Hotel.Empleado");
                case 7 -> mostrarVista("Hotel.Servicio");
                case 8 -> mostrarVista("Hotel.ConsumoCliente");
                case 0 -> { return; }
                default -> System.out.println("\nOpción inválida.");
            }
        }
    }

    private void mostrarVista(String nombreVista) {
        String sql = "SELECT * FROM " + nombreVista;
        try (java.sql.Connection conn = Conexion.getConnection();
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            System.out.println("\n--- Resultados de " + nombreVista + " ---");
            while (rs.next()) {
                for (int i = 1; i <= columnas; i++) {
                    System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("\nError al consultar " + nombreVista + ": " + e.getMessage());
        }
    }

    private void mostrarConsultaPrecioReserva() {
        String sql = """
            SELECT 
                R.DNI AS cliente,
                R.idHabitacion,
                PR.precioReserva,
                R.fechaLlegada,
                R.fechaSalida,
                (fechaSalida::date - fechaLlegada::date) AS cantidadNoches,
                ((fechaSalida::date - fechaLlegada::date) * PR.precioReserva) AS totalEstimado
            FROM Hotel.Reserva R
            NATURAL JOIN Hotel.PrecioReserva PR;
        """;

        try (java.sql.Connection conn = Conexion.getConnection();
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Noches y precio estimado por reserva ---");
            while (rs.next()) {
                System.out.printf("Cliente: %d, Habitación: %d, Precio/Noche: %d, Noches: %d, Total: %d%n",
                        rs.getInt("cliente"),
                        rs.getInt("idHabitacion"),
                        rs.getInt("precioReserva"),
                        rs.getInt("cantidadNoches"),
                        rs.getInt("totalEstimado"));
            }

        } catch (SQLException e) {
            System.out.println("\nError en consulta personalizada: " + e.getMessage());
        }
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}