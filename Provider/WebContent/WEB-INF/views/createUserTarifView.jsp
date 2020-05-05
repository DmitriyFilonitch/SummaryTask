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
	<div style="">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>
			<fmt:message key="admininfo_userstariffs"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<form method="POST"
			action="${pageContext.request.contextPath}/createUserTarif">
			<table border="0" align="center">
				<tr>
					<td><fmt:message key="id_user"></fmt:message></td>
					<td><input type="text" name="id_user"
						value="${userTarif.id_user}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="tarif_code"></fmt:message></td>
					<td><input type="text" name="code" value="${usertarif.code}" /></td>
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