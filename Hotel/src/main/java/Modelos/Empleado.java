package Modelos;

public class Empleado {
    private int DNIEmpleado;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String calle;
    private String carrera;
    private String numero;
    private String cargo;
    private int idArea;

    public Empleado(int DNIEmpleado, String primerNombre, String primerApellido, 
                   String calle, String carrera, String numero, String cargo, int idArea) {
        this.DNIEmpleado = DNIEmpleado;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.calle = calle;
        this.carrera = carrera;
        this.numero = numero;
        this.cargo = cargo;
        this.idArea = idArea;
    }

    public int getDNIEmpleado() {
        return DNIEmpleado;
    }

    public void setDNIEmpleado(int DNIEmpleado) {
        this.DNIEmpleado = DNIEmpleado;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    @Override
    public String toString() {
        return "Empleado{" +
               "DNIEmpleado=" + DNIEmpleado +
               ", PrimerNombre='" + primerNombre + '\'' +
               ", SegundoNombre='" + segundoNombre + '\'' +
               ", PrimerApellido='" + primerApellido + '\'' +
               ", SegundoApellido='" + segundoApellido + '\'' +
               ", Calle='" + calle + '\'' +
               ", Carrera='" + carrera + '\'' +
               ", Numero='" + numero + '\'' +
               ", Cargo='" + cargo + '\'' +
               ", IdArea=" + idArea +
               '}';
    }
}