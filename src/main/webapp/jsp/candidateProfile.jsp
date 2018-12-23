<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/meta.jsp" %>	
<title>Candidate Form</title>
<%@ include file="../partials/header.jsp" %>
<link type="text/css" rel="stylesheet" href="../css/navbar_doom.css">
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
</style>
<script type="text/javascript" src="../js/HandleSignOut.js"></script>  
</head>
<body>
<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // Http 1.1
		response.setHeader("Pragma","no-cache"); // HTTP 1.0
		response.setHeader("Expires","0"); // Proxies
		
		String domain = (String)session.getAttribute("domain");
		String userName = (String) session.getAttribute("username"); 
		String familyName = (String) session.getAttribute("familyname");
		String email = (String) session.getAttribute("email");
		
		
		boolean isRegistered = (boolean) session.getAttribute("isRegistered");

		if(userName == null){
			response.sendRedirect("login.jsp");
		}else{
			userName = userName.toLowerCase();
			familyName = familyName.toLowerCase();				
		}
		//domain != "lnmiit.ac.in" &&
			
%>
	
<div class="page" style="position:absolute;top:0;left:0;right:0;">
  <span class="menu_toggle">
    <i class="menu_open fa fa-bars fa-lg"></i>
    <i class="menu_close fa fa-times fa-lg"></i>
  </span>
  <ul class="menu_items">
    <li><a href="candidateProfile.jsp"><i class="icon fa fa-user fa-2x"></i> Profile</a></li>
    <li><a href="CandidateForm.jsp"><i class="icon fa fa-list-ul fa-2x"></i> Form</a></li>
    <li><a href="#" onclick="signoutApp()"><i class="icon fa fa-sign-out fa-2x"></i> Logout</a></li>
  </ul>
  
  
  



  <main class="content">
    <div class="content_inner">
				<div  class="form-style-Bitter">
			<h2>Profile<span><% out.println(domain); %></span></h2>
				<div class="container" style="text-align:center;width:50%">
				<c:choose>
					<c:when test="${!isRegistered}">
						<h3>you need to register first</h3>
					</c:when>				
				</c:choose>
				</div><br>
				<div class="section"><span style="position:relative;padding:10px;"><i class="fa fa-list-ul" aria-hidden="true"></i>
				</span>&nbsp; Details</div>
				<div class="inner-wrap">
					<div class="container-fluid">
		      			<h3>${candidate.firstName} ${candidate.familyName}</h3>
		      			<br>
				      	<table class="table table-striped">
						  <tbody>
						    <tr>
						      <th scope="row">Email Id</th>
						      <td>${candidate.email}</td>
						    </tr>
						    <tr>
						      <th scope="row">Phone Number</th>
						      <td>${candidate.phoneNum}</td>
						    </tr>
						    <tr>
						      <th scope="row">Hostel</th>
						      <td>${candidate.hostel}</td>
						    </tr>
						    <tr>
						      <th scope="row">Room Number</th>
						      <td>${candidate.room}</td>
						    </tr>
						    <tr>
						      <th scope="row">Batch</th>
						      <td>${candidate.batch}</td>
						    </tr>
						    <tr>
						      <th scope="row">Branch</th>
						      <td>${candidate.branch}</td>
						    </tr>
						    <tr>
						      <th scope="row">CGPA</th>
						      <td>${candidate.cgpa}</td>
						    </tr>
						    <tr>
						      <th scope="row">Position</th>
						      <td>${candidate.position}</td>
						    </tr>
						    <tr>
						      <th scope="row">manifesto Link</th>
						      <td><a href="${candidate.manifestoId}">${candidate.manifestoId}</a></td>
						    </tr> 
						  </tbody>
						</table>
						<br><br>   			
					</div>
				</div>
				<c:choose>
					<c:when test="${isRegistered}">
					<form action="../candidateProfile/edit" method="get">
					<div class="innder-wrap">
					    <input id="submit" type="submit" value="Edit Details">		
					</div>
					</form>									
					</c:when>
				</c:choose>						
			</div>

   		 </div>
  </main>
</div>

<%@ include file="../partials/footer.jsp" %>	
<script type="text/javascript" src="../js/navbar_doom.js"></script>
<script type="text/javascript">

</script>
</body>
</html>