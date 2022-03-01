package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Habilidad;

import es.uji.ei1027.skillsharing.model.Nivel;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public final class HabilidadRowMapper implements RowMapper<Habilidad> {

    public Habilidad mapRow(ResultSet rs, int rowNum) throws SQLException{
        Habilidad habilidad = new Habilidad();
        habilidad.setId_habilidad(rs.getInt("id_habilidad"));
        habilidad.setNombre(rs.getString("nombre"));
        habilidad.setNivel(Nivel.valueOf(rs.getString("nivel").toLowerCase(Locale.ROOT)));
        habilidad.setDescripcion(rs.getString("descripcion"));
        habilidad.setActiva(rs.getBoolean("activa"));
        return habilidad;
    }
}
