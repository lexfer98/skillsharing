package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoRegDao;
import es.uji.ei1027.skillsharing.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

class AlumnoValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Alumno.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        // Exercici: Afegeix codi per comprovar que
        // l'usuari i la contrasenya no estiguen buits
        // ...
        Alumno alumno = (Alumno) obj;
        if ((alumno.getDni().trim().equals("") )&& (alumno.getContraseña().trim().equals("") ))
            errors.rejectValue("pass", "pass",
                    "Cal introduir usuari y contrasenya");
    }
}

@Controller
public class LoginController {
    @Autowired
    private AlumnoRegDao alumnoRegDao;

    @RequestMapping("/loginV2")
    public String login(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "loginV2";
    }

    @RequestMapping(value = "/loginV2", method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute("alumno") Alumno alumno,
                             BindingResult bindingResult, HttpSession session) {

        String url = "";
        AlumnoValidator alumnoValidator = new AlumnoValidator();
        alumnoValidator.validate(alumno, bindingResult);
        if (bindingResult.hasErrors()) {
            return "loginV2";
        }
        // Comprova que el login siga correcte
        // intentant carregar les dades de l'usuari
        alumno = alumnoRegDao.loadUserByUsername(alumno.getDni(), alumno.getContraseña());
        if (alumno == null) {
            bindingResult.rejectValue("contraseña", "badpw", "Contrasenya incorrecta");
            return "loginV2";
        }
        // Autenticats correctament.
        // Guardem les dades de l'usuari autenticat a la sessió
        session.setAttribute("alumno", alumno);
        url = (session.getAttribute("nextUrl") != null ? session.getAttribute("nextUrl").toString() : "gestionUsuario/users");
        // Torna a la pàgina principal
        return url;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
