package es.uji.ei1027.skillsharing.model;

public class UserDetails {
    String username;
    String password;
    String tipo;
    String dni;
    int code;
    String nom;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String name) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dni='" + dni + '\'' +
                ", code=" + code +
                ", nom='" + nom + '\'' +
                '}';
    }
}
