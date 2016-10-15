var share = function(id) {
    s = new gapi.drive.share.ShareClient();
    s.setOAuthToken($('#token').val());
    s.setItemIds([id]);
    s.showSettingsDialog()
}

window.onload = function() {
    gapi.load('drive-share');
}
