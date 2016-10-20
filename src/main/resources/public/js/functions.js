 var CLIENT_ID = '596165206145-2565vba79b77agspv0kugksicla8qh0e.apps.googleusercontent.com';
 var API_KEY= 'AIzaSyAlqYcVM3s8kP_1K67TnbXORAcs9fltCPM';
 var SCOPES = [
          'https://www.googleapis.com/auth/drive.file',
          'email',
          'profile',
          // Add other scopes needed by your application.
  ];
 
 


var init = function() {
	s = new gapi.drive.share.ShareClient();
	s.setOAuthToken($('#token').val());
}
window.onload = function() {
	gapi.load('drive-share', init);
}

var share = function(id){
    gapi.client.setApiKey(API_KEY);
    checkAuth();
	s.setItemIds([id]);
	s.showSettingsDialog();
}

var showCrearDocumento = function(p){
	if(p.signed_in){
		$("#myModal").modal();
	}
}

var crearDocumento = function(){
//	checkAuth(showCrearDocumento);
	$("#myModal").modal();
}

function handleAuthResult(authResult) {
        if (authResult) {
          // Access token has been successfully retrieved, requests can be sent to the API
        } else {
          // No access token could be retrieved, force the authorization flow.
          gapi.auth.authorize(
              {'client_id': CLIENT_ID, 'scope': SCOPES, 'immediate': false},
              handleAuthResult);
        }
}

var checkAuth = function(callback) {
     gapi.auth.setToken({
            access_token: $('#token').val()
    });
    return gapi.auth.authorize(
        {'client_id': CLIENT_ID, 'scope': SCOPES, 'immediate': false,'approval_prompt':'auto'},
        callback);
  }