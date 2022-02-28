package es.uji.ei1027.skillsharing.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class Oferta {
    private int idOferta;
    private int idHabilidad;
    private String dniPropietario;
    private String nombre;
    private String descripcion;
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIniciacion;
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinalizacion;

    public Oferta() {
    }

    public String toString() {
        return "Oferta{" +
                "idOferta='" + idOferta + '\'' +
                ", idHabilidad='" + idHabilidad + '\'' +
                ", dniPropietario='" + dniPropietario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion=" + descripcion +
                ", fechaIniciacion='" + fechaIniciacion + '\'' +
                ", fechaFinalizacion=" + fechaFinalizacion + '\'' +
                '}';
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public int getIdOferta() {
        return this.idOferta;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public int getIdHabilidad() {
        return this.idHabilidad;
    }

    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

    public String getDniPropietario() {
        return this.dniPropietario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setFechaIniciacion(LocalDate fechaIniciacion) {
        this.fechaIniciacion = fechaIniciacion;
    }

    public LocalDate getFechaIniciacion() {
        return this.fechaIniciacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public LocalDate getFechaFinalizacion() {
        return this.fechaFinalizacion;
    }
}
