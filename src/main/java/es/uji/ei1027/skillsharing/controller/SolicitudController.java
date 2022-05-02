package es.uji.ei1027.skillsharing.controller;

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

    @RequestMapping("/list")
    public String listSolicitudes(Model model) {
        model.addAttribute("solicitudes", solicitudDao.getSolicitudes());
        return "solicitud/list";
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
    public String editSolicitud(Model model, @PathVariable int id_solicitud) {
        model.addAttribute("solicitud", solicitudDao.getSolicitud(id_solicitud));
        return "solictud/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("solicitud") Solicitud solicitud,
            BindingResult bindingResult) {
        System.out.println(solicitud);
        if (bindingResult.hasErrors())
            return "solicitud/update";
        solicitudDao.updateSolicitud(solicitud);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id_solicitud}")
    public void processDelete(@PathVariable int id_solicitud) {
        solicitudDao.deleteSolicitud(id_solicitud);
    }
}