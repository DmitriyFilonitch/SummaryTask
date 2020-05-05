<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
<center>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	
<br />
		<h3><fmt:message key="login_title"></fmt:message></h3>
		<p style="color: red;">${errorString}</p>



		<form method="POST" action="${pageContext.request.contextPath}/login">
			<table border="0">
				<tr>
					<td><fmt:message key="userinfo_username"></fmt:message></td>
					<td><input type="text" name="userName"
						value="${user.userName}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="login_pass"></fmt:message></td>
					<td><input type="text" name="password"
						value="${user.password}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="login_rememberme"></fmt:message></td>
					<td><input type="checkbox" name="rememberMe" value="Y" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="<fmt:message key="login_singin"></fmt:message>" /> <a
						href="${pageContext.request.contextPath}/"><fmt:message key="login_cancel"></fmt:message></a></td>
				</tr>
			</table>
		</form>

	</center>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>