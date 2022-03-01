package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("habilidad", habilidadDao.getHabilidades());
        return "habilidad/list";
    }
}
