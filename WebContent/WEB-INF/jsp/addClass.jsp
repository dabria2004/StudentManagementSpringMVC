<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<title>addClass.jsp</title>

<script type="text/javascript">
    window.onload=function()
    {
        menu();     

    }
	
	function insert()
    {
        var con = confirm("Are you sure to register?");
        if(con)
        {
            document.getElementById('errormsg').innerHTML = "Successfully Saved!";
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
	<h3>Class Register</h3>
	<label id="errormsg" > Message </label><br/><br/><br/>
	<div style="color:blue;"> ${success}</div>
	<div style="color:red;"> ${fail}</div>
	<div style="color:red;"> ${fields}</div>
	<div style="color:red;"> ${iderror}</div>
	<form:form action="/StudentManagementSpring/addclass" method="post" modelAttribute="cbean" name="registerForm">

	<table class="tableForm">
		<tr>
			<td class="tblSingleLabel"> Class ID </td>
			<td class="tblSingleInput">
				<form:input type="text" path="class_id" id="txtUserId" class="txt_popup"/>
			</td>
			
		</tr>
		<tr>
			<td class="tblSingleLabel">Class Name </td>
			<td class="tblSingleInput">
				<form:input type="text" path="class_name" class="txtlong_popup" id="txtUserName"/>
			</td> 
			       
		</tr>
	</table>
	<br/>
	<input type="submit" value="Register" class="button"/>
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