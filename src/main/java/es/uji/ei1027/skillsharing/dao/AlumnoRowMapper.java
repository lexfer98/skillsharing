package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Genero;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AlumnoRowMapper implements RowMapper<Alumno> {

    public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setDni(rs.getString("dni"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setApellidos((rs.getString("apellidos")));
        alumno.setEmail(rs.getString("email"));
        alumno.setTitulacion(rs.getString("titulacion"));
        alumno.setCurso(rs.getInt("curso"));
        alumno.setFechaCreacion(rs.getObject("fechacreacion", LocalDate.class));
        alumno.setGenero(rs.getObject("genero", Genero.class));
        alumno.setEdad(rs.getInt("edad"));
        alumno.setNumTel(rs.getInt("numtelefono"));
        alumno.setBalanceHoras(rs.getInt("balancehoras"));
        alumno.setSkp(rs.getBoolean("SKP"));
        alumno.setContraseña(rs.getString("contraseña"));

        return alumno;
    }
}
