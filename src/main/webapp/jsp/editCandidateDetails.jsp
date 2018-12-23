<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/meta.jsp" %>	
<title>Candidate Form</title>
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
		
		String domain = (String)session.getAttribute("domain");
		String userName = (String) session.getAttribute("username"); 
		String familyName = (String) session.getAttribute("familyname");
		String email = (String) session.getAttribute("email");
		String batch = "Y" + email.substring(0,2);
		String temp = email.substring(3,5);
		
		String branch = null;
		
		switch(temp)	{
		
		case "cs":	branch = "CSE";break;
		
		case "cc":	branch = "CCE";break;
		
		case "ec":	branch = "ECE";break;
		
		case "me":	branch = "ME";break;
		
		case "mm": branch = "MTRE";break;
		
		}
		
		boolean isRegistered = (boolean) session.getAttribute("isRegistered");
		if(userName == null){
			response.sendRedirect("login.jsp");
		}else{
			userName = userName.toLowerCase();
			familyName = familyName.toLowerCase();				
		}
		//domain != "lnmiit.ac.in" &&
			
%>
	
<div class="container">
 
  <main class="content">
    <div class="content_inner" style="margin:50px auto;">
    	
		<form autocomplete="off" class="form-style-Bitter" action="../candidateProfile/Save" method="post">
			<h2>Candidate Form<span><% out.println(domain); %></span></h2>
			<div class="section"><span style="position:relative">1</span>Name</div>
				<div class="inner-wrap">
	        		<label for="fname">First Name <br><input id="fname" type="text" name="firstName" value="<% out.println(userName); %>" readonly/></label><br>
	        		<label for="faname">Family Name <br><input id="faname" type="text" name="familyName" value="<% out.println(familyName); %>" readonly/></label>
    			</div>
    			<div class="section"><span style="position:relative">2</span>Personal Detail's</div>
				<div class="inner-wrap">                                                      <!-- oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" maxlength="25" pattern=".+@lnmiit.ac.in" required title="eg:- 16ucs001@lnmiit.ac.in "  -->
	        		<label for="email">Email<br><input id="email" type="text" name="email" value="<% out.println(email); %>" readonly/></label><br>
	        		<label for="Pnumber">Phone Number <span>+91</span><br><input id="Pnumber" type="number" name="PhoneNumber" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" pattern="\d*" maxlength="10" required title="10 digits required" value="${candidate.phoneNum}"/></label><br>
    				<label for="hostel">Hostel<br>
    					<select style="border-radius:20px;" id="hostel" name="hostel" value="${candidate.hostel}" required>
    						<option value="" disabled="disabled" selected="selected">select hostel</option>
    						<option value="BH1">BH 1</option>
    						<option value="BH2">BH 2</option>
    						<option value="BH3">BH 3</option>
    						<option value="GH">GH</option>
    						<option value="Guest House">Guest House</option>
    					</select>
    				</label><br>
    				<label for="roomNum">Room number<br><!--  (S|D|NS|ND|A|B|C|D)*-[0-9]{3}-->
    					<input type="text" maxlength="6"id="roomNum" value="${candidate.room}" name="roomNumber" pattern="([a-zA-Z]*-[0-9]{3}|[0-9]{3})" title="format:- ns-703 or A-230 or 102 ">
    				</label>
    			</div>
    			<div class="section"><span style="position:relative">3</span>Academic Info</div>
    			<div class="inner-wrap">
    				<label for="batch">Batch<br>
    				<input id="batch" type="text" name="batch" style="border-radius:20px;" value="<% out.println(batch); %>" readonly/>
    				</label><br>
    				<label for="branch">Branch<br>
    				<input id="branch" type="text" name="branch" style="border-radius:20px;" value="<% out.println(branch); %>" readonly/>
    				</label><br>
    				<label for="cgpa">CGPA &nbsp;(current)<br><input id="cgpa" value="${candidate.cgpa}" name="cgpa" type="number" step="any" max="10" min="0" required>
    				</label>
    				</div>
    			
	    	<div class="section"><span style="position:relative">4</span>Other Info</div>
	    	<div class="inner-wrap">
	    		<label>Position<br>
	    		<select style="border-radius:20px;" id="position" name="position" value="${candidate.position}" required>
    					<option value="" disabled="disabled" selected="selected">Please select position you are applying for</option>
    					<option value="President">President</option>
					  	<option value="Vice-President">Vice-President</option>
					  	<option value="GSEC-Sports">GSEC-Sports</option>
					  	<option value="GSEC-Science and Tech.">GSEC-Science and Tech.</option>
					  	<option value="GSEC-Cultural">GSEC-Cultural</option>
    				</select>
    			</label><br>			
	    	</div>
	    	
	    	<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-6">
							<input type="hidden" name="prevEmail" value="${email}">
							<input type="submit" class="registrationbtn" style="border-radius:20px;outline:0px;" value="Save">
					</div>	<!-- background-color:#2A88AD  -->
					<div class="col-md-6 col-sm-6 col-xs-6">
							<a href="../candidateProfile" class="btn btn-primary btn-block registrationbtn" style="border-radius:20px;outline:0px;color:white;text-decoration:none;">Cancel</a>
					</div>	
				</div>
	    			
		</form>
	
    </div>
  </main>
</div>

<%@ include file="../partials/footer.jsp" %>	
</body>
</html>