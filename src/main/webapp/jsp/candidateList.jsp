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
		
		if(session.getAttribute("adminUsername") == null){
			response.sendRedirect("login.jsp");
		} 
		
%>
<div class="page" style="position:absolute;top:0;left:0;right:0;">
  <span class="menu_toggle">
    <i class="menu_open fa fa-bars fa-lg"></i>
    <i class="menu_close fa fa-times fa-lg"></i>
  </span>
  <ul class="menu_items">
    <li><a href="../adminProfile"><i class="icon fa fa-user fa-2x"></i> Profile</a></li>
    <li><a href="../candidateList"><i class="icon fa fa-list-ul fa-2x"></i> List</a></li>
    <li><a href="../adminLogout"><i class="icon fa fa-sign-out fa-2x"></i> Logout</a></li>
  </ul>
  
  	
  <main class="content">
  	<div class="container-fluid" style="padding:10px;">
  		<div class="container" style="width:900px;">  		
  		<div class="row" style="color:white;background-color:rgb(88, 78, 74);border:solid 3px #675f6b;margin:20px;padding:20px;border-radius:50px;">
  			<div class="col-md-2"></div>
  			<form>
  			<div class="col-md-2" style="text-align:center;"><label class="radio-inline"><input type="radio" name="optradio" style="visibility:hidden;" class="all" onclick="handleReq('all')" checked><strong>all</strong></label></div>
  			<div class="col-md-2" style="text-align:center;"><label class="radio-inline"><input type="radio" name="optradio" style="visibility:hidden;" class="accepted" onclick="handleReq('1')"><strong>accepted</strong></label></div>
  			<div class="col-md-2" style="text-align:center;"><label class="radio-inline"><input type="radio" name="optradio" style="visibility:hidden;" class="pending" onclick="handleReq('0')"><strong>pending</strong></label></div>
  			<div class="col-md-2" style="text-align:center;"><label class="radio-inline"><input type="radio" name="optradio" style="visibility:hidden;" class="rejected" onclick="handleReq('-1')"><strong>rejected</strong></label></div>
  			</form>
  			<script>
  				function handleReq(val){
  					$.ajax({
						type:"POST",
						url:"http://localhost:8080/WebServer/candidateFilter",
						contentType:"application/json",  
						data:val,
						success:function(result,textStatus){
								console.log("working");
								window.location = "http://localhost:8080/WebServer/jsp/candidateList.jsp";
								
						}
						
					});
  				};
  			</script>
  		<div class="col-md-2"></div>
  		</div>
  		</div>
  	</div>	 	
    <div class="content_inner" style="padding:50px 150px;">
    	<% int i = 1; %>
		<div  class="form-style-Bitter">
			<h2 style="margin-bottom:20px;">Candidate List - ${filterBtn}<span><% out.println("lnmiit.ac.in"); %></span></h2>
			<div class="row" style="margin-bottom:20px;">
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Name</div>
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Id</div>
	    			<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Position</div>    				
    				<div class="col-md-3 col-sm-3 col-xs-3" style="text-align:center;">Batch</div>
	    	</div>
			<c:forEach items	="${candidates}" var="n">
			<div>			
    		<div class="section decline" data-toggle="modal" data-target=".<%out.println(i);%>" style="">
    			<c:choose>
		      		<c:when test="${n.status == 0}"><span style="position:relative;top:20px;box-shadow: 0 0 0 3px #FCEC12"><% out.println(i); %></span></c:when>
		      		<c:when test="${n.status == 1}"><span style="position:relative;top:20px;box-shadow: 0 0 0 3px #70E100;"><% out.println(i); %></span></c:when>
		      		<c:when test="${n.status == -1}"><span style="position:relative;top:20px;box-shadow: 0 0 0 3px coral;"><% out.println(i); %></span></c:when>
		      </c:choose>
    			
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
						      <c:choose>
						      		<c:when test="${n.status == 0}"><strong>pending</strong></c:when>
						      		<c:when test="${n.status == 1}"><strong>accepted</strong></c:when>
						      		<c:when test="${n.status == -1}"><strong>rejected</strong></c:when>
						      </c:choose> 
						      </td>
						    </tr>
						  </tbody>
						</table>
						<br><br>
						 <div class="row">
							<div class="col-md-6 col-sm-6 col-xs-6">
								<form action="../candidateRequest/accept" method="post" onsubmit="reloadLocation()">
									<input type="hidden" name="email" value="${n.email}">
									<input type="hidden" name="status" value="${n.status}"> 
									<button class="btn btn-primary btn-md btn-block" style="border-radius:20px;outline:0px;" class="accept<%out.println(i);%>">Accept</button>
								</form>
							</div>	
							<div class="col-md-6 col-sm-6 col-xs-6">
								<form action="../candidateRequest/reject" method="post" onsubmit="reloadLocation()">
									<input type="hidden" name="email" value="${n.email}">
									<input type="hidden" name="status" value="${n.status}">
									<button class="btn btn-primary btn-md btn-block" style="border-radius:20px;outline:0px;" class="decline<%out.println(i);%>">Reject</button>
								</form>
							</div>	
						</div>
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

<script type="text/javascript" src="../js/navbar_doom.js"></script>
<script>
	function reloadLocation(){
		setTimeout(function(){location.reload(true);},1000);
	};
</script>
</body>
</html>
