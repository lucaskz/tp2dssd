package ar.edu.unlp.info.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static ar.edu.unlp.info.services.GoogleDriveAuthenticationService.getDriveService;

/**
 * Created by Ezequiel on 03/10/2016.
 */

@Service
public class GoogleDriveOperationsService {

    public static void parseDateTime (String date) {

    }


    public List<File> getUserFiles() throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();
        FileList request = service.files().list()
                .setPageSize(30)
                .setQ("'root' in parents")
                .setFields("nextPageToken, files(id, name,modifiedTime,mimeType,shared,ownedByMe)")
                .execute();

        return request.getFiles();

    }

    public List<File> getFiles(Integer id) throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();
        FileList request = service.files().list()
                .setPageSize(30)
                .setQ(id + " in parents")
                .setFields("nextPageToken, files(id, name,modifiedTime,mimeType,shared,ownedByMe)")
                .execute();
        return request.getFiles();

    }

    public List<File> getFileByIdentifier(String identifier) throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();
        FileList request = service.files().list()
                .setPageSize(30)
                .setQ("'" + identifier + "' in parents")
                .setFields("nextPageToken, files(id, name,modifiedTime,mimeType,shared,ownedByMe)")
                .execute();

        return request.getFiles();
    }

    public void createDocument(String nombre, String folderID) throws IOException {
        Drive service = getDriveService();
        File fileMetadata = new File();
        fileMetadata.setName(nombre);
        if (folderID!="")
            fileMetadata.setParents(Collections.singletonList(folderID));
        fileMetadata.setMimeType("application/vnd.google-apps.document");
        File file = service.files().create(fileMetadata)
                .setFields("id")
                .execute();

    }
}





