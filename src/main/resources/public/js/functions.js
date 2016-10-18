init = function() {
	s = new gapi.drive.share.ShareClient();
	s.setOAuthToken($('#token').val());
}
window.onload = function() {
	gapi.load('drive-share', init);
}

share = function(id){
	s.setItemIds([id]);
	s.showSettingsDialog()
}