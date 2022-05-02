package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoDao;
import es.uji.ei1027.skillsharing.dao.AlumnoRegDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/list")
    public String listarAlumnos(HttpSession session,Model model){
        session.setAttribute("nextUrl","alumno/list");
        if (session.getAttribute("alumno") == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }

        return "alumno/list";
    }

    @RequestMapping(value="/add")
    public String addAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "alumno/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("alumno") Alumno alumno,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        return "alumno/add";
        alumnoDao.addAlumno(alumno);
        return "redirect:/";
    }

}