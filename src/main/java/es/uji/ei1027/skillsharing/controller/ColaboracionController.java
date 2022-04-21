package es.uji.ei1027.skillsharing.controller;


import es.uji.ei1027.skillsharing.dao.ColaboracionDao;
import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.dao.OfertaDao;
import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/colaboracion")
public class ColaboracionController {

    private ColaboracionDao colaboracionDao;

    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){
        this.colaboracionDao=colaboracionDao;
    }


    @RequestMapping("/list")
    public String listColaboraciones(Model model) {
        model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
        return "colaboracion/list";
    }

    @RequestMapping(value="/update/{id_colaboracion}", method = RequestMethod.GET)
    public String editColaboracion(Model model, @PathVariable int id_colaboracion) {
        model.addAttribute("colaboracion", colaboracionDao.getColaboracion(id_colaboracion));
        return "colaboracion/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("colaboracion")Colaboracion colaboracion,
            BindingResult bindingResult) {
        System.out.println(colaboracion);
        if (bindingResult.hasErrors())
            return "colaboracion/update";
        colaboracionDao.updateColaboracion(colaboracion);
        return "redirect:list";
    }

}
