package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Habilidad;
import es.uji.ei1027.skillsharing.model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfertaDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //Añade una oferta a la base de datos
    public void addOferta(Oferta oferta) {
        jdbcTemplate.update("INSERT INTO oferta VALUES(?, ?, ?, ?, ?, ?, ?)",
                oferta.getIdOferta(), oferta.getIdHabilidad(), oferta.getDniPropietario(), oferta.getNombre(), oferta.getDescripcion(), oferta.getFechaIniciacion(), oferta.getFechaFinalizacion());
    }
    public List<Oferta> getOferta(String dniPropietario) {
        try {
            return jdbcTemplate.query("SELECT * FROM Oferta",
                    new OfertaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public void updateHabilidadOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET id_habilidad = '?' WHERE id_oferta = '?'",
                oferta.getIdHabilidad(), oferta.getIdOferta());
    }

    public void updateNombreOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET nombre = '?' WHERE id_oferta = '?'",
                oferta.getNombre(), oferta.getIdOferta());
    }

    public void updateDescripcionOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET descripcion = '?' WHERE id_oferta = '?'",
                oferta.getDescripcion(), oferta.getIdOferta());
    }

    public void FechaIniciacionOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET fecha_inic = '?' WHERE id_oferta = '?'",
                oferta.getFechaIniciacion(), oferta.getIdOferta());
    }

    public void FechaFinalizacionOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET fecha_fin = '?' WHERE id_oferta = '?'",
                oferta.getFechaFinalizacion(), oferta.getIdOferta());
    }

    public void deleteOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET activa = false WHERE id_oferta = '?'",
                oferta.getIdOferta());
    }
}
