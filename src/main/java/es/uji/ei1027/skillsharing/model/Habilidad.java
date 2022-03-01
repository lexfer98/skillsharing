package es.uji.ei1027.skillsharing.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Habilidad {

    private int id_habilidad;
    private String nombre;
    private Nivel nivel;
    private String descripcion;
    private Boolean activa;

    public Habilidad(){

    }

    public int getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(int id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "id_habilidad=" + id_habilidad +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", descripcion='" + descripcion + '\'' +
                ", activa=" + activa +
                '}';
    }
}
