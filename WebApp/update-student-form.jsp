<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Update Student Form</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="wrappper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	<div>
		<h2>Update</h2><br/>
		<form action="StudentControllerServlet" method="get">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="studentId" value="${THE_STUDENT.id }" />
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" value="${THE_STUDENT.firstName }" /></td>
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" value="${THE_STUDENT.lastName }"/></td>
					</tr>
					<tr>
						<td><label>Email</label></td>
						<td><input type="email" name="email" value="${THE_STUDENT.email}"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td>
							<input type="submit" name="Update" />
							
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<p>
			<a href="StudentControllerServlet" >Back to List</a>
		</p>
	</div>
</body>
</html>