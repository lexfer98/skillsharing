package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoDao;
import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.dao.OfertaDao;
import es.uji.ei1027.skillsharing.dao.SolicitudDao;
import es.uji.ei1027.skillsharing.model.Alumno;
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
@RequestMapping("/solicitud")
public class SolicitudController {

    private SolicitudDao solicitudDao;
    private HabilidadDao habilidadDao;
    private OfertaDao ofertaDao;
    private AlumnoDao alumnoDao;

    @Autowired
    public void setSolicitudDao(SolicitudDao solicitudDao) {
        this.solicitudDao = solicitudDao;
    }

    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao) {
        this.habilidadDao = habilidadDao;
    }

    @Autowired
    public void setOfertaDao(OfertaDao ofertaDao) {
        this.ofertaDao = ofertaDao;
    }

    @Autowired
    public void setAlumnoDao(AlumnoDao alumnoDao) { this.alumnoDao = alumnoDao; }

    //Lo que tu solicitas para saber si te la han aceptado o no
    @RequestMapping("/listpropias")
    public String listTusSolicitudes(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        model.addAttribute("alumno", alumno);
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("solicitudes", solicitudDao.getTusSolicitadas(alumno.getDni()));
        return "solicitud/listpropias";
    }

    @RequestMapping("/listsolicitadas/{id_oferta}")
    public String listSolicitudesDeCadaOferta(Model model, @PathVariable int id_oferta, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("alumnos", alumnoDao.getAlumnos());
        model.addAttribute("solicitudes", solicitudDao.getSolicitudesDeCadaOferta(id_oferta));
        return "solicitud/listsolicitadas";
    }


    @RequestMapping(value = "/add/{id_oferta}")
    public String processAddSubmit(@PathVariable int id_oferta, HttpSession session) {
        if (session.getAttribute("alumno") == null){
            return "../loginV2";
        }
        Solicitud solicitud = new Solicitud();
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        solicitud.crearSolicitudOferta(ofertaDao.getOferta(id_oferta));
        solicitud.setDni_solicitud(alumno.getDni());
        solicitudDao.addSolicitud(solicitud);
        session.setAttribute("alumno", session.getAttribute("alumno"));
        return "redirect:../../oferta/list";
    }

    @RequestMapping(value = "/update/{id_solicitud}", method = RequestMethod.GET)
    public String editSolicitud(Model model, @PathVariable int id_solicitud, HttpSession session) {
        model.addAttribute("solicitud", solicitudDao.getSolicitud(id_solicitud));
        session.setAttribute("alumno", session.getAttribute("alumno"));
        return "solictud/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("solicitud") Solicitud solicitud,
            BindingResult bindingResult,
            HttpSession session) {
        if (bindingResult.hasErrors())
            return "solicitud/update";
        solicitudDao.updateSolicitud(solicitud);
        session.setAttribute("alumno", session.getAttribute("alumno"));
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{idSolicitud}")
    public String processDelete(@PathVariable int idSolicitud, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        solicitudDao.deleteSolicitud(idSolicitud);
        return "redirect:/solicitud/listpropias/";
    }

    @RequestMapping(value = "/rechazar/{id_solicitud}")
    public String rechazar(@PathVariable int id_solicitud, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        solicitudDao.rechazarSolicitud(id_solicitud);
        return "redirect:/solicitud/listsolicitadas/"+solicitudDao.getSolicitud(id_solicitud).getId_oferta();
    }





}