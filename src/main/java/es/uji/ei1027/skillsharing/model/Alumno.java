package es.uji.ei1027.skillsharing.model;

import org.jasypt.util.password.BasicPasswordEncryptor;

import java.time.LocalDate;

public class Alumno {
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String titulacion;
    private int curso;
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    private String genero;
    private int edad;
    private int numTel;
    private int balanceHoras;
    private boolean isSkp;
    private String contraseña;





    @Override
    public String toString() {
        return "Alumno{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", titulacion='" + titulacion + '\'' +
                ", curso=" + curso +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", genero=" + genero +
                ", edad=" + edad +
                ", numTel=" + numTel +
                ", balanceHoras='" + balanceHoras + '\'' +
                ", isSkp='" + isSkp + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(Integer numTel) {
        this.numTel = numTel;
    }

    public int getBalanceHoras() {
        return balanceHoras;
    }

    public void setBalanceHoras(int balanceHoras) {
        this.balanceHoras = balanceHoras;
    }

    public boolean isSkp() {
        return isSkp;
    }

    public void setSkp(boolean skp) {
        isSkp = skp;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;

    }
    public void encriptarContraseña(){
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        this.contraseña = passwordEncryptor.encryptPassword(this.contraseña);
    }
}

