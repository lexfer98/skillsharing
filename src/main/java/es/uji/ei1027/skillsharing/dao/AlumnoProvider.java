package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AlumnoProvider implements AlumnoRegDao {

    private AlumnoDao alumnoDao;
    @Autowired
    public void setAlumnoDao(AlumnoDao alumnoDao){
        this.alumnoDao= alumnoDao;
    }
    @Override
    public Alumno loadUserByUsername(String dni, String contraseña) {
        Alumno alumno = alumnoDao.getAlumno(dni);

        if (alumno == null)
            return null; // Usuari no trobat
        // Contrasenya
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(contraseña, alumno.getContraseña())) {
            // Es deuria esborrar de manera segura el camp password abans de tornar-lo
            alumno.encriptarContraseña();
            return alumno;
        }
        else {
            return null; // bad login!
        }
    }

}

