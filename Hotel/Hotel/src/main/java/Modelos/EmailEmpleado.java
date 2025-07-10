package Modelos;

public class EmailEmpleado {
    private int dniEmpleado;
    private String email;

    public EmailEmpleado(int dniEmpleado, String email) {
        this.dniEmpleado = dniEmpleado;
        this.email = email;
    }

    public int getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(int dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailEmpleado{" +
               "dniEmpleado=" + dniEmpleado +
               ", email='" + email + '\'' +
               '}';
    }
}