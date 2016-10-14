package ar.edu.unlp.info.controllers;

import ar.edu.unlp.info.services.GoogleDriveOperationsService;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ezequiel on 04/10/2016.
 */

@Controller
public class GoogleDriveOperationsController {

    private final GoogleDriveOperationsService drive;

    @Autowired
    public  GoogleDriveOperationsController(GoogleDriveOperationsService drive){
          this.drive=drive;
    }

    @RequestMapping(value = "/listMyFiles", method= RequestMethod.GET)
    public  String  getMyFiles(Model model) throws IOException {

        List<File> files = drive.getUserFiles("root");
        model.addAttribute("MyFiles",files);
        return  "files";

    }

   /* @RequestMapping(method = RequestMethod.GET, value = "/file/{identifier}")
    public  @ResponseBody File  getFileById (@PathVariable String identifier) throws IOException {

           // return  drive.getFileByIdentifier(identifier);

    }
*/


}
