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
            return jdbcTemplate.query("SELECT * FROM Colaboracion",new ColaboracionRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public List<Colaboracion> getColaboracion(String id_colaboracion){
        try {
            return jdbcTemplate.query("SELECT * FROM Colaboracion WHERE id_colaboracion = '?'", new ColaboracionRowMapper(),id_colaboracion);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public void addColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("INSERT INTO Colaboracion VALUES(?,?,?,?,?,?,?,?,?,?)", colaboracion.getId_colaboracion(),colaboracion.getDni_propietario(),colaboracion.getDni_solicitante(),
                colaboracion.getIdSolicitud(),colaboracion.getIdOferta(),colaboracion.getFecha_inicio(),colaboracion.getFecha_fin(),colaboracion.getHoras(),
                colaboracion.getPuntuacion(),colaboracion.getOpinion());
    }
    public void updateColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("UPDATE Colaboracion SET dni_propietario = '?', dni_solicitante  = '?', id_solicitud = '?', id_oferta = '?'," +
                "fecha_inic = '?', fecha_fin = '?', horas = '?', puntuacion = '?', opinion = '?' WHERE id_colaboracion = '?'",
                colaboracion.getDni_propietario(),colaboracion.getDni_solicitante(),colaboracion.getIdSolicitud(),colaboracion.getIdOferta(),
                colaboracion.getFecha_inicio(),colaboracion.getFecha_fin(),colaboracion.getHoras(),colaboracion.getPuntuacion(),colaboracion.getOpinion(),
                colaboracion.getId_colaboracion());
    }

    public void deleteColaboracion(Colaboracion colaboracion){
        jdbcTemplate.update("DELETE FROM Colaboracion WHERE = '?'",colaboracion.getId_colaboracion());
    }
}
