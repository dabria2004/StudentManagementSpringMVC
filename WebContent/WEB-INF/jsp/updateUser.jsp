<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>updateUser.jsp</title>
<spring:url value="/resources/css/base.css" var="baseCss" />
<spring:url value="/resources/css/container.css" var="containerCss" />
<spring:url value="/resources/js/accordian-menu.js" var="accordianjs" />
<spring:url value="/resources/js/general.js" var="generaljs" />

<link href="${baseCss}" rel="stylesheet" />
<link href="${containerCss}" rel="stylesheet" />
<script src="${accordianjs}"></script>
<script src="${generaljs}"></script>
<script type="text/javascript">
    window.onload=function()
    {
        menu();     

    }
	
	function update()
    {
        var con = confirm("Are you sure to register?");
        if(con)
        {
            window.close();
        }
        
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
			<td>: ${sessionScope.userInfo.user_id}
				${sessionScope.userInfo.user_name}</td> 
		</tr>
		<tr>
			<td>Current Date</td>
			<td>: ${sessionScope.date}</td>
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
		<h3>User Update</h3>
			
	<div style="color:red;"> ${success}</div>
	<div style="color:red;"> ${error}</div>
		<form:form method="post" action="/StudentManagementSpring/updateuser" name="registerForm" modelAttribute="bean">
			<table class="tableForm">
				<tr>
					<td class="tblSingleLabel"> User ID</td>
					<td class="tblSingleInput">
						<form:input type="text" path="user_id"/>
					</td>
					<td><form:errors path="user_id" style="color:red;"></form:errors></td>
				</tr>
				<tr>
					<td class="tblSingleLabel">User Name</td>
					<td class="tblSingleInput">
						<form:input type="text" class="txtlong_popup" id="txtUserName" path="user_name"/>
					</td>  
					<td><form:errors path="user_name" style="color:red;"></form:errors></td>            
				</tr>
				<tr>
					<td class="tblSingleLabel">Password</td>
					<td class="tblSingleInput">
						<form:input type="text" class="txtlong_popup" id="txtUserName" path="user_password"/>
					</td>  
					<td><form:errors path="user_password" style="color:red;"></form:errors></td>            
				</tr>
			</table>
		<br/>
			<input type="submit" value="Update" class="button"/>
			<a href="/StudentManagementSpring/setupusersearch">
            	<input type="button" value="Back" class="button"/>
        	</a>
<!-- <input type="button" value="Back" class="button" onClick="window.location.replace('User_Search.jsp')"/> -->			
		</form:form>
	<br/><br/><br/>
	</div>
	</div>
	</div>
		<div class="footer"> 
			<span>Copyright &#169; ACE Inspiration 2016</span>        
		</div>
</body>
</html>