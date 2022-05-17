<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Final_exam.jsp</title>
<script type="text/javascript">
    window.onload=function()
    {
        menu();  
    }
</script>
</head>
<body class="main_body">
	<div id="header">
	<div id="title">
		<a href="/StudentManagementSpring/finalexampage">Student Registration Assignment</a>
	</div>
	<div id="menuLoginTime">
	<table>
		<tr>
			<td>User</td>
			<td>${sessionScope.userInfo.user_id}
				${sessionScope.userInfo.user_name}</td> 
		</tr>
		<tr>
			<td>Current Date</td>
			<td>${sessionScope.date}</td>
		</tr>
	</table>
	</div>
		<a href="/StudentManagementSpring/logout"><input id="btn_logout" class="button" type="submit" value="Logout"></a>   
	</div>
	<div id="container"> 
	<div id="left_menu">
		<!-- menu html code exist the menu function of general.js -->
	</div>
	<div id="main_contents">     
	<div id="contents">
		<h4 style="color:brown;">Welcome...!<br/><br/>
			This is the final exam project of the Java Web Development Course.</h4>			
		</div>
	</div> 
	</div>
	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>        
	</div>
</body>
</html>