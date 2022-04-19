package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private AlumnoDao alumnoDao;

    @Autowired
    public void setAlumnoDao(AlumnoDao AlumnoDao){
        this.alumnoDao=alumnoDao;
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
        return "redirect:/login";
    }

}