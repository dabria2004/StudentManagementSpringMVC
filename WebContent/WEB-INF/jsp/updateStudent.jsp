<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="studentmanagement.dto.StudentResponseDTO" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>addStudent.jsp</title>
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

	function myRegister() {
        confirm("Welcome for Registration!");
	}
	
	function myUpdate(){
	   confirm("Welcome for Update!");
	}
	
	function showMessage(){     
        var message = confirm("Are you sure to register?");
        if(message)                 
        document.getElementById('message').innerHTML = "Registration completed.";    
    }
	
    function addListData(source,destination)
    {
        var sourceList=document.getElementById(source.id);
        var sourceSelect=sourceList.selectedIndex;
        
        var len=sourceList.length;
        
        if(sourceSelect!=-1)
        {
            for(i=0;i<len;i++)
            {
                var isExist = false;
                var sourceText=sourceList.options[i].text;
                var destinationList=document.getElementById(destination.id);
                if(sourceList[i].selected)
                {
                    for(var j=0;j<destinationList.length;j++)
                    {
                        if(destinationList.options[j].text == sourceText)
                        {
                            isExist = true;
                        }
                    }
                    if(!isExist)
                    {
                        destinationList.options[destinationList.length]=new Option(sourceText,"0");     
                    }
                }
            }           
        }
    }
    function delListData(source)
    {
        var sourceList=document.getElementById(source.id);
        for(var index=0;index<sourceList.length;index++){   
            if (sourceList[index].selected) {
                sourceList.remove(index);
                delListData(source);
            }
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
	<div class="search_form">
	<h3>Student Register</h3>
	<label id="errormsg" > Message </label><br/><br/><br/>
<div style="color:red;"> ${error}</div>
<div style="color:blue;"> ${success}</div>
	<form:form action="/StudentManagementSpring/updatestudent" method="post" modelAttribute="sbean">
	
		<form:errors path="student_id" style="color:red;"></form:errors>
		<form:errors path="student_name" style="color:red;"></form:errors>
		<form:errors path="class_name" style="color:red;"></form:errors>
		<form:errors path="year" style="color:red;"></form:errors>
		<form:errors path="month" style="color:red;"></form:errors>
		<form:errors path="day" style="color:red;"></form:errors>
		<form:errors path="status" style="color:red;"></form:errors>
		
			<table class="tableForm">
				<tr>
					<td class="tblSingleLabel">Student No *</td>
					<td class="tblSingleInput">
						<form:input type="text" class="txtlong" path="student_id"/>
					</td>
				</tr>
				<tr>
					<td class="tblSingleLabel">Student Name *</td>
					<td class="tblSingleInput">
						<form:input type="text" class="txtlong" path="student_name"/>
				</tr>
				 <tr>
					<td class="tblSingleLabel">Class Name *</td>
					<td class="tblSingleInput">  
						<form:select id="expenseType" class="normal_sel" path="class_name">
							<c:forEach var="list" items="${sessionScope.classlist}">
								<form:option value="${list.class_name }" path="class_name"/> 
							</c:forEach>
						</form:select>
					</td>                  
				</tr> 
				<tr>
				<td class="tblSingleLabel">Registered Date *</td>
					<td class="tblSingleInput">
						<form:select path="year" id="expenseType" class="short_sel" required="required">
						<!--<form:option value="NONE" label="Year"/>-->
									<form:option value="${editStuInfo.year}"/>
									<form:option value="2016"/>
									<form:option value="2015"/>
									<form:option value="2014"/>
									<form:option value="2013"/>
									<form:option value="2012"/>
									<form:option value="2011"/>
									<form:option value="2010"/>
									<form:option value="2009"/>
						</form:select> 
						<form:select path="month" id="expenseType" class="short_sel" required="required">
						<!--<form:option value="NONE" label="Month"/>-->
						<form:option value="${editStuInfo.month}"/>
									<form:option value="01"/>
									<form:option value="02"/>
									<form:option value="03"/>
									<form:option value="04"/>
									<form:option value="05"/>
									<form:option value="06"/>
									<form:option value="07"/>
									<form:option value="08"/>
									<form:option value="08"/>
									<form:option value="10"/>
									<form:option value="11"/>
									<form:option value="12"/>
						</form:select> 
						<form:select path="day" id="expenseType" class="short_sel" required="required">
						<!--<form:option value="NONE" label="Day"/>-->
						<form:option value="${editStuInfo.day}"/>
									<form:option value="01"/>
									<form:option value="02"/>
									<form:option value="03"/>
									<form:option value="04"/>
									<form:option value="05"/>
									<form:option value="06"/>
									<form:option value="07"/>
									<form:option value="08"/>
									<form:option value="08"/>
									<form:option value="10"/>
									<form:option value="11"/>
									<form:option value="12"/>
									<form:option value="13"/>
									<form:option value="14"/>
									<form:option value="15"/>
									<form:option value="16"/>
									<form:option value="17"/>
									<form:option value="18"/>
									<form:option value="19"/>
									<form:option value="20"/>
									<form:option value="21"/>
									<form:option value="22"/>
									<form:option value="23"/>
									<form:option value="24"/>
									<form:option value="25"/>
									<form:option value="26"/>
									<form:option value="27"/>
									<form:option value="28"/>
									<form:option value="29"/>
									<form:option value="30"/>
									<form:option value="31"/>
						</form:select></td>
						<tr>
							<td class="tblSingleLabel">Status *</td>
							<td class="tblSingleInput">
							<form:select path="status" id="expenseType" required="required">
									<form:option value="Attending"/>
									<form:option value="passed"/>
									<form:option value="failed"/>
							</form:select>
							</td>
						</tr>
		</table>
	<br/>
	<div id="btnGroup">
			<input type="submit" value="Update" class="button"/>
		<a href="/StudentManagementSpring/deletestudent/${editStuInfo.student_id}">
			<input type="button" value="Delete" class="button"/>
		</a>
		<a href="/StudentManagementSpring/setupsearchstudent">
			<input type="button" value="Back" class="button"/>
		</a>
	</div>
			</form:form>
	</div> 
	</div>
	</div>   
	</div>
	<div class="footer"> 
		<span>Copyright &#169; ACE Inspiration 2016</span>        
	</div>
</body>
</html>