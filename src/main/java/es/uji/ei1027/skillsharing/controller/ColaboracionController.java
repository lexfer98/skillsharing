package es.uji.ei1027.skillsharing.controller;


import es.uji.ei1027.skillsharing.controller.validators.ValorarColaboracionValidator;
import es.uji.ei1027.skillsharing.dao.*;
import es.uji.ei1027.skillsharing.model.*;
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
    private AlumnoDao alumnoDao;
    private HabilidadDao habilidadDao;

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

    @Autowired
    public void setAlumnoDao(AlumnoDao alumnoDao){
        this.alumnoDao=alumnoDao;
    }

    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao){ this.habilidadDao = habilidadDao; }



    @RequestMapping("/listpropias")
    public String listColaboracionesPropias(Model model, HttpSession session) {
        session.setAttribute("nextUrl", "redirect:colaboracion/listpropias");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", session.getAttribute("alumno"));
        session.setAttribute("solicitud", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        model.addAttribute("alumno",alumno);
        model.addAttribute("solicitudes", solicitudDao.getTodasSolicitudes());
        model.addAttribute("ofertas", ofertaDao.getTodasOfertas());
        model.addAttribute("habilidades", habilidadDao.getTodasHabilidades());
        model.addAttribute("colaboraciones", colaboracionDao.getColaboracionesPropias(alumno.getDni()));
        return "colaboracion/listpropias";
    }

    @RequestMapping(value="/update/{id_colaboracion}", method = RequestMethod.GET)
    public String editColaboracion(Model model, @PathVariable int id_colaboracion, HttpSession session) {
        session.setAttribute("nextUrl", "redirect:colaboracion/update/"+id_colaboracion);
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("colaboracion", colaboracionDao.getColaboracion(id_colaboracion));
        return "colaboracion/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("colaboracion")Colaboracion colaboracion,
            BindingResult bindingResult, HttpSession session, Model model) {
        session.setAttribute("nextUrl", "redirect:colaboracion/update/"+colaboracion.getId_colaboracion());
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        Solicitud solicitud = solicitudDao.getSolicitud(colaboracion.getIdSolicitud());
        Oferta oferta = ofertaDao.getOferta(colaboracion.getIdOferta());
        if (!alumno.getDni().equals(solicitud.getDni_solicitud()) && !alumno.getDni().equals(oferta.getDniPropietario())){
            return "alumno/users";
        }
        ValorarColaboracionValidator colaboracionValidator = new ValorarColaboracionValidator();
        colaboracionValidator.validate(colaboracion,bindingResult);
        if (bindingResult.hasErrors())
            return "colaboracion/update";
        alumnoDao.modificarHorasAlumno(-(colaboracion.getHoras()), solicitud.getDni_solicitud());
        alumnoDao.modificarHorasAlumno(colaboracion.getHoras(), oferta.getDniPropietario());
        colaboracionDao.updateColaboracion(colaboracion);
        return "redirect:listpropias";
    }

    @RequestMapping(value = "/add/{id_solicitud}")
    public String processAddSubmit(@PathVariable int id_solicitud,HttpSession session, Model model) {
        Solicitud solicitud = solicitudDao.getSolicitud(id_solicitud);
        session.setAttribute("nextUrl", "redirect:solicitud/listsolicitadas/"+solicitud.getId_oferta());
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        Oferta oferta = ofertaDao.getOferta(solicitud.getId_oferta());
        if (!oferta.getDniPropietario().equals(alumno.getDni())){
            return "alumno/users";
        }
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.crearColaboracion(solicitud);
        session.setAttribute("alumno", session.getAttribute("alumno"));
        solicitudDao.aceptarSolicitud(id_solicitud);
        colaboracionDao.addColaboracion(colaboracion);
        return "redirect:../../solicitud/listsolicitadas/"+solicitud.getId_oferta();
    }

    @RequestMapping(value = "/colaboracionVerValoracion/{id_colaboracion}")
    public String datosColaboracion(Model model,@PathVariable int id_colaboracion ,HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }

        Colaboracion colaboracion = colaboracionDao.getColaboracion(id_colaboracion);
        Solicitud solicitud = solicitudDao.getSolicitudIndiferente(colaboracion.getIdSolicitud());
        Oferta oferta = ofertaDao.getOfertaIndiferente(colaboracion.getIdOferta());
        Habilidad habilidad = habilidadDao.getIdHabilidad(solicitud.getId_habilidad());
        Alumno propietario = alumnoDao.getAlumno(oferta.getDniPropietario());
        Alumno solicitante = alumnoDao.getAlumno(solicitud.getDni_solicitud());
        model.addAttribute("colaboracion", colaboracion);
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("oferta", oferta);
        model.addAttribute("habilidad", habilidad);
        model.addAttribute("propietario", propietario);
        model.addAttribute("solicitante", solicitante);
        model.addAttribute("valoracion", colaboracionDao.getValoracionMedia(propietario.getDni()));
        return "colaboracion/colaboracionVerValoracion";
    }

}
