package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoDao;
import es.uji.ei1027.skillsharing.dao.AlumnoRegDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Habilidad;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/alumno")
public class AlumnoController{

    private AlumnoDao alumnoDao;
    private AlumnoRegDao alumnoRegDao;



    @Autowired
    public void setAlumnoDao(AlumnoDao AlumnoDao){
        this.alumnoDao= AlumnoDao;
    }

    @Autowired
    public void setAlumnoRegDao(AlumnoRegDao alumnoRegDao){
        this.alumnoRegDao= alumnoRegDao;
    }

    @RequestMapping(value="/add")
    public String addAlumno(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("alumno", new Alumno());
        return "alumno/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("alumno") Alumno alumno,
                                   BindingResult bindingResult, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        AlumnoAddValidator alumnoValidator = new AlumnoAddValidator();
        alumnoValidator.validate(alumno,bindingResult);
        if (bindingResult.hasErrors())
            return "alumno/add";
        alumno.encriptarContraseña();
        alumnoDao.addAlumno(alumno);
        return "redirect:/";
    }

    @RequestMapping("/users")
    public String llevarMenú(HttpSession session,Model model) {

        session.setAttribute("nextUrl","alumno/users");
        if (session.getAttribute("alumno") == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        return "alumno/users";
    }

    @RequestMapping("/perfil")
    public String llevarPerfil(HttpSession session,Model model) {
        session.setAttribute("nextUrl","alumno/perfil");

        session.setAttribute("alumno", session.getAttribute("alumno"));
        if (session.getAttribute("alumno") == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }

        Alumno alumno = (Alumno) session.getAttribute("alumno");
        String dni = (alumno!=null ? alumno.getDni():"");

        model.addAttribute("alumnos", alumnoDao.getAlumno(dni));
        return "alumno/perfil";
    }

    @RequestMapping("/contactanos")
    public String llevarContactanos(HttpSession session,Model model) {

        session.setAttribute("nextUrl","alumno/contactanos");
        if (session.getAttribute("alumno") == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        return "alumno/contactanos";
    }

}