package Modelos;

import java.time.LocalDateTime;

public class Consume {
    private int idServicio;
    private LocalDateTime fechaLlegada;
    private int dni;
    private int idHabitacion;
    private LocalDateTime fechaUso;

    public Consume(int idServicio, LocalDateTime fechaLlegada, int dni, int idHabitacion, LocalDateTime fechaUso) {
        this.idServicio = idServicio;
        this.fechaLlegada = fechaLlegada;
        this.dni = dni;
        this.idHabitacion = idHabitacion;
        this.fechaUso = fechaUso;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public LocalDateTime getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(LocalDateTime fechaUso) {
        this.fechaUso = fechaUso;
    }

    @Override
    public String toString() {
        return "Consume{" +
                "idServicio=" + idServicio +
                ", fechaLlegada=" + fechaLlegada +
                ", dni=" + dni +
                ", idHabitacion=" + idHabitacion +
                ", fechaUso=" + fechaUso +
                '}';
    }
}