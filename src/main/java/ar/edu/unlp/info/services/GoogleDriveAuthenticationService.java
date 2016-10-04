package ar.edu.unlp.info.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ezequiel on 02/10/2016.
 */

@Service
@Transactional
public class GoogleDriveAuthenticationService {


    private static final String APPLICATION_NAME ="TP2DSSD";

    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/drive-java-quickstart");


    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();


    private static HttpTransport HTTP_TRANSPORT;

    private static final List<String> SCOPES =
            Arrays.asList(DriveScopes.DRIVE);

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static GoogleClientSecrets CLIENT_SECRETS;

    private static Credential CREDENTIAL;


    private static final String CALLBACK_URI = "https://tp2dssd.herokuapp.com/";

    private String stateToken;

    private final GoogleAuthorizationCodeFlow flow;

    public GoogleDriveAuthenticationService() throws IOException {
        try {
            InputStream in = GoogleDriveAuthenticationService.class.getResourceAsStream("/client_secret.json");
            CLIENT_SECRETS=GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, CLIENT_SECRETS, SCOPES).setAccessType("offline").setApprovalPrompt("force").build();
        generateStateToken();

    }



    /**
     * Builds a login URL based on client ID, secret, callback URI, and scope
     */
    public String buildLoginUrl() {

        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();

        return url.setRedirectUri(CALLBACK_URI).setState(stateToken).build();
    }

    /**
     * Generates a secure state token
     */
    private void generateStateToken(){
        SecureRandom sr1 = new SecureRandom();
        stateToken = "google;"+sr1.nextInt();
    }

    /**s
     * Accessor for state token
     */
    public String getStateToken(){
        return stateToken;
    }

    /**
     * Expects an Authentication Code, and makes an authenticated request for the user's profile information
     * * @param authCode authentication code provided by google
     */
    public void saveCredentials(final String authCode) throws IOException {

        GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
        CREDENTIAL = flow.createAndStoreCredential(response, null);
        System.out.println(" Credential access token is "+CREDENTIAL.getAccessToken());
        System.out.println("Credential refresh token is "+CREDENTIAL.getRefreshToken());

    }

    public static Drive getDriveService() throws IOException {
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, CREDENTIAL)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    public Credential getCredential () {

        return CREDENTIAL;
    }

    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    public static JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }

    public static HttpTransport getHttpTransport() {
        return HTTP_TRANSPORT;
    }
}
