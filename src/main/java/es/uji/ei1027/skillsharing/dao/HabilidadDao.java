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
            return jdbcTemplate.query("SELECT * FROM Habilidad",
                    new HabilidadRowMapper());

        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Habilidad>();
        }
    }

    public void deleteHabilidad(Habilidad habilidad) {
        jdbcTemplate.update("DELETE FROM habilidad WHERE id_habilidad = '?'",habilidad.getId_habilidad());
    }

    public void addHabilidad(Habilidad habilidad) {
        jdbcTemplate.update(
                "INSERT INTO Habilidad VALUES(?, ?, ?, ?, ?)",habilidad.getId_habilidad(), habilidad.getNombre(), habilidad.getNivel().toString().toUpperCase(Locale.ROOT), habilidad.getDescripcion(), habilidad.getActiva());
    }


}
