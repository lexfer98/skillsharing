package es.uji.ei1027.skillsharing.controller;

import com.fasterxml.jackson.core.JsonToken;
import es.uji.ei1027.skillsharing.dao.*;
import es.uji.ei1027.skillsharing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/skp")
public class SkpController {

    private ColaboracionDao colaboracionDao;
    private SolicitudDao solicitudDao;
    private HabilidadDao habilidadDao;
    private AlumnoDao alumnoDao;
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
    public void setAlumnoDao(AlumnoDao alumnoDao) { this.alumnoDao = alumnoDao; }

    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){
        this.colaboracionDao=colaboracionDao;
    }

    @Autowired
    public void setOfertaDao(OfertaDao ofertaDao) { this.ofertaDao=ofertaDao; }

    @RequestMapping("/menu")
    public String menuSkp(HttpSession session, Model model){
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if ( alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        else if (!alumno.isSkp())
            return "/alumno/users";
        return "skp/menu";
    }
    @RequestMapping("/colaboracion/list")
    public String listColaboraciones(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("alumnos", alumnoDao.getAlumnos());
        model.addAttribute("solicitudes", solicitudDao.getTodasSolicitudes());
        model.addAttribute("ofertas", ofertaDao.getTodasOfertas());
        model.addAttribute("habilidades", habilidadDao.getTodasHabilidades());
        model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
        return "skp/collist";
    }
    @RequestMapping("/solicitud/list")
    public String listSolicitudes(HttpSession session,Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("alumnos", alumnoDao.getAlumnos());
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("solicitudes", solicitudDao.getSolicitudes());
        return "skp/sollist";
    }
    @RequestMapping("/alumnos/list")
    public String listarAlumnos(HttpSession session,Model model){
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("alumnos",alumnoDao.getAlumnos());
        return "skp/list";
    }
    @RequestMapping(value="/ban/{dni}")
    public String motivoBan(Model model, @PathVariable String dni, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        model.addAttribute("alumnoban",alumnoDao.getAlumno(dni));
        return "skp/ban";
    }
    @RequestMapping(value="/ban", method = RequestMethod.POST)
    public String banearsubmmit(Model model,  @ModelAttribute("alumnoBan") Alumno alumnoBan, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        System.out.println(alumnoBan);
        alumnoDao.banearAlumno(alumnoBan);
        return "redirect:alumnos/list";
    }

    @RequestMapping(value="/desbanear/{dni}")
    public String processDesbanear(@PathVariable String dni, HttpSession session, Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        session.setAttribute("nextUrl", "redirect:skp/menu");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        System.out.println(dni);
        alumnoDao.desbanearAlumno(dni);
        return "redirect:../alumnos/list";
    }

    @RequestMapping(value = "/colaboracion/{id_colaboracion}")
    public String datosColaboracion(Model model,@PathVariable int id_colaboracion ,HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        if(!alumno.isSkp())
            return "alumno/users";

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
        return "skp/colaboracion";
    }
    @RequestMapping(value = "/colaboracionBorrar/{id_colaboracion}")
    public String colaboracionBorrar(Model model,@PathVariable int id_colaboracion ,HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        if(!alumno.isSkp())
            return "alumno/users";

        colaboracionDao.deleteColaboracion(id_colaboracion);
        return "redirect:../colaboracion/list";
    }
}
