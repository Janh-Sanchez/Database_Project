package Modelos;

import java.time.LocalDateTime;

public class Reserva {
    private int dni;
    private int idHabitacion;
    private LocalDateTime fechaLlegada;
    private LocalDateTime fechaSalida;

    public Reserva(int dni, int idHabitacion, LocalDateTime fechaLlegada, LocalDateTime fechaSalida) {
        this.dni = dni;
        this.idHabitacion = idHabitacion;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
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

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "dni=" + dni +
                ", idHabitacion=" + idHabitacion +
                ", fechaLlegada=" + fechaLlegada +
                ", fechaSalida=" + fechaSalida +
                '}';
    }
}