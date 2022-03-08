package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlumnoDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Alumno> getAlumnos(){
        try {
            return jdbcTemplate.query("SELECT * FROM Alumno", new AlumnoRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public List<Alumno> getAlumno(String dni){
        try {
            return jdbcTemplate.query("SELECT * FROM Alumno WHERE dni = '?'", new AlumnoRowMapper(), dni);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public void addAlumno(Alumno alumno){
        jdbcTemplate.update("INSERT INTO Alumno VALUES(?,?,?,?,?,?,?,?,?,?)", alumno.getDni(), alumno.getNombre(),alumno.getApellidos(), alumno.getEmail(),
                alumno.getTitulacion(), alumno.getGenero(),alumno.getEdad(), alumno.getNumTel(), alumno.getBalanceHoras(), alumno.isSkp());
    }

    public void updateAlumno(Alumno alumno){
        jdbcTemplate.update("UPDATE Alumno SET nombre = '?', apellidos = '?', email = '?', titulacion = '?', genero = '?', edad = '?', num_tel = '?', " +
                "balance_horas = '?', isSKP = '?' WHERE dni = '?'", alumno.getNombre(), alumno.getApellidos(), alumno.getEmail(), alumno.getTitulacion(),
                alumno.getGenero(), alumno.getEdad(), alumno.getNumTel(), alumno.getBalanceHoras(), alumno.isSkp(), alumno.getDni());
    }

    public void deleteAlumno(Alumno alumno){
        jdbcTemplate.update("DELETE FROM Alumno WHERE = '?'", alumno.getDni());
    }
}

