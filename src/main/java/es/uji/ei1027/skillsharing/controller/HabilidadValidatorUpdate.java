package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.model.Habilidad;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class HabilidadValidatorUpdate implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Habilidad.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Habilidad habilidad = (Habilidad) obj;
        if(habilidad.getNombre().trim().equals(""))
            errors.rejectValue("nombre","obligatori","El campo nombre no puede estar vacío");
        if(habilidad.getDescripcion().trim().equals(""))
            errors.rejectValue("descripcion","obligatori","El campo descripcion no puede estar vacío");
    }
}
