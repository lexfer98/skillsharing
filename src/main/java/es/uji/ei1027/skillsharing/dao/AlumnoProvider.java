package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AlumnoProvider implements AlumnoRegDao {

    final Map<String, Alumno> knownUsers = new HashMap<String, Alumno>();


    @Override
    public Alumno loadUserByUsername(String dni, String contraseña) {
        Alumno alumno = knownUsers.get(dni.trim());
        if (alumno == null)
            return null; // Usuari no trobat
        // Contrasenya
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(contraseña, alumno.getContraseña())) {
            // Es deuria esborrar de manera segura el camp password abans de tornar-lo
            return alumno;
        } else {
            return null; // bad login!
        }
    }

        @Override
        public Collection<Alumno> listarUsuarios () {
            return knownUsers.values();
        }

}

