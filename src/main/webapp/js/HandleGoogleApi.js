  function HandleGoogleApiLibrary() {
      gapi.load('client:auth2',  {
  		callback: function() {
  			// Initialize client & auth libraries
  			gapi.client.init({
  		    	apiKey: 'AIzaSyBwrPAV1I8OtVzr9S4ebyoYYMQXllyULE4',
  		    	clientId: '106505139826-7ghat02qa5eq0dhmpctbvoq3kjpiseni.apps.googleusercontent.com',
  		    	scope: 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.me'
  			}).then(
  				function(success) {
  					console.log(success);
  			  		// Libraries are initialized successfully
  		  			// You can now make API calls
  				}, 
  				function(error) {
  					// Error occurred
  					console.log(error);
  			  	}
  			);
  		},
  		onerror: function() {
  			// Failed to load libraries
  		}
  	});
 }