package es.uji.ei1027.skillsharing.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Colaboracion {
    private int id_colaboracion;
    private String dni_propietario;
    private String dni_solicitante;
    private int idOferta;
    private int idSolicitud;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private LocalTime horas;
    private int puntuación;
    private String opinion;

    public String getDni_propietario() {
        return dni_propietario;
    }

    public void setDni_propietario(String dni_propietario) {
        this.dni_propietario = dni_propietario;
    }

    public String getDni_solicitante() {
        return dni_solicitante;
    }

    public void setDni_solicitante(String dni_solicitante) {
        this.dni_solicitante = dni_solicitante;
    }

    public Colaboracion() {


    }


    public int getId_colaboracion() {
        return id_colaboracion;
    }

    public void setId_colaboracion(int id_colaboracion) {
        this.id_colaboracion = id_colaboracion;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public int getIdSolicitud() {
        return this.idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalTime getHoras() {
        return horas;
    }

    public void setHoras(LocalTime horas) {
        this.horas = horas;
    }

    public int getPuntuacion() {
        return puntuación;
    }

    public void setPuntuación(int puntuación) {
        this.puntuación = puntuación;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "Colaboracion{" +
                "id_colaboracion=" + id_colaboracion +
                ", idOferta='" + idOferta + '\'' +
                ", idSolicitud='" + idSolicitud + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", horas=" + horas +
                ", puntuación=" + puntuación +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}






