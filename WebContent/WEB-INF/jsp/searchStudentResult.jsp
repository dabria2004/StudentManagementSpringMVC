<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="studentmanagement.dto.StudentResponseDTO" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/resources/css/base.css" var="baseCss" />
<spring:url value="/resources/css/container.css" var="containerCss" />
<spring:url value="/resources/js/accordian-menu.js" var="accordianjs" />
<spring:url value="/resources/js/general.js" var="generaljs" />

<link href="${baseCss}" rel="stylesheet" />
<link href="${containerCss}" rel="stylesheet" />
<script src="${accordianjs}"></script>
<script src="${generaljs}"></script>
<script type="text/javascript">
	window.onload = function() {
		menu();
		var param = getPara('flag');
		if (param == 1) {
			document.getElementById('list').style.display = "none";
		} else if (param == 2) {
			document.getElementById('list').style.display = "block";
		}

	}
	function changeName() {
		var ascName = document.getElementById('ascName').style.display;
		var dscName = document.getElementById('dscName').style.display;
		if (ascName == 'none') {
			document.getElementById('ascName').style.display = 'inline';
			document.getElementById('dscName').style.display = 'none';
		} else if (ascName == 'inline') {
			document.getElementById('ascName').style.display = 'none';
			document.getElementById('dscName').style.display = 'inline';
		}
	}
	function searchList() {
		document.getElementById('list').style.display = "block";
	}

	function resetForm() {
		document.getElementById('message').innerHTML = "Message";
		document.getElementById('list').style.display = "none";
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
					<td>Login Date</td>
					<td>: ${sessionScope.date}</td>
				</tr>
			</table>
		</div>
		<a href="/StudentManagementSpring/logout"><input id="btn_logout" class="button"
			type="button" value="Logout"></a>
	</div>

	<div id="container">
		<div id="left_menu">
			<!-- menu html code exist the menu function of general.js -->
		</div>
	<div id="main_contents">
	<div id="contents">
	<div class="search_form">
		<h3>Student Search Page</h3>
			<form action="/StudentManagementSpring/searchstudent" method="post">
				<table class="tableForm">
					<tr>
						<td class="tblLabel">Student No.</td>
						<td class="tblInputShort">
							<input type="text" name="studentId" value="" class="txt" /></td>
						<td class="tblLabel">Student Name</td>
						<td class="tblInputShort">
							<input type="text" name="studentName" value="" class="txt" /></td>
						</tr>
					<tr>
						<td class="tblLabel">Class Name</td>
						<td class="tblInputNormal" colspan="3">
							<input type="text" name="className" id="companyName" value="" class="txtlong" /></td>
					</tr>
				</table>
			<br/> 
			<input type="submit" value="Search" class="button" /> 
		<a href="/StudentManagementSpring/setupsearchstudent">
			<input type="button" class="button" value="Reset"/> 
		</a>
			<br/><br/>
			<label id="message" style="color: red;">Message</label>
			</form>
		<div id="list">
		<br/>
			<table style="border: 1px solid black; width:80%; border-collapse:collapse; text-align: center;">
				<tr class="tblHeader">
					<th width="5%" style="border: 1px solid black;">No</th>
					<th width="10%" style="border: 1px solid black;">Student No</th>
					<th width="25%" style="border: 1px solid black;">Student Name</th>
					<th width="40%" style="border: 1px solid black;">Class Name</th>
					<th width="15%" style="border: 1px solid black;">Registered Date</th>
					<th width="14%" style="border: 1px solid black;">Status</th>
					<!-- <th width="5%" style="border: 1px solid black;">Action</th> -->
				</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach var="list" items="${studentList}">
				<tr>
				<c:set var="count" value="${count + 1}" scope="page" />
					<td style="border: 1px solid black; content-align:center">${count}</td>
					<td style="border: 1px solid black; content-align:center">${list.student_id}</td>
					<td style="border: 1px solid black; content-align:center">
						<a href="/StudentManagementSpring/setupstudentupdate/${list.student_id}">${list.student_name}</a>
					</td>
					<td style="border: 1px solid black; content-align:center">${list.class_name}</td>
					<td style="border: 1px solid black; content-align:center">${list.date}</td>
					<td style="border: 1px solid black; content-align:center">${list.status}</td>
					<!-- <td style="border: 1px solid black; content-align:center">
						<a href="/StudentManagementSpring/deletestudent/${list.student_id}">Delete</a>
					</td> -->
				</tr>
			</c:forEach>
		</table>
	<br />

					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>

</body>
</html>