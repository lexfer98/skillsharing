package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Solicitud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SolicitudRowMapper {

    public Solicitud mapRow(ResultSet rs, int rowNum) throws SQLException {
        Solicitud solicitud = new Solicitud();
        solicitud.setId_solicitud(rs.getInt("id_solicitud"));
        solicitud.setId_habilidad(rs.getInt("id_habilidad"));
        solicitud.setDni_solicitud(rs.getString("dni_solicitante"));
        solicitud.setNombre(rs.getString("nombre"));
        solicitud.setDescripcion(rs.getString("descripcion"));
        solicitud.setFecha_inic(rs.getObject("fecha_inic", LocalDate.class));
        solicitud.setFecha_fin(rs.getObject("fecha_fin", LocalDate.class));
        solicitud.setActiva(rs.getBoolean("activa"));


        return solicitud;
    }
}
