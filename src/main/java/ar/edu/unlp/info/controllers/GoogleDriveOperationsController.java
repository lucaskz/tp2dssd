package ar.edu.unlp.info.controllers;

import ar.edu.unlp.info.services.GoogleDriveOperationsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Ezequiel on 04/10/2016.
 */

@RestController
public class GoogleDriveOperationsController {

    @RequestMapping(value = "/listfiles")
    public void getFiles () throws IOException {
        GoogleDriveOperationsService.getUserFiles();
    }
}
