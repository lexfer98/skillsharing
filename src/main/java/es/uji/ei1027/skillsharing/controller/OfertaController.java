package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoDao;
import es.uji.ei1027.skillsharing.dao.ColaboracionDao;
import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.dao.OfertaDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Oferta;
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
@RequestMapping("/oferta")
public class OfertaController {

    private OfertaDao ofertaDao;
    private HabilidadDao habilidadDao;
    private AlumnoDao alumnoDao;
    private ColaboracionDao colaboracionDao;

    @Autowired
    public void setOfertaDao(OfertaDao ofertaDao) {
        this.ofertaDao = ofertaDao;
    }
    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao) {
        this.habilidadDao = habilidadDao;
    }
    @Autowired
    public void setAlumnoDao(AlumnoDao alumnoDao){ this.alumnoDao=alumnoDao;}
    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){ this.colaboracionDao = colaboracionDao;}

    @RequestMapping("/listpropias")
    public String listOfertas(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        model.addAttribute("alumno", alumno);
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("ofertas", ofertaDao.getTusOfertas(alumno.getDni()));
        return "oferta/listpropias";
    }


    @RequestMapping("/list")
    public String listTusOfertas(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("alumnos", alumnoDao.getAlumnos());
        model.addAttribute("ofertas", ofertaDao.getOfertas());
        return "oferta/list";
    }


    @RequestMapping("/listarSkillsOfertas")
    public String listTusOfertasSegunSkill(Model model,String habilidad, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("alumnos", alumnoDao.getAlumnos());
        model.addAttribute("ofertas", ofertaDao.getOfertasSegunSkill(habilidad));
        System.out.println(habilidad);
        System.out.println(ofertaDao.getOfertasSegunSkill(habilidad));
        return "oferta/list";
    }



    @RequestMapping(value="/add")
    public String addOferta(Model model, @ModelAttribute("alumno") Alumno alumno, HttpSession session) {
        session.setAttribute("nextUrl", "redirect:oferta/add");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("alumno", alumno);
        model.addAttribute("oferta", new Oferta());
        return "oferta/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("oferta") Oferta oferta,
                                   BindingResult bindingResult, HttpSession session, Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        OfertaValidator ofertaValidator = new OfertaValidator();
        ofertaValidator.validate(oferta,bindingResult);
        if (bindingResult.hasErrors())
            return "oferta/add";
        oferta.setDniPropietario(alumno.getDni());
        ofertaDao.addOferta(oferta);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{idOferta}")
    public String editOferta(Model model, @PathVariable int idOferta, HttpSession session) {
        session.setAttribute("nextUrl", "redirect:oferta/update/"+idOferta);
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidades", habilidadDao.getHabilidades());
        model.addAttribute("oferta", ofertaDao.getOferta(idOferta));
        return "oferta/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("oferta") Oferta oferta,
            BindingResult bindingResult, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        if (bindingResult.hasErrors())
            return "oferta/update";
        ofertaDao.updateOferta(oferta);
        return "redirect:list";
    }

    @RequestMapping(value="/oferta/{idOferta}")
    public String verOferta(Model model, @PathVariable int idOferta, HttpSession session) {
        session.setAttribute("nextUrl", "redirect:oferta/update/"+idOferta);
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        Double valoracion = (colaboracionDao.getValoracionMedia(ofertaDao.getOferta(idOferta).getDniPropietario())==null ? -1 : colaboracionDao.getValoracionMedia(ofertaDao.getOferta(idOferta).getDniPropietario()));
        session.setAttribute("alumno", session.getAttribute("alumno"));
        model.addAttribute("habilidad", habilidadDao.getIdHabilidad(ofertaDao.getOferta(idOferta).getIdHabilidad()));
        model.addAttribute("oferta", ofertaDao.getOferta(idOferta));
        model.addAttribute("alumno", alumnoDao.getAlumno(ofertaDao.getOferta(idOferta).getDniPropietario()));
        model.addAttribute("valoracion", valoracion);
        return "oferta/oferta";
    }

    @RequestMapping(value="/delete/{idOferta}")
    public String processDelete(@PathVariable int idOferta, HttpSession session, Model model) {
        session.setAttribute("nextUrl", "redirect:oferta/listpropias");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", session.getAttribute("alumno"));
        ofertaDao.deleteOferta(idOferta);
        return "redirect:oferta/listpropias";
    }
}
