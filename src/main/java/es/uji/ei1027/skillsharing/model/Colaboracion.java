package es.uji.ei1027.skillsharing.model;

import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class Colaboracion {
    private int id_colaboracion;
    private int idOferta;
    private int idSolicitud;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private int horas;
    private int puntuacion;
    private String opinion;
    private boolean activo;
    private boolean finalizada;

    @Override
    public String toString() {
        return "Colaboracion{" +
                "id_colaboracion=" + id_colaboracion +
                ", idOferta=" + idOferta +
                ", idSolicitud=" + idSolicitud +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", horas=" + horas +
                ", puntuación=" + puntuacion +
                ", opinion='" + opinion + '\'' +
                ", activo=" + activo +
                ", finalizada=" + finalizada +
                '}';
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuación(int puntuación) {
        this.puntuacion = puntuación;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isFinalizada(){ return finalizada;}

    public void setFinalizada(boolean finalizada){ this.finalizada = finalizada;}

    public void crearColaboracion(Solicitud solicitud){
        this.idOferta = solicitud.getId_oferta();
        this.idSolicitud = solicitud.getId_solicitud();
        this.fecha_inicio = solicitud.getFecha_inic();
        this.fecha_fin = solicitud.getFecha_fin();
    }
}






