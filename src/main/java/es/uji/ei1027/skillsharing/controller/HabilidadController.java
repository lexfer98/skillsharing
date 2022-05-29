package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import es.uji.ei1027.skillsharing.model.Habilidad;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/habilidad")
public class HabilidadController {

    private HabilidadDao habilidadDao;

    @Autowired
    public void setHabilidadDao(HabilidadDao habilidadDao){
        this.habilidadDao=habilidadDao;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listHabilidades(Model model,HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("nextUrl", "redirect:habilidad/list");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("habilidades", habilidadDao.getTodasHabilidades());
        return "habilidad/list";
    }

    @RequestMapping("/listarHabilidadesEstado")
    public String listarHabilidadesEstado(Model model,String habilidad, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if(!alumno.isSkp())
            return "alumno/users";
        if (habilidad.equals("Todas")){
            model.addAttribute("habilidades", habilidadDao.getTodasHabilidades());
        }else if (habilidad.equals("Desactivadas")){
            model.addAttribute("habilidades", habilidadDao.getHabilidadesDesactivadas());
        }else{
            model.addAttribute("habilidades", habilidadDao.getHabilidades());
        }
        return "habilidad/list";
    }

    @RequestMapping(value="/add")
    public String addHabilidad(Model model, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("nextUrl", "redirect:habilidad/add");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("habilidad", new Habilidad());
        return "habilidad/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("habilidad") Habilidad habilidad,
                                   BindingResult bindingResult, HttpSession session, Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("nextUrl", "redirect:habilidad/add");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        HabilidadValidatorAdd habilidadValidatorAdd = new HabilidadValidatorAdd();
        habilidadValidatorAdd.validate(habilidad,bindingResult);
        if (bindingResult.hasErrors())
            return "habilidad/add";
        habilidadDao.addHabilidad(habilidad);
        return "alumno/users";
    }

    @RequestMapping(value="/update/{id_habilidad}", method = RequestMethod.GET)
    public String editHabilidad(Model model, @PathVariable int id_habilidad, HttpSession session) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("nextUrl", "redirect:habilidad/update/"+id_habilidad);
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        model.addAttribute("habilidad", habilidadDao.getIdHabilidad(id_habilidad));
        return "habilidad/update";
    }
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("habilidad") Habilidad habilidad, BindingResult bindingResult, HttpSession session, Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        session.setAttribute("nextUrl", "redirect:habilidad/update/"+habilidad.getId_habilidad());
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        session.setAttribute("alumno", alumno);
        if(!alumno.isSkp())
            return "alumno/users";
        HabilidadValidatorUpdate habilidadValidatorUpdate = new HabilidadValidatorUpdate();
        habilidadValidatorUpdate.validate(habilidad,bindingResult);
        if (bindingResult.hasErrors())
            return "habilidad/update";
        habilidadDao.updateHabilidad(habilidad);
        return "redirect:list";
    }

    @RequestMapping(value="/desactivar/{idHabilidad}")
    public String processDesactivate(@PathVariable int idHabilidad, HttpSession session, Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        session.setAttribute("nextUrl", "redirect:oferta/listpropias");
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        if(!alumno.isSkp())
            return "alumno/users";

        habilidadDao.desactivaHabilidad(idHabilidad);
        return "redirect:../list";
    }

    @RequestMapping(value="/activar/{idHabilidad}")
    public String processActivate(@PathVariable int idHabilidad, HttpSession session, Model model) {
        session.setAttribute("alumno", session.getAttribute("alumno"));
        session.setAttribute("nextUrl", "redirect:oferta/listpropias");
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if (session.getAttribute("alumno") == null)
        {
            model.addAttribute("alumno",new Alumno() );
            return "loginV2";
        }
        if(!alumno.isSkp())
            return "alumno/users";

        habilidadDao.activaHabilidad(idHabilidad);
        return "redirect:../list";
    }
}
