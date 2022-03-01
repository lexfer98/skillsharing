package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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




}
