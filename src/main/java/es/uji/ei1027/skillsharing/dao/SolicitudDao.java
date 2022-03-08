package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SolicitudDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Solicitud> getSolicitudes(){
        try {
            return jdbcTemplate.query("SELECT * FROM Solicitud", new SolicitudRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<Solicitud>();
        }
    }

    public List<Solicitud> getSolicitud(String id_solicitud){
        try {
            return jdbcTemplate.query("SELECT * FROM Solicitud WHERE id_solicitud = '?'", new SolicitudRowMapper(),id_solicitud);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<Solicitud>();
        }
    }

    public void addSolicitud(Solicitud solicitud){
        jdbcTemplate.update("INSER INTO Solicitud VALUES(?,?,?,?,?,?,?,?)",solicitud.getId_solicitud(),solicitud.getId_habilidad(),solicitud.getDni_solicitud(),
                solicitud.getNombre(),solicitud.getDescripcion(),solicitud.getFecha_inic(),solicitud.getFecha_fin(), solicitud.isActiva());
    }

    public void updateSolicitud(Solicitud solicitud){
        jdbcTemplate.update("UPDATE Solicitud SET id_habilidad = '?', dni_solicitante = '?', nombre = '?', descripcion = '?', fecha_inic = '?'" +
                ", fecha_fin = '?', activa = '?' WHERE id_solicitud = '?'",solicitud.getId_habilidad(),solicitud.getDni_solicitud(),solicitud.getNombre(),
                solicitud.getDescripcion(),solicitud.getFecha_inic(),solicitud.getFecha_fin(),solicitud.getId_solicitud());
    }

    public void deleteSolicitud(Solicitud solicitud){
        jdbcTemplate.update("UPDATE Solicitud SET activa = false WHERE id_solicitud = '?'",solicitud.getId_solicitud());
    }
}
