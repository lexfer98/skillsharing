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
            return jdbcTemplate.query("SELECT * FROM Colaboracion Where activa = true",new ColaboracionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public List<Colaboracion> getColaboracionesPropias(String dni){
        try{
            return jdbcTemplate.query("SELECT c.* FROM Colaboracion AS c JOIN oferta AS o USING(id_oferta) JOIN solicitud AS s USING(id_solicitud) WHERE o.dni_propietario=? OR s.dni_solicitante = ? AND activa = true",
                    new ColaboracionRowMapper(), dni, dni);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public List<Colaboracion> getColaboracion(int id_colaboracion){
        try {
            return jdbcTemplate.query("SELECT * FROM Colaboracion WHERE id_colaboracion = ? AND activa = true", new ColaboracionRowMapper(),id_colaboracion);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public void addColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("INSERT INTO Colaboracion VALUES(default,?,?,?,?,null,null,null,true)",
                colaboracion.getIdSolicitud(),colaboracion.getIdOferta(),colaboracion.getFecha_inicio(),
                colaboracion.getFecha_fin());
    }

    public void updateColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("UPDATE Colaboracion SET dni_propietario = ?, dni_solicitante  = ?, id_solicitud = ?, id_oferta = ?," +
                        "fecha_inic = ?, fecha_fin = ?, horas = ?, puntuacion = ?, opinion = ? WHERE id_colaboracion = ?"
               ,colaboracion.getIdSolicitud(),colaboracion.getIdOferta(), colaboracion.getFecha_inicio(),colaboracion.getFecha_fin()
                ,colaboracion.getHoras(),colaboracion.getPuntuacion(),colaboracion.getOpinion(),
                colaboracion.getId_colaboracion());
    }

    public void deleteColaboracion(int id_colaboracion){
        jdbcTemplate.update("UPDATE Colaboracion SET activa = false WHERE id_colaboracion = ?", id_colaboracion);
    }


}
