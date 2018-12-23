function signInCallback() {
	gapi.client.request({ path: 'https://www.googleapis.com/plus/v1/people/me' }).then(
			function(success) {
				// API call is successful
				user_info = JSON.parse(success.body);
				// user profile information
				
			},
			function(error) {
				// Error occurred
				 console.log(error);
				 return null;
			}
		);
}
	 $('#signinButton').click(function() {
		// API call for Google login
			gapi.auth2.getAuthInstance().signIn().then(
				function(success) {
				
					console.log(success);
					gapi.client.request({ path: 'https://www.googleapis.com/plus/v1/people/me' }).then(
							function(success) {
								// API call is successful
								var user_info = JSON.parse(success.body);
								// user profile information
								console.log(JSON.stringify(user_info));
								$.ajax({
									type:"POST",
									url:"http://localhost:8080/WebServer/candidateLogin",
									contentType:"application/json",  
									data:JSON.stringify(user_info),
									success:function(result,textStatus){
											console.log("working");
											if(user_info.domain === "lnmiit.ac.in"){
												window.location = "http://localhost:8080/WebServer/jsp/CandidateForm.jsp";
											}else{
												window.location = "http://localhost:8080/WebServer/jsp/signOutRedirect.jsp";
											}
									
											
									}
									
								});
							},
							function(error) {
								// Error occurred
								 console.log(error);
								 return null;
							}
						);
					
				
				},
				function(error) {
					// Error occurred
					console.log(error);
				}
	 );
	});
		
	
