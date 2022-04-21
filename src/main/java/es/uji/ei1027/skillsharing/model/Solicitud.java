package es.uji.ei1027.skillsharing.model;

import java.time.LocalDate;

public class Solicitud {

    private int id_solicitud;
    private int id_habilidad;
    private int id_oferta;
    private String dni_solicitud;
    private String nombre;
    private String descripcion;
    private LocalDate fecha_inic;
    private LocalDate fecha_fin;
    private boolean activa;
    private boolean estado;

    @Override
    public String toString() {
        return "Solicitud{" +
                "id_solicitud=" + id_solicitud +
                ", id_habilidad=" + id_habilidad +
                ", id_oferta=" + id_oferta +
                ", dni_solicitud='" + dni_solicitud + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_inic=" + fecha_inic +
                ", fecha_fin=" + fecha_fin +
                ", activa=" + activa +
                ", estado=" + estado +
                '}';
    }

    public Solicitud(){}

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public int getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(int id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    public String getDni_solicitud() {
        return dni_solicitud;
    }

    public void setDni_solicitud(String dni_solicitud) {
        this.dni_solicitud = dni_solicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_inic() {
        return fecha_inic;
    }

    public void setFecha_inic(LocalDate fecha_inic) {
        this.fecha_inic = fecha_inic;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fi) {
        this.fecha_fin = fecha_fi;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
