package ar.edu.unlp.info.controllers;

import ar.edu.unlp.info.services.GoogleDriveAuthenticationService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Ezequiel on 02/10/2016.
 */

@Controller
public class GoogleDriveAuthenticationController {


    private final GoogleDriveAuthenticationService helper;

    @Autowired
    public GoogleDriveAuthenticationController(GoogleDriveAuthenticationService helper) {
        this.helper=helper;
    }



    @RequestMapping(value = {"/","/index"})
    public String getGoogleDriveLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {


        if (helper.getCredential()!= null) {
            String accessToken = helper.getCredential().getAccessToken();
            RestTemplate restTemplate = new RestTemplate();
            try {
                String jsonResponse = restTemplate.getForObject("https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken, String.class);
                JSONObject jsonObject= new JSONObject(jsonResponse);
                try {
                    String tokenError=jsonObject.getString("aud");
                }
                catch (JSONException e) {
                    session.removeAttribute("logged");
                }
            }
            catch (HttpClientErrorException e) {
                session.removeAttribute("logged");
            }

        }

        if (request.getParameter("code") == null || request.getParameter("state") == null) {

            model.addAttribute("URL", helper.buildLoginUrl());
            session.setAttribute("state", helper.getStateToken());

        } else if (request.getParameter("code") != null && request.getParameter("state") != null && request.getParameter("state").equals(session.getAttribute("state"))) {
            session.removeAttribute("state");

            try {
                helper.saveCredentials(request.getParameter("code"));
                session.setAttribute("logged", true);
                return "index";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }

    @RequestMapping (value="/revoketoken")
    public String revokeToken (HttpSession session) {
        if(helper.getCredential()!=null) {
            String accessToken = helper.getCredential().getAccessToken();
            RestTemplate restTemplate = new RestTemplate();
            try {
                String jsonResponse = restTemplate.getForObject("https://accounts.google.com/o/oauth2/revoke?token=" + accessToken, String.class);
                session.removeAttribute("logged");
            } catch (HttpClientErrorException e) {

            }

            return "redirect:/index";
        }
        session.removeAttribute("logged");
        return "index";
    }
}
