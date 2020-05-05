<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Tarif</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<div style="">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>
			<fmt:message key="admininfo_newTarif"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<form method="POST"
			action="${pageContext.request.contextPath}/createTarif">
			<table border="0" align="center">
				<tr>
					<td><fmt:message key="common_id"></fmt:message></td>
					<td><input type="text" name="code" value="${tarif.code}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="common_name"></fmt:message></td>
					<td><input type="text" name="name" value="${tarif.name}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="price"></fmt:message></td>
					<td><input type="text" name="price" value="${tarif.price}" /></td>
				</tr>

				<tr>
					<td><fmt:message key="common_description"></fmt:message></td>
					<td><input type="text" name="description"
						value="${tarif.description }" /></td>
				</tr>

				<tr>
					<td><fmt:message key="service_id"></fmt:message></td>
					<td><select name="service_id" size="1">

							<option selected="selected" value="1"><fmt:message
									key="internet"></fmt:message></option>
							<option value="2"><fmt:message key="tv"></fmt:message></option>
							<option value="3"><fmt:message key="calling"></fmt:message></option>
					</select></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit"
						value="<fmt:message key="common_save"></fmt:message>" /> <a
						href="adminInfo"><fmt:message key="login_cancel"></fmt:message></a></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>