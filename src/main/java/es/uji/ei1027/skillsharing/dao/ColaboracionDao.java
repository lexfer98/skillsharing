package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ColaboracionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Colaboracion> getColaboraciones(){
        try{
            return jdbcTemplate.query("SELECT * FROM Colaboracion Where activo = true",new ColaboracionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public List<Colaboracion> getColaboracionesPropias(String dni){
        try{
            return jdbcTemplate.query("SELECT c.* FROM Colaboracion AS c JOIN solicitud AS s USING(id_solicitud) WHERE s.dni_solicitante = ? AND c.activo = true",
                    new ColaboracionRowMapper(), dni);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public Colaboracion getColaboracion(int id_colaboracion){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Colaboracion WHERE id_colaboracion = ? AND activo = true", new ColaboracionRowMapper(),id_colaboracion);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("INSERT INTO Colaboracion VALUES(default,?,?,?,?,null,null,null,true,false)",
                colaboracion.getIdSolicitud(),colaboracion.getIdOferta(),colaboracion.getFecha_inicio(),
                colaboracion.getFecha_fin());
    }

    public void updateColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("UPDATE Colaboracion SET horas = ?, puntuacion = ?, opinion = ?, finalizada = true , activo = false WHERE id_colaboracion = ?"
               ,colaboracion.getHoras(),colaboracion.getPuntuacion(),colaboracion.getOpinion(),
                colaboracion.getId_colaboracion());
    }

    public void deleteColaboracion(int id_colaboracion){
        jdbcTemplate.update("UPDATE Colaboracion SET activo = false WHERE id_colaboracion = ?", id_colaboracion);
    }

    public Double getValoracionMedia(String dniPropietario){
        try {
            return jdbcTemplate.queryForObject("SELECT ROUND(AVG(c.puntuacion),2) FROM Colaboracion AS c JOIN Oferta AS o USING(id_oferta) WHERE finalizada = true and dni_propietario = ? GROUP BY dni_propietario", Double.class , dniPropietario);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
