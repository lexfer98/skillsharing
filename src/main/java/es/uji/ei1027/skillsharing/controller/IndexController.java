package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.dao.OfertaDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private OfertaDao ofertaDao;
    private HabilidadDao habilidadDao;

    @Autowired
    public void setOfertaDao(OfertaDao ofertaDao) {
        this.ofertaDao = ofertaDao;
    }
    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao) {
        this.habilidadDao = habilidadDao;
    }

    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model) {
        List<Habilidad> habilidades = habilidadDao.getTodasHabilidades();
        Habilidad noHabilidad = new Habilidad();
        List<String> nombres = new ArrayList<>();
        noHabilidad.setNombre("Todas");
        habilidades.add(0, noHabilidad);
        for(Habilidad h : habilidades){
            if (!nombres.contains(h.getNombre())){
                nombres.add(h.getNombre());
            }
        }
        System.out.println("Hola");
        System.out.println(nombres.toString());
        if (session.getAttribute("alumno") == null){
            model.addAttribute("nombres", nombres);
            model.addAttribute("habilidades", habilidades);
            model.addAttribute("ofertas", ofertaDao.getOfertas(""));
            model.addAttribute("alumno",new Alumno());
            return "index.html";
        }
        return "alumno/users";
    }
}
