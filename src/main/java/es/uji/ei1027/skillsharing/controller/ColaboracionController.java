package es.uji.ei1027.skillsharing.controller;


import es.uji.ei1027.skillsharing.dao.ColaboracionDao;
import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.dao.OfertaDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Colaboracion;
import es.uji.ei1027.skillsharing.model.Oferta;
import es.uji.ei1027.skillsharing.model.Solicitud;
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
@RequestMapping("/colaboracion")
public class ColaboracionController {

    private ColaboracionDao colaboracionDao;

    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){
        this.colaboracionDao=colaboracionDao;
    }


    @RequestMapping("/list")
    public String listColaboraciones(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("colaboraciones", colaboracionDao.getColaboraciones());
        return "colaboracion/list";
    }

    @RequestMapping(value="/update/{id_colaboracion}", method = RequestMethod.GET)
    public String editColaboracion(Model model, @PathVariable int id_colaboracion, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("colaboracion", colaboracionDao.getColaboracion(id_colaboracion));
        return "colaboracion/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("colaboracion")Colaboracion colaboracion,
            BindingResult bindingResult, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        System.out.println(colaboracion);
        if (bindingResult.hasErrors())
            return "colaboracion/update";
        colaboracionDao.updateColaboracion(colaboracion);
        return "redirect:list";
    }

    @RequestMapping(value = "/add/{id_oferta}")
    public String processAddSubmit(@PathVariable int id_oferta,@PathVariable int id_solicitud, HttpSession session) {
        if (session.getAttribute("alumno") == null){
            return "../loginV2";
        }
        Colaboracion colaboracion = new Colaboracion();
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("alumno", session.getAttribute("alumno"));
        return "redirect:../../colaboracion/listpropia";
    }

}
