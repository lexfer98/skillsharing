package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.model.Alumno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("alumno") == null){
            model.addAttribute("alumno",new Alumno());
            return "index.html";
        }
        return "alumno/users";
    }
}
