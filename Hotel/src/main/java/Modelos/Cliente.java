package Modelos;

public class Cliente {
    private int DNI;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String calle;
    private String carrera;
    private String numero;

    public Cliente(int DNI, String primerNombre, String segundoNombre, String primerApellido, 
                 String segundoApellido, String calle, String carrera, String numero) {
        this.DNI = DNI;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.calle = calle;
        this.carrera = carrera;
        this.numero = numero;
    }

    public int getDNI() {
        return DNI;
    }
    
    public String getPrimerNombre() {
        return primerNombre;
    }
    
    public String getSegundoNombre() {
        return segundoNombre;
    }
    
    public String getPrimerApellido() {
        return primerApellido;
    }
    
    public String getSegundoApellido() {
        return segundoApellido;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public String getCarrera() {
        return carrera;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "DNI = " + DNI +
               ", PrimerNombre = '" + primerNombre + '\'' +
               ", SegundoNombre = '" + segundoNombre + '\'' +
               ", PrimerApellido = '" + primerApellido + '\'' +
               ", SegundoApellido = '" + segundoApellido + '\'' +
               ", Calle='" + calle + '\'' +
               ", Carrera='" + carrera + '\'' +
               ", Numero='" + numero + '\'' +
               '}';
    }

    public String getNombreCompleto() {
        return primerNombre + " " + (segundoNombre != null ? segundoNombre + " " : "") + 
               primerApellido + " " + (segundoApellido != null ? segundoApellido : "");
    }
}