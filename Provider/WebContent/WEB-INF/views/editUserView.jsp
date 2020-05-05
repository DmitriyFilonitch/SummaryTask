<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<center>
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>Edit User</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty user}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editUser">
				<input type="hidden" name="user_id" value="${user.user_id}" />
				<table border="0">
					<tr>
						<td>ID</td>
						<td style="color: red;"><b>${user.user_id}</b></td>
					</tr>
					<tr>
						<td>userName</td>
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

								<option selected="selected">${user.gender }</option>
								<c:choose>
									<c:when test="${user.gender == 'Female' }">
										<option value="Male">Male</option>
									</c:when>
									<c:when test="${user.gender == 'Male' }">
										<option value="Female">Female</option>
									</c:when>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td>Balance</td>
						<td><input type="text" name="balance" value="${user.balance}" /></td>
					</tr>
					</tr>
					<tr>
						<td>Block Status</td>
						<td><select name="block_status" size="1">

								<option selected="selected">${user.block_status}</option>
								<c:choose>
									<c:when test="${not user.block_status }">
										<option value="true">true</option>
									</c:when>
									<c:when test="${user.block_status }">
										<option value="false">false</option>
									</c:when>
								</c:choose>
						</select></td>
					</tr>

					<tr>
						<td colspan="3"><input type="submit" value="Submit" /> <a
							href="${pageContext.request.contextPath}/adminInfo">Cancel</a></td>
					</tr>
				</table>
			</form>
		</c:if>

		<jsp:include page="_footer.jsp"></jsp:include>
	</center>
</body>
</html>