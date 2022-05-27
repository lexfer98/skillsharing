package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.*;
import es.uji.ei1027.skillsharing.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/skp")
public class SkpController {

    private ColaboracionDao colaboracionDao;
    private SolicitudDao solicitudDao;
    private HabilidadDao habilidadDao;
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
    public void setAlumnoDao(AlumnoDao alumnoDao) { this.alumnoDao = alumnoDao; }

    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){
        this.colaboracionDao=colaboracionDao;
    }

    @RequestMapping("/menu")
    public String menuSkp(HttpSession session, Model model){
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
        model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
        return "skp/collist";
    }
    @RequestMapping("/solicitud/list")
    public String listSolicitudes(HttpSession session,Model model) {

        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("alumnos", alumnoDao.getAlumnos());
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("solicitudes", solicitudDao.getSolicitudes());
        return "skp/sollist";
    }

}
