<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="base.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Student List</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<%
@SuppressWarnings("unchecked")
List<Student> theStudents = (List<Student>) request.getAttribute("STUDENT_LIST");
pageContext.setAttribute("itemx", theStudents);
%>

<body>
	
	<div id="wrappper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<!-- put new button : Add Student -->
			<input type="button" value="add Student"
			  		onclick="window.location.href='add-student-form.jsp'; return false;"
			  		class="add-student-button"	/>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempStudent" items="${itemx }">
				<!-- set up a link for each student -->
				<c:url var="tempLink" value="StudentControllerServlet" >
					<c:param name="command" value="LOAD" />
					<c:param name="studentID" value="${tempStudent.id }" />
				</c:url>
				
				<!-- Set up a link to delete a student -->
				<c:url var="deleteLink" value="StudentControllerServlet" >
					<c:param name="command" value="DELETE" />
					<c:param name="studentID" value="${tempStudent.id }" />
				</c:url>
				<tr>
					<td>${tempStudent.firstName }</td>
					<td>${tempStudent.lastName }</td>
					<td>${tempStudent.email }</td>
					<td>
					<a href="${tempLink }" >Update</a>
					| 
					<a href="${deleteLink }"
						onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false"
					>Delete</a>
					</td>	
				</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>
	

</body>
</html>