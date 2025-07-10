package Modelos;

public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private String detalleServicio;
    private double precioServicio;

    public Servicio(int idServicio, String nombreServicio, String detalleServicio, double precioServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.detalleServicio = detalleServicio;
        this.precioServicio = precioServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }
    
    public String getNombreServicio() {
        return nombreServicio;
    }
    
    public String getDetalleServicio() {
        return detalleServicio;
    }
    
    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
    
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    
    public void setDetalleServicio(String detalleServicio) {
        this.detalleServicio = detalleServicio;
    }
    
    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    @Override
    public String toString() {
        return "Servicio{" +
               "IdServicio = " + idServicio +
               ", NombreServicio='" + nombreServicio + '\'' +
               ", DetalleServicio='" + detalleServicio + '\'' +
               ", PrecioServicio=" + precioServicio +
               '}';
    }
}