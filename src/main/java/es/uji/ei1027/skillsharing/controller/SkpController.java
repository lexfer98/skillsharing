package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/skp")
public class SkpController {

    @RequestMapping("/menu")
    public String menuSkp(HttpSession session, Model model){
        Alumno alumno = (Alumno) session.getAttribute("alumno");
        if ( alumno == null){
            model.addAttribute("alumno",new Alumno());
            return "loginV2";
        }
        else if (!alumno.isSkp())
            return "/alumno/users";
        return "skp/menu";
    }

}
