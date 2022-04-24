package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.Alumno;

import java.util.Collection;

public interface AlumnoRegDao {

    Alumno loadUserByUsername(String dni, String contraseña);
    Collection<Alumno> listarUsuarios();

}
