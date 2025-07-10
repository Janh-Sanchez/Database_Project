package Modelos;

public class TelefonoCliente {
    private int DNI;
    private String telefono;

    public TelefonoCliente(int DNI, String telefono) {
        this.DNI = DNI;
        this.telefono = telefono;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "TelefonoCliente{" + "DNI=" + DNI + ", telefono=" + telefono + '}';
    }
}