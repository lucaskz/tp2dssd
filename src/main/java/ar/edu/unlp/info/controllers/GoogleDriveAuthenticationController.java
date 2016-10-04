package ar.edu.unlp.info.controllers;

import ar.edu.unlp.info.services.GoogleDriveOperationsService;
import ar.edu.unlp.info.services.GoogleDriveAuthenticationService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Ezequiel on 02/10/2016.
 */

@Controller
public class GoogleDriveAuthenticationController {


    private final GoogleDriveAuthenticationService helper;
    private final GoogleDriveOperationsService drive;

    @Autowired
    public GoogleDriveAuthenticationController(GoogleDriveAuthenticationService helper, GoogleDriveOperationsService drive) {
        this.helper=helper;
        this.drive=drive;
    }



    @RequestMapping(value = "/")
    public String getGoogleDriveLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {


        if (request.getParameter("code") == null
                || request.getParameter("state") == null) {

            model.addAttribute("URL", helper.buildLoginUrl());
            model.addAttribute("logged", true);
            session.setAttribute("state", helper.getStateToken());

        } else if (request.getParameter("code") != null && request.getParameter("state") != null && request.getParameter("state").equals(session.getAttribute("state"))) {
            session.removeAttribute("state");
            model.addAttribute("logged", false);

            try {
                helper.saveCredentials(request.getParameter("code"));
                drive.getUserFiles();
                return "/index";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/index";
    }
}
