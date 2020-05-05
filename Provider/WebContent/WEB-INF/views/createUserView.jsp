<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create User</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
<div style="text-align:center">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
<br />
		<h3>Create User</h3>

		<p style="color: red;">${errorString}</p>

		<form method="POST"
			action="${pageContext.request.contextPath}/createUser">
			<table border="0" align="center">
				<tr>
					<td>user_id</td>
					<td><input type="text" name="user_id" value="${user.user_id}" /></td>
				</tr>
				<tr>
					<td>username</td>
					<td><input type="text" name="userName"
						value="${user.userName}" /></td>
				</tr>
				<tr>
					<td>fullname</td>
					<td><input type="text" name="fullname"
						value="${user.fullname}" /></td>
				</tr>

				<tr>
					<td>gender</td>
			
					<td><select name="gender" size="1">

								
								<option value="Male">Male</option>
								<option value="Female">Female</option>
						</select></td>
					
				</tr>
				<tr>
					<td>password</td>
					<td><input type="text" name="password"
						value="${user.password }" /></td>
				</tr>
				<tr>
					<td>Balance</td>
					<td><input type="text" name="balance"
						value="${user.balance }" /></td>
				</tr>

				<tr>
					<td>role_id</td>
				
					<td><select name="role_id" size="1">

							
								<option value="1">Admin</option>
								<option value="2">Client</option>
						</select></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="adminInfo">Cancel</a></td>
			</table>
		</form>
</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	
</body>
</html>