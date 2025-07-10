package Modelos;

public class EmailCliente {
    private int DNI;
    private String email;

    public EmailCliente(int DNI, String email) {
        this.DNI = DNI;
        this.email = email;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailCliente{" + "DNI=" + DNI + ", email=" + email + '}';
    }
}