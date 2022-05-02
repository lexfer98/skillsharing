package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;

import java.util.Collection;
import java.util.List;

public interface AlumnoRegDao {

    Alumno loadUserByDni(String dni, String contraseña);
    Collection<Alumno> listarUsuarios();
}
