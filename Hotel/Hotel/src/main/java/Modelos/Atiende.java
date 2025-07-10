package Modelos;

import java.time.LocalDateTime;

public class Atiende {
    private int dni;
    private int dniEmpleado;
    private int idArea;
    private int idHabitacion;
    private int idServicio;
    private LocalDateTime fechaLlegada;
    private LocalDateTime fechaUso;

    public Atiende(int dni, int dniEmpleado, int idArea, int idHabitacion, 
                  int idServicio, LocalDateTime fechaLlegada, LocalDateTime fechaUso) {
        this.dni = dni;
        this.dniEmpleado = dniEmpleado;
        this.idArea = idArea;
        this.idHabitacion = idHabitacion;
        this.idServicio = idServicio;
        this.fechaLlegada = fechaLlegada;
        this.fechaUso = fechaUso;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(int dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
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

    public LocalDateTime getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(LocalDateTime fechaUso) {
        this.fechaUso = fechaUso;
    }

    @Override
    public String toString() {
        return "Atiende{" +
                "dni=" + dni +
                ", dniEmpleado=" + dniEmpleado +
                ", idArea=" + idArea +
                ", idHabitacion=" + idHabitacion +
                ", idServicio=" + idServicio +
                ", fechaLlegada=" + fechaLlegada +
                ", fechaUso=" + fechaUso +
                '}';
    }
}