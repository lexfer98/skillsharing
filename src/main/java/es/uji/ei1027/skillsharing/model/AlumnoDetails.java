package es.uji.ei1027.skillsharing.model;

public class AlumnoDetails {

    String username;
    String contraseña;
    String dni;
    int code;
    String nom;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", password='" + contraseña + '\'' +
                ", dni='" + dni + '\'' +
                ", code=" + code +
                ", nom='" + nom + '\'' +
                '}';
    }



}
