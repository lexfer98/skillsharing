package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AlumnoProvider implements AlumnoRegDao {
    AlumnoDao knownUsers = new AlumnoDao();

    @Autowired
    public void setAlumnoProviderDao(AlumnoDao alumnoDao) {
        this.knownUsers = alumnoDao;
    }
    @Override
    public Alumno loadUserByUsername(String dni, String contraseña) {
        Alumno alumno = knownUsers.getAlumno(dni);
        System.out.println("Provider Contraseña");
        System.out.println(alumno);
        System.out.println("Pass");
        System.out.println(contraseña);
        if (alumno == null)
            return null; // Usuari no trobat
        // Contrasenya
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(contraseña, alumno.getContraseña())) {
            // Es deuria esborrar de manera segura el camp password abans de tornar-lo
            alumno.encriptarContraseña();
            alumno.encriptarContraseña();
            return alumno;
        }
        else {
            return null; // bad login!
        }
    }

}

