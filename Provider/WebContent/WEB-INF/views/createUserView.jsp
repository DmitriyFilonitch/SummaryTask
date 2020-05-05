<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create User</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<div style="text-align: center">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>
			<fmt:message key="admininfo_newUser"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<form method="POST"
			action="${pageContext.request.contextPath}/createUser">
			<table border="0" align="center">

				<tr>
					<td><fmt:message key="userinfo_username"></fmt:message></td>
					<td><input type="text" name="userName"
						value="${user.userName}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="userinfo_fullname"></fmt:message></td>
					<td><input type="text" name="fullname"
						value="${user.fullname}" /></td>
				</tr>

				<tr>
					<td><fmt:message key="userinfo_gender"></fmt:message></td>

					<td><select name="gender" size="1">


							<option value="Male"><fmt:message key="male"></fmt:message></option>
							<option value="Female"><fmt:message key="female"></fmt:message></option>
					</select></td>

				</tr>
				<tr>
					<td><fmt:message key="login_pass"></fmt:message></td>
					<td><input type="text" name="password"
						value="${user.password }" /></td>
				</tr>
				<tr>
					<td><fmt:message key="userinfo_balance"></fmt:message></td>
					<td><input type="text" name="balance" value="${user.balance }" /></td>
				</tr>

				<tr>
					<td><fmt:message key="userinfo_roleid"></fmt:message></td>

					<td><select name="role_id" size="1">


							<option value="1"><fmt:message key="admin"></fmt:message></option>
							<option value="2"><fmt:message key="client"></fmt:message></option>
					</select></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit"
						value="<fmt:message key="common_save"></fmt:message>" /> <a
						href="adminInfo"><fmt:message key="login_cancel"></fmt:message></a></td>
			</table>
		</form>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>