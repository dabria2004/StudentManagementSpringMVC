<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/base.css" var="baseCss" />
<spring:url value="/resources/css/container.css" var="containerCss" />
<spring:url value="/resources/js/accordian-menu.js" var="accordianjs" />
<spring:url value="/resources/js/general.js" var="generaljs" />

<link href="${baseCss}" rel="stylesheet" />
<link href="${containerCss}" rel="stylesheet" />
<script src="${accordianjs}"></script>
<script src="${generaljs}"></script>

<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body class="main_body" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
	
	<div id="header">
    <div id="title">
    	<u>Student Registration Assignment</u>     
    </div> 
    </div>

	<div id="container"> 
	<div id="main_contents">     
	<div style="margin-left:35%">
	<form action="/StudentManagementSpring/finalexam" method="post" name="confirm">
		<br/>  
			<table class="sortName">
        <tr>
        	<td colspan="2" align="center"><h2>Login</h2></td>
		</tr>
		                
		<tr align="left">
			<td>User Id</td>
			<td><input name="userid" /></td>
		</tr>
		
		<tr align="left">
			<td>Password</td>
			<td><input type="password" name="userpassword" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input name="Submit" type="submit" value="Login" class="button"/></td>
		</tr>
	</table>
	
	</form>  
	<div style="color:red;">${loginfail}</div> 
	</div>  
	</div> <!-- end of main contents --> 
    </div><!-- end of container -->
    
    <div class="footer">    
        <span>Copyright &#169; ACE Inspiration 2016</span>
    </div> 
</body>
</html>