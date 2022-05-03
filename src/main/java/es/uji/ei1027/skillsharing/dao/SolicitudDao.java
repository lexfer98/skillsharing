package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Oferta;
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

    public List<Solicitud> getSolicitud(int id_solicitud){
        try {
            return jdbcTemplate.query("SELECT * FROM Solicitud WHERE id_solicitud = '?'", new SolicitudRowMapper(),id_solicitud);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<Solicitud>();
        }
    }

    public List<Oferta> getTusSolicitudes(String dniPropietario) {
        try {
            return jdbcTemplate.query("SELECT * FROM Solicitud where activa = true and dni_propietadio='?'",
                    new OfertaRowMapper(), dniPropietario);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Oferta>();
        }
    }

    public void addSolicitud(Solicitud solicitud){
        jdbcTemplate.update("INSERT INTO Solicitud VALUES(default,?,?,null,?,?,?,?,?,true)",solicitud.getId_habilidad(), solicitud.getId_oferta(), solicitud.getDni_solicitud(), solicitud.getNombre(), solicitud.getDescripcion(), solicitud.getFecha_inic(), solicitud.getFecha_fin());
    }

    public void updateSolicitud(Solicitud solicitud){
        jdbcTemplate.update("UPDATE Solicitud SET id_habilidad = '?',id_oferta = '?',estado = '?', dni_solicitante = '?', nombre = '?', descripcion = '?', fecha_inic = '?'" +
                ", fecha_fin = '?', activa = '?' WHERE id_solicitud = '?'",solicitud.getId_habilidad(),solicitud.getId_oferta(),solicitud.isEstado(),solicitud.getDni_solicitud(),solicitud.getNombre(),
                solicitud.getDescripcion(),solicitud.getFecha_inic(),solicitud.getFecha_fin(),solicitud.getId_solicitud());
    }

    public void deleteSolicitud(int solicitud){
        jdbcTemplate.update("UPDATE Solicitud SET activa = false WHERE id_solicitud = '?'",solicitud);
    }

    public void desactivarSolicitud(int solicitud){
        jdbcTemplate.update("UPDATE Solicitud SET estado = false WHERE id_solicitud = '?'",solicitud);
    }
}
