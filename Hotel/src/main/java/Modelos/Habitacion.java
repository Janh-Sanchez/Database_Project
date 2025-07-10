package Modelos;

public class Habitacion {
    private int idHabitacion;
    private Categoria categoria;
    private boolean disponible;

    public Habitacion(int idHabitacion, Categoria categoria) {
        this.idHabitacion = idHabitacion;
        this.categoria = categoria;
        this.disponible = true;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "ID =" + idHabitacion +
                ", Categoria = " + categoria.getNombreCategoria() +
                ", Disponible = " + disponible +
                '}';
    }
}