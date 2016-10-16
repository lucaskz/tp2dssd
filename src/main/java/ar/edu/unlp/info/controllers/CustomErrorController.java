package ar.edu.unlp.info.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ezequiel on 13/10/2016.
 */

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    // Error page
    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, Model model) {
        Integer statusCode= (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = "Error interno";
        }
        else if (statusCode==null) {
            errorMessage="Error interno";
        }
        else if(statusCode==401) {
            errorMessage="Acceso no autorizado";
        }
        else if (statusCode==404) {
            errorMessage="Página no encontrada";
        }
        else if (statusCode==403) {
            errorMessage="Acceso prohibido";
        }
        model.addAttribute("errorCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

}
