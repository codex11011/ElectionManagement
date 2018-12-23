<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>login</title>
<link type=text/css rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../css/login_btn.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<link type="text/css" rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script async defer src="https://apis.google.com/js/api.js" onload="this.onload=function(){};HandleGoogleApiLibrary()" onreadystatechange="if (this.readyState === 'complete') this.onload()"></script>
<script type="text/javascript" src="../js/HandleGoogleApi.js"></script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // Http 1.1
	response.setHeader("Pragma","no-cache"); // HTTP 1.0
	response.setHeader("Expires","0"); // Proxies
	%>
	<div class="login-wrap">
	<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Administrator</label>
				<div class="login-form">
					<div class="sign-up-htm" style="padding:10px;">
						<form action="../adminLogin" method="POST">			
							<div class="group">
								<label for="username" class="label">Username</label>
								<input id="username" type="text" class="input" size="25" name="adminUsername" required>
							</div>
							<div class="group">
								<label for="email" class="label">Email</label>
								<input id="email" type="email" class="input" size="25" name="adminEmail" required>
							</div>
							<!--  pattern=".+@lnmiit.ac.in"-->
							<div class="group">
								<label for="pass" class="label">Password</label>
								<input id="pass" type="password" class="input" data-type="password" name="adminPassword">
							</div>
							<!-- <div class="group">
								<input id="check" type="checkbox" class="check" checked>
								<label for="check"><span class="icon"></span> Keep me Signed in</label>
							</div>  -->
							<br>
							<div class="group">
								<input type="submit" class="button" value="Sign In">
							</div>
						</form>					
						<div class="hr"></div>
						<div class="foot-lnk">
						</div>
					</div>
					<div class="sign-in-htm">
					<div class="group" style="margin:100px 0px;align-content:center;">
						<div style="padding:10px">
							<div style="padding:10px;">							
								<h2><i style="position:relative;left:110px" class="icon fa fa-user-circle fa-5x"></i></h2><br>		
							</div>		
							<button id="signinButton" class="loginBtn loginBtn--google">Sign in with Google</button>
							<br><br>
							<a href="../homePage" style="text-decoration:none;border-radius:20px;padding:10px;" class="btn btn-sm btn-primary btn-block"><strong>View Candidates</strong></a>
						</div>
					</div>
						
				</div>
			</div>			
		</div>
	</div>
<script type="text/javascript" src="../js/HandleSignIn.js"></script>	
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>