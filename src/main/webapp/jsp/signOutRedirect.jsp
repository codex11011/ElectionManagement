<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta Http-Equiv="Cache-Control" Content="no-cache">
<meta Http-Equiv="Pragma" Content="no-cache">
<meta Http-Equiv="Expires" Content="0">

<title>Insert title here</title>

</head>
<body onload="setTimeout(signoutRedirect,5000)">
<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // Http 1.1
		response.setHeader("Pragma","no-cache"); // HTTP 1.0
		response.setHeader("Expires","0"); // Proxies
		String userName = (String) session.getAttribute("username"); 
		String familyName = (String) session.getAttribute("familyname");
		String email = (String) session.getAttribute("email");
		
		
		
			
%>
	
	<h1>Please signin with your lnmiit email id</h1>
	<h3>Redirecting to login page in 5 seconds...</h3>
<script>
function signoutRedirect() {
	  console.log("worked");
	  document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/WebServer/candidateLogout";		
}
</script>
</body>
</html>