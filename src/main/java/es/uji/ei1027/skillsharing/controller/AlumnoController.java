package es.uji.ei1027.skillsharing.controller;

import es.uji.ei1027.skillsharing.dao.AlumnoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private AlumnoDao alumnoDao;

    @Autowired
    public void setAlumnoDao(AlumnoDao AlumnoDao){
        this.alumnoDao=alumnoDao;
    }
}