package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Habilidad;
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
@RequestMapping("/habilidad")
public class HabilidadController {

    private HabilidadDao habilidadDao;

    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao){
        this.habilidadDao=habilidadDao;
    }

    @RequestMapping("/list")
    public String listHabilidades(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        return "habilidad/list";
    }

    @RequestMapping(value="/add")
    public String addHabilidad(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidad", new Habilidad());
        return "habilidad/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("habilidad") Habilidad habilidad,
                                   BindingResult bindingResult, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        if (bindingResult.hasErrors())
            return "habilidad/add";
        habilidadDao.addHabilidad(habilidad);
        return "alumno/users";
    }

    @RequestMapping(value="/update/{id_habilidad}", method = RequestMethod.GET)
    public String editHabilidad(Model model, @PathVariable int id_habilidad, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidad", habilidadDao.getIdHabilidad(id_habilidad));
        return "habilidad/update";
    }

}
