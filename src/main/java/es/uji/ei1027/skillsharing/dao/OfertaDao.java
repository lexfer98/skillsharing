package es.uji.ei1027.skillsharing.dao;

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
}
