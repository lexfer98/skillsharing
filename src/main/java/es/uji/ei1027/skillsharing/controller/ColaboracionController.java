package es.uji.ei1027.skillsharing.controller;


import es.uji.ei1027.skillsharing.dao.*;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Oferta;
import es.uji.ei1027.skillsharing.model.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/colaboracion")
public class ColaboracionController {

    private ColaboracionDao colaboracionDao;
    private SolicitudDao solicitudDao;
    private OfertaDao ofertaDao;

    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){
        this.colaboracionDao=colaboracionDao;
    }

    @Autowired
    public void setSolicitudDao(SolicitudDao solicitudDao){
        this.solicitudDao=solicitudDao;
    }

    @Autowired
    public void setOfertaDao(OfertaDao ofertaDao){
        this.ofertaDao=ofertaDao;
    }



    @RequestMapping("/list")
    public String listColaboraciones(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
        return "colaboracion/list";
    }

    @RequestMapping("/listpropias")
    public String listColaboracionesPropias(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        model.addAttribute("colaboraciones", colaboracionDao.getColaboracionesPropias(alumno.getDni()));
        return "colaboracion/listpropias";
    }

    @RequestMapping(value="/update/{id_colaboracion}", method = RequestMethod.GET)
    public String editColaboracion(Model model, @PathVariable int id_colaboracion, HttpSession session) {
        session.setAttribute("nextUrl", "redirect:colaboracion/update/"+id_colaboracion);
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("colaboracion", colaboracionDao.getColaboracion(id_colaboracion));
        return "colaboracion/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("colaboracion")Colaboracion colaboracion,
            BindingResult bindingResult, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        System.out.println(colaboracion);
        if (bindingResult.hasErrors())
            return "colaboracion/update";
        colaboracionDao.updateColaboracion(colaboracion);
        return "redirect:listpropias";
    }

    @RequestMapping(value = "/add/{id_solicitud}")
    public String processAddSubmit(@PathVariable int id_solicitud,HttpSession session) {
        if (session.getAttribute("alumno") == null){
            return "../loginV2";
        }
        Solicitud solicitud = solicitudDao.getSolicitud(id_solicitud);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.crearColaboracion(solicitud);
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("alumno", session.getAttribute("alumno"));
        solicitudDao.aceptarSolicitud(id_solicitud);
        colaboracionDao.addColaboracion(colaboracion);
        return "redirect:../../solicitud/listsolicitadas/"+solicitud.getId_oferta();
    }

}
