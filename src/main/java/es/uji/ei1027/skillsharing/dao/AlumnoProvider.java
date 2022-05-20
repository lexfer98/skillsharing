package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AlumnoProvider implements AlumnoRegDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Alumno loadUserByDni(String dni, String contraseña) {

        Alumno alumno;

        try {
            alumno = jdbcTemplate.queryForObject("SELECT * FROM alumnos WHERE dni=?", new AlumnoRowMapper(), dni);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }



        if (alumno == null)
            return null; // Usuari no trobat
        // Contrasenya
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(contraseña, alumno.getContraseña())) {
            // Es deuria esborrar de manera segura el camp password abans de tornar-lo
            return alumno;
        }
        else {
            return null; // bad login!
        }
    }

    public Collection<Alumno> listarUsuarios(){
        return jdbcTemplate.query("SELECT * FROM alumnos",new AlumnoRowMapper());


    }



}