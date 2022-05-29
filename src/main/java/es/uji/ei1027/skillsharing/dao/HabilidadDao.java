package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class HabilidadDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Habilidad> getHabilidades() {
        try {
            return jdbcTemplate.query("SELECT * FROM Habilidad  WHERE activa = true",
                    new HabilidadRowMapper());

        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Habilidad>();
        }
    }

    public List<Habilidad> getTodasHabilidades() {
        try {
            return jdbcTemplate.query("SELECT * FROM Habilidad",
                    new HabilidadRowMapper());

        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Habilidad>();
        }
    }

    public List<Habilidad> getHabilidadesDesactivadas() {
        try {
            return jdbcTemplate.query("SELECT * FROM Habilidad  WHERE activa = false",
                    new HabilidadRowMapper());

        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Habilidad>();
        }
    }

    public void desactivaHabilidad(int idHabilidad) {
        jdbcTemplate.update("UPDATE habilidad SET activa = false WHERE id_habilidad = ?", idHabilidad);
    }

    public void activaHabilidad(int idHabilidad) {
        jdbcTemplate.update("UPDATE habilidad SET activa = true WHERE id_habilidad = ?", idHabilidad);
    }

    public void addHabilidad(Habilidad habilidad) {
        jdbcTemplate.update(
                "INSERT INTO Habilidad VALUES(default, ?, ?, ?, true)", habilidad.getNombre(), habilidad.getDescripcion(), habilidad.getNivel().toString().toUpperCase(Locale.ROOT));
    }

    public void updateHabilidad(Habilidad habilidad) {
        jdbcTemplate.update(
                "UPDATE Habilidad SET nombre = ?, descripcion = ?, nivel = ? , activa = ? WHERE id_habilidad = ?",
                        habilidad.getNombre(), habilidad.getDescripcion(),
                habilidad.getNivel().toString().toUpperCase(Locale.ROOT), habilidad.getActiva(),habilidad.getId_habilidad());
    }

    public Habilidad getIdHabilidad(int habilidad) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Habilidad WHERE id_habilidad = ?",
                    new HabilidadRowMapper(),
                    habilidad);

        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

}
