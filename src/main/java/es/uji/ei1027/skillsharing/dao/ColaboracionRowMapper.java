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
        colaboracion.setDni_propietario(rs.getString("dni_propietario"));
        colaboracion.setDni_solicitante(rs.getString("dni_solicitante"));
        colaboracion.setFecha_inicio(rs.getObject("fecha_inic", LocalDate.class));
        colaboracion.setFecha_fin(rs.getObject("fecha_fin", LocalDate.class));
        colaboracion.setHoras(rs.getObject("horas", LocalTime.class));
        colaboracion.setPuntuación(rs.getInt("puntuacion"));
        colaboracion.setOpinion(rs.getString("opinion"));
        return colaboracion;
    }
}
