<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Search Result</title>
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
	function resetForm()
	{
		document.getElementById('message').innerHTML = "Message";
		document.getElementById('list').style.display="none";
	}

	function checkDelete()
    {
        var con = confirm("Are you sure to delete?");
        if(con)
        {
            window.location.replace('USR001.html?flag=1')
        }

    }
	
</script>
</head>
<body>
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
    <div class="search_form">
	<h3>User Search</h3>
	<form method="post" action="/StudentManagementSpring/usersearch">
	<table class="tableForm">
	<tr>
		<td class="tblLabel"><label>User ID</label></td>
		<td class="tblInputNormal"><input type="text" id="txtUserId" class="txt" name="uid"/>	</td>
	
		<td class="tblLabel">User Name	</td>
		<td class="tblInputNormal"><input type="text" id="txtUserName" class="txt" name="uname"/>	　</td>
		</tr>
	</table>
		<br/>
		<input type="submit"  value="Search" class="button"/>
		<a href="/StudentManagementSpring/setupadduser">
             <input type="button" value="Add" class="button" id="userInsert" />
        </a>
	<a href="/StudentManagementSpring/setupusersearch">
             <input type="button" value="Reset" class="button" id="userInsert" />
        </a>

	</form>
	<br/>
	<br/>	
	<div id="errormsg">
		<label id="message">Message</label>
		</div>
	</div>
	
	<br/><br/><br/>
	<div id="list">
<!-- <form name="listForm"> -->	
	<div style="color:red;"> ${error}</div>
	<table style="border: 1px solid black; width:80%; border-collapse:collapse; text-align: center;">
<!-- <table class="resultTable"> -->	
		<tr class="tblHeader">
			<th width="3%" style="border: 1px solid black;">Delete</th>
			<th width="3%" style="border: 1px solid black;">Update</th>
			<th width="12%" style="border: 1px solid black;">User ID</th>
			<th width="24%" style="border: 1px solid black;">User Name</th>
		</tr>
		<tr>
		
			<td style="border: 1px solid black; content-align:center" id="userUpdate">
				<a href="setupUpdateUser/${list.user_id}">Update</a>
			</td>
			<td style="border: 1px solid black; content-align:center" id="delete">
				<a href="deleteuser/${list.user_id}">Delete</a>
			</td>
			<td style="border: 1px solid black; content-align:center">${list.user_id}</td>
			<td style="border: 1px solid black; content-align:center">${list.user_name}</td>
			
		</tr>
		</table>
	<!-- </form> -->	
	</div>
	</div>
	</div>
	</div>
	<div class="footer"> 
		<span>Copyright &#169; ACE Inspiration 2016</span>        
		</div>
	
	
</body>
</html>