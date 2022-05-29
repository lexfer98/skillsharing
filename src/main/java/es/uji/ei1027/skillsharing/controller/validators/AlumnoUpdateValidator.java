package es.uji.ei1027.skillsharing.controller.validators;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AlumnoUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Alumno.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        Alumno alumno = (Alumno) obj;
        if ((alumno.getNombre().trim().equals("") ))
            errors.rejectValue("nombre", "nombre",
                    "Es necesario introducir un nombre");
        if ((alumno.getApellidos().trim().equals("") ))
            errors.rejectValue("apellidos", "apellidos",
                    "Es necesario introducir tus apellidos");
        if ((alumno.getEdad() < 18 ))
            errors.rejectValue("edad", "edad",
                    "Es necesario tener más de 18 años");
        else if (Integer.toString(alumno.getNumTel()).length() < 9)
            errors.rejectValue("numTel", "numTel",
                    "El número de teléfono tiene que ser de 9 digitos");
        if ((alumno.getEmail().trim().equals("") ))
            errors.rejectValue("email", "email",
                    "Es necesario introducir un correo");
        if ((alumno.getTitulacion().trim().equals("") ))
            errors.rejectValue("titulacion", "titulacion",
                    "Es necesario introducir la titulación que estás cursando");
    }
}
