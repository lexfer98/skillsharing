package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.*;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Habilidad;
import es.uji.ei1027.skillsharing.model.Oferta;
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
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
        return "skp/collist";
    }
    @RequestMapping("/solicitud/list")
    public String listSolicitudes(HttpSession session,Model model) {

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
}
