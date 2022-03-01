package es.uji.ei1027.skillsharing.controller;


import es.uji.ei1027.skillsharing.dao.ColaboracionDao;
import es.uji.ei1027.skillsharing.dao.HabilidadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/colaboracion")
public class ColaboracionController {

    private ColaboracionDao colaboracionDao;

    @Autowired
    public void setColaboracionDao(ColaboracionDao colaboracionDao){
        this.colaboracionDao=colaboracionDao;
    }


}
