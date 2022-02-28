package es.uji.ei1027.skillsharing.dao;


import es.uji.ei1027.skillsharing.model.Oferta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OfertaRowMapper implements RowMapper<Oferta> {
    public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Oferta oferta = new Oferta();
        oferta.setIdOferta(rs.getInt("id_oferta"));
        oferta.setIdHabilidad(rs.getInt("id_habilidad"));
        oferta.setDniPropietario(rs.getString("dni_propietario"));
        oferta.setNombre(rs.getString("nombre"));
        oferta.setDescripcion(rs.getString("descripcion"));
        oferta.setFechaIniciacion(rs.getObject("fecha_inic", LocalDate.class));
        oferta.setFechaFinalizacion(rs.getObject("fecha_fin", LocalDate.class));
        return oferta;
    }
}
