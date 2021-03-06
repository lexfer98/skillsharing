package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Habilidad;
import es.uji.ei1027.skillsharing.model.Nivel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ColaboracionRowMapper implements RowMapper<Colaboracion> {

    public Colaboracion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setId_colaboracion(rs.getInt("id_colaboracion"));
        colaboracion.setIdOferta(rs.getInt("id_oferta"));
        colaboracion.setIdSolicitud(rs.getInt("id_solicitud"));
        colaboracion.setFecha_inicio(rs.getObject("fecha_inic", LocalDate.class));
        colaboracion.setFecha_fin(rs.getObject("fecha_fin", LocalDate.class));
        colaboracion.setHoras(rs.getInt("horas"));
        colaboracion.setPuntuación(rs.getInt("puntuacion"));
        colaboracion.setOpinion(rs.getString("opinion"));
        colaboracion.setActivo(rs.getBoolean("activo"));
        colaboracion.setFinalizada(rs.getBoolean("finalizada"));
        return colaboracion;
    }
}
