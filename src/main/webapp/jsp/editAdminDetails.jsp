<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/meta.jsp" %>	
<title>Profile</title>
<%@ include file="../partials/header.jsp" %>
<link type="text/css" rel="stylesheet" href="../css/candidateForm.css">
<style>
.menu_items a:hover .icon:after {
  border-color: #2A88AD;
}
.menu_items a:active .icon {
  color: #2A88AD;
}
.menu_toggle{
		  background-color: #2A88AD;
}

.registrationbtn{
	background-color: #2A88AD;
}

.registrationbtn:hover {
	background-color: #2A6881;
}

</style>
</head>
<body style="background-color:#efefef;">
<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // Http 1.1
		response.setHeader("Pragma","no-cache"); // HTTP 1.0
		response.setHeader("Expires","0"); // Proxies
		
		String adminUsername = (String) session.getAttribute("adminUsername");
		String adminEmail = (String) session.getAttribute("adminEmail");
		
		if(adminUsername == null){
			response.sendRedirect("login.jsp");
		}
		//domain != "lnmiit.ac.in" &&
			
%>
<div class="container">
  <main class="content">
    <div class="content_inner">
		<form class="form-style-Bitter" style="margin-top:50px;margin-bottom:50px;" action="../adminProfile/Save" method="post">
			<h2>Edit Profile<span>lnmiit.ac.in</span></h2>
			
				<div class="section"><span style="position:relative">1</span>Name</div>
				<div class="inner-wrap">
	        		<label for="fname">First Name <br><input id="fname" type="text" name="adminFirstName" value="${adminDetails.firstName}"/></label><br>
	        		<label for="faname">Family Name <br><input id="faname" type="text" name="adminFamilyName" value="${adminDetails.familyName}"/></label>
    			</div>
    			<div class="section"><span style="position:relative">2</span>Account</div>
    			<div class="inner-wrap">
	        		<label for="adminUsername">User Name <br><input id="adminUsername" type="text" name="adminUsername" value="${adminDetails.userName}"/></label>
    				<label for="email">Email <br><input id="email" type="text" name="adminEmail" value="${adminDetails.email}"/></label>
    			</div>
				<br><br>
				 <div class="row">
					<div class="col-md-6 col-sm-6 col-xs-6">
							<input type="hidden" name="prevEmail" value="${adminEmail}">
							<input type="submit" class="registrationbtn" style="border-radius:20px;outline:0px;" value="Save">
					</div>	<!-- background-color:#2A88AD  -->
					<div class="col-md-6 col-sm-6 col-xs-6">
							<a href="../adminProfile" class="btn btn-primary btn-block registrationbtn" style="border-radius:20px;outline:0px;color:white;text-decoration:none;">Cancel</a>
					</div>	
				</div>
				<br><br>
		     </form>
		</div>  	
  </main>
</div>
 <%@ include file="../partials/footer.jsp" %>	
<script type="text/javascript" src="../js/navbar_doom.js"></script>
</body>
</html>














	