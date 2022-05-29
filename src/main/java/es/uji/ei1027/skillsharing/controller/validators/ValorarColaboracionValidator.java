package es.uji.ei1027.skillsharing.controller.validators;

import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Oferta;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValorarColaboracionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Colaboracion.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Colaboracion colaboracion = (Colaboracion) obj;


        if (colaboracion.getHoras()== 0) {
            errors.rejectValue("horas","obligatori","Es necesario poner las horas empleadas");
        }
        if (colaboracion.getPuntuacion() < 0 || colaboracion.getPuntuacion() > 5){
            errors.rejectValue("puntuacion","obligatori","Es necesaria una puntuación del 1 al 5");
        }
        if (colaboracion.getOpinion().trim().equals("")){
            errors.rejectValue("opinion","obligatori","Necesitamos que des feedback sobre la colaboración");
        }
    }

}