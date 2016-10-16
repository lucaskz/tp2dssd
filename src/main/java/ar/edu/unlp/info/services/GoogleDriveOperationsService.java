package ar.edu.unlp.info.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.edu.unlp.info.services.GoogleDriveAuthenticationService.getDriveService;

/**
 * Created by Ezequiel on 03/10/2016.
 */

@Service
public class GoogleDriveOperationsService {


    public List<File> getUserFiles() throws IOException {


        Drive service = getDriveService();
        FileList request = service.files().list()
                .setPageSize(30)
                .setQ("'root' in parents")
                .setFields("nextPageToken, files(id, name)")
                .execute();
     return request.getFiles();

}


}


