package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.UserDao;
import es.uji.ei1027.skillsharing.model.UserDetails;
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

class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        // Exercici: Afegeix codi per comprovar que
        // l'usuari i la contrasenya no estiguen buits
        UserDetails user = (UserDetails) obj;
        if (user.getUsername().trim().equals("")) {
            errors.rejectValue("username", "obligatorio",
                    "Debes introducir un valor");
        }
        if(user.getPassword().trim().equals("")) {
            errors.rejectValue("password", "obligatoria",
                    "Debes introducir un valor");
        }
    }
}

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(Model model, HttpSession session) {
        if(session.getAttribute("user")!=null){
            UserDetails user = (UserDetails) session.getAttribute("user");
            if (user.getTipo().equals("alumno")){
                return "redirect:/gestionUsuario/users";
            }
            else {
                    return "redirect:/admin";
                }
            }

        model.addAttribute("user",new UserDetails());
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator=new UserValidator();
        if (bindingResult.hasErrors()) {
            return "login";
        }
        // Comprova que el login siga correcte
        // intentant carregar les dades de l'usuari
        user = userDao.loadUserByUsername(user.getUsername(), user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw","Contraseña incorrecta");
            return "login";
        }
        // Autenticats correctament.
        // Guardem les dades de l'usuari autenticat a la sessió
        String url = (String) session.getAttribute("nextUrl");
        session.setAttribute("user", user);

        // Torna a la pàgina principal
        //return "redirect:/";
        if (url != null)
            return "redirect:/" + url;
        if (user.getTipo().equals("alumno")) {
            return "gestionUsuario/users";
        } else {
                return "admin";
            }
        }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
