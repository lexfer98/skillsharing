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
        jdbcTemplate.update("INSERT INTO oferta VALUES(default, ?, ?, ?, ?, ?, ?, true)",
                oferta.getIdHabilidad(), oferta.getDniPropietario(), oferta.getNombre(), oferta.getDescripcion(), oferta.getFechaIniciacion(), oferta.getFechaFinalizacion());
    }

    public List<Oferta> getOfertas(String dniUsuario) {
        try {
            return jdbcTemplate.query("SELECT * FROM Oferta where activa = true and dni_propietario!=?",
                    new OfertaRowMapper(), dniUsuario);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public List<Oferta> getTodasOfertas() {
        try {
            return jdbcTemplate.query("SELECT * FROM Oferta",
                    new OfertaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public List<Oferta> getTusOfertas(String dniPropietario) {
        try {
            return jdbcTemplate.query("SELECT * FROM Oferta where activa = true and dni_propietario=?",
                    new OfertaRowMapper(), dniPropietario);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public List<Oferta> getTodasTusOfertas(String dniPropietario) {
        try {
            return jdbcTemplate.query("SELECT * FROM Oferta where dni_propietario=?",
                    new OfertaRowMapper(), dniPropietario);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    //Ofertas globales segun skill
    public List<Oferta> getOfertasSegunSkill(String nombreHabilidad,String dni) {
        try {
            return jdbcTemplate.query("SELECT o.* FROM oferta AS o JOIN habilidad AS h USING(id_habilidad) WHERE o.activa = true AND h.nombre=? AND dni_propietario!=?",
                    new OfertaRowMapper(), nombreHabilidad,dni);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public void updateOferta(Oferta oferta) {
        jdbcTemplate.update("UPDATE oferta SET nombre = ?, descripcion = ?, fecha_inic = ?, fecha_fin=? WHERE id_oferta = ? and activa = ?",
                oferta.getNombre(), oferta.getDescripcion(), oferta.getFechaIniciacion(), oferta.getFechaFinalizacion(), oferta.getIdOferta(), oferta.isActiva());
    }

    public void deleteOferta(int idOferta) {
        jdbcTemplate.update("UPDATE oferta SET activa = false WHERE id_oferta = ?",
                idOferta);
    }

    public Oferta getOferta(int idOferta) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM oferta WHERE id_oferta = ? and activa = true",
                    new OfertaRowMapper(),
                    idOferta);

        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public Oferta getOfertaIndiferente(int idOferta) {
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
