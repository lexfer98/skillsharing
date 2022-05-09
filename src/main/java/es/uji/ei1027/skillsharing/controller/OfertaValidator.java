package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.model.Oferta;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OfertaValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Oferta.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Oferta oferta = (Oferta)obj;
        System.out.println("SU");
        if (oferta.getNombre().trim().equals("") || oferta.getDescripcion().trim().equals("") ||
                oferta.getFechaIniciacion() == null || oferta.getFechaFinalizacion() == null) {
            errors.rejectValue("oferta","badof","Hay que rellenar todos los campos");
        }
    }

}
