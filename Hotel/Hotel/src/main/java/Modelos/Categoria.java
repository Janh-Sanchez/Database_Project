package Modelos;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private double precioCategoria;

    public Categoria(int idCategoria, String nombreCategoria, String descripcionCategoria, double precioCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.precioCategoria = precioCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public double getPrecioCategoria() {
        return precioCategoria;
    }

    public void setPrecioCategoria(double precioCategoria) {
        this.precioCategoria = precioCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "ID =" + idCategoria +
                ", Nombre ='" + nombreCategoria + '\'' +
                ", Precio=" + precioCategoria +
                '}';
    }
}