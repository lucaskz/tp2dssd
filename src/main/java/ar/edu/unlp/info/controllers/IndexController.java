package ar.edu.unlp.info.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ezequiel on 29/09/2016.
 */

@Controller
public class IndexController {

    @RequestMapping(value="/")
    public String index(ModelAndView modelAndView){
        System.out.println("Home Page");
        return "/index";
    }
}
