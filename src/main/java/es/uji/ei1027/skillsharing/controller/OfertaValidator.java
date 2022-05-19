package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.model.Oferta;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;

public class OfertaValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Oferta.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Oferta oferta = (Oferta) obj;
        if (oferta.getNombre().trim().equals("")) {
            errors.rejectValue("nombre","obligatori","El campo nombre no puede estar vacío");
        }
        if (oferta.getFechaIniciacion() == null){
            errors.rejectValue("fechaIniciacion","obligatori","Se necesita una fecha de iniciación");
        }
        if (oferta.getFechaFinalizacion() == null){
            errors.rejectValue("fechaFinalizacion","obligatori","Se necesita una fecha de finalicación");
        }
        if (oferta.getDescripcion().trim().equals("")){
            errors.rejectValue("descripcion","obligatori","El campo descripcion no puede estar vacío");
        }
        if ((oferta.getFechaFinalizacion() != null && oferta.getFechaIniciacion() != null) && oferta.getFechaFinalizacion().isBefore(oferta.getFechaIniciacion())){
            errors.rejectValue("fechaIniciacion && fechaFinalizacion","valor incorrecte","La fecha de iniciación no puede ser posterior a la fecha de finalización");
        }
        /*
        if(oferta.getFechaFinalizacion() != null && oferta.getFechaIniciacion().isBefore(LocalDate.now().minus(Period.ofDays(1)))){
            errors.rejectValue("fechaIniciacion","valor incorrecte","La fecha de inicio no puede ser anterior a la de hoy");
        }
        System.out.println(oferta.getFechaIniciacion().isBefore(LocalDate.now().minus(Period.ofDays(1))));
         */
    }

}
