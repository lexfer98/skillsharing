package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.model.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/habilidad")
public class HabilidadController {

    private HabilidadDao habilidadDao;

    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao){
        this.habilidadDao=habilidadDao;
    }

    @RequestMapping("/list")
    public String listHabilidades(Model model) {
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        return "habilidad/list";
    }

    @RequestMapping(value="/add")
    public String addHabilidad(Model model) {
        model.addAttribute("habilidad", new Habilidad());
        return "habilidad/add";
    }
    @RequestMapping(value="/update/{id_habilidad}", method = RequestMethod.GET)
    public String editHabilidad(Model model, @PathVariable String id_habilidad) {
        model.addAttribute("habilidad", id_habilidad);
        return "habilidad/update";
    }

}
