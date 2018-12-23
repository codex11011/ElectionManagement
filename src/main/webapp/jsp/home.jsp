<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html>
<html>
<head>
<title>Candidate List</title>
<!-- Compiled and minified CSS -->
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
.form-style-Bitter{
	width:1000px;
}
.changeColor :hover{
	background-color:rgb(228,234,239);
}
.section{
background-color:rgb(240,240,240);
padding-bottom:20px;
border-radius:30px;
margin-bottom:0px;

}

.section:hover{
	background-color:rgb(232,234,237);
	
}

</style>
</head>
<body>
	<!--background-color:#ffe0e7aa;-->
<%
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); // Http 1.1
		response.setHeader("Pragma","no-cache"); // HTTP 1.0
		response.setHeader("Expires","0"); // Proxies
		
	
		
%>

  
  	
  <main class="content">	 	
    <div class="content_inner" style="padding:50px 150px;">
    	<% int i = 1; %>
		<div  class="form-style-Bitter">
			<h2 style="margin-bottom:20px;">Candidate List - accepted<span><% out.println("lnmiit.ac.in"); %></span></h2>
			<div class="row" style="margin-bottom:20px;">
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Name</div>
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Id</div>
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Position</div>    				
    				<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Batch</div>
	    	</div>
			<c:forEach items="${candidates}" var="n">
			<div>			
    		<div class="section decline" data-toggle="modal" data-target=".<%out.println(i);%>" style="">
    			<span style="position:relative;top:20px;"><% out.println(i); %></span>
    			<div class="row">
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">${n.firstName} ${n.familyName}</div>
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">${n.email}</div>
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">${n.position}</div>    				
    				<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">${n.batch}</div>
	    		</div>
    		</div>
    		
				<div class="modal fade <% out.println(i); %>" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg">
				    <div class="modal-content">
				      <div class="container-fluid">
				      	<h3>${n.firstName} ${n.familyName}</h3>
				      	<br>
				      	<table class="table table-striped">
						  <tbody>
						    <tr>
						      <th scope="row">Name</th>
						      <td>${n.firstName} ${n.familyName}</td>
						    </tr>
						    <tr>
						      <th scope="row">Email Id</th>
						      <td>${n.email}</td>
						    </tr>
						    <tr>
						      <th scope="row">Phone Number</th>
						      <td>${n.phoneNum}</td>
						    </tr>
						    <tr>
						      <th scope="row">Room Number</th>
						      <td>${n.room}</td>
						    </tr>
						    <tr>
						      <th scope="row">Batch</th>
						      <td>${n.batch}</td>
						    </tr>
						    <tr>
						      <th scope="row">Branch</th>
						      <td>${n.branch}</td>
						    </tr>
						    <tr>
						      <th scope="row">Cgpa</th>
						      <td>${n.cgpa}</td>
						    </tr>
						    <tr> 
						      <th scope="row">Position</th>
						      <td>${n.position}</td>
						    </tr>
						    <tr> 
						      <th scope="row">Status</th>
						      <td>
						      <strong>accepted</strong> 
						      </td>
						    </tr>
						  </tbody>
						</table>
						<br><br>
				      </div>
				    </div>
				  </div>
				</div>
	    		<% i++; %>
			</div>
    		<br><hr><br>
			</c:forEach>	
	    	
	    			
		</div>
	
    </div>
  </main>
</div>
	

	
	
<%@ include file="../partials/footer.jsp" %>	
</body>
</html>
