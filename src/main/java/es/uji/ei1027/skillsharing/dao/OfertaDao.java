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

    public List<Oferta> getOfertas() {
        try {
            return jdbcTemplate.query("SELECT * FROM Oferta",
                    new OfertaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public void updateOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET nombre = '?', descripcion = '?', fecha_inic = '?', fecha_fin='?' WHERE id_oferta = '?'",
                oferta.getNombre(), oferta.getDescripcion(), oferta.getFechaIniciacion(), oferta.getFechaFinalizacion(), oferta.getIdOferta());
    }

    public void deleteOferta(int idOferta) {
        jdbcTemplate.update("UPDATE oferta SET activa = false WHERE id_oferta = '?'",
                idOferta);
    }

    public Oferta getOfertas(int idOferta) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM oferta WHERE id_oferta = ?",
                    new OfertaRowMapper(),
                    idOferta);

        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
