package es.uji.ei1027.skillsharing.model;

import java.time.LocalDate;

public class Alumno {
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String titulacion;
    private Integer curso;
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    private Genero genero;
    private Integer edad;
    private Integer numTel;
    private Integer balanceHoras;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    private boolean isSkp;
    private String contraseña;



    @Override
    public String toString() {
        return "Alumno{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
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

    public Integer getCurso() {
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getNumTel() {
        return numTel;
    }

    public void setNumTel(Integer numTel) {
        this.numTel = numTel;
    }

    public Integer getBalanceHoras() {
        return balanceHoras;
    }

    public void setBalanceHoras(Integer balanceHoras) {
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
}

