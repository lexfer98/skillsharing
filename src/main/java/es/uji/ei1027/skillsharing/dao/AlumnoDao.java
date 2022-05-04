package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
            return jdbcTemplate.query("SELECT * FROM Alumnos", new AlumnoRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    public Alumno getAlumno(String dni){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Alumnos WHERE dni = ?", new AlumnoRowMapper(), dni);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addAlumno(Alumno alumno){
        jdbcTemplate.update("INSERT INTO Alumnos VALUES(?,?,?,?,?,?,?,?,?,?,?,0,false)", alumno.getDni(), alumno.getNombre(),alumno.getApellidos(), alumno.getContraseña(),alumno.getEmail(),
                LocalDate.now(),alumno.getTitulacion(), alumno.getCurso(), alumno.getGenero(), alumno.getEdad(), alumno.getNumTel());
    }

    public void updateAlumno(Alumno alumno){
        jdbcTemplate.update("UPDATE Alumnos SET nombre = '?', apellidos = '?', email = '?', titulacion = '?', genero = '?', edad = '?', num_tel = '?', " +
                "balance_horas = '?', isSKP = '?' WHERE dni = '?'", alumno.getNombre(), alumno.getApellidos(), alumno.getEmail(), alumno.getTitulacion(),
                alumno.getGenero(), alumno.getEdad(), alumno.getNumTel(), alumno.getBalanceHoras(), alumno.isSkp(), alumno.getDni());
    }

    public void deleteAlumno(Alumno alumno){
        jdbcTemplate.update("DELETE FROM Alumnos WHERE = '?'", alumno.getDni());
    }
}

