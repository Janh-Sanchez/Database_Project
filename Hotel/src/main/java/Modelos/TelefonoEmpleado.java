package Modelos;

public class TelefonoEmpleado {
    private int dniEmpleado;
    private String telefono;

    public TelefonoEmpleado(int dniEmpleado, String telefono) {
        this.dniEmpleado = dniEmpleado;
        this.telefono = telefono;
    }

    public int getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(int dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "TelefonoEmpleado{" +
               "dniEmpleado=" + dniEmpleado +
               ", telefono='" + telefono + '\'' +
               '}';
    }
}