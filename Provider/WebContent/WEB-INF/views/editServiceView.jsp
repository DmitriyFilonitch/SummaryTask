<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Service</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<center>
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>
			<fmt:message key="service_edit"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty service}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editService">
				<input type="hidden" name="service_id" value="${service.service_id}" />
				<table border="0">
					<tr>
						<td><fmt:message key="common_id"></fmt:message></td>
						<td style="color: red; text-align: center"><b>${service.service_id}</b></td>
					</tr>
					<tr>
						<td><fmt:message key="common_name"></fmt:message></td>
						<td><input type="text" name="service_name"
							value="${service.service_name}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="common_description"></fmt:message></td>
						<td><input type="text" name="service_description"
							value="${service.service_description}" /></td>
					</tr>
					<tr>
						<td colspan="3"><input type="submit"
							value="<fmt:message key="common_save"></fmt:message>" /> <a
							href="${pageContext.request.contextPath}/adminInfo"><fmt:message
									key="login_cancel"></fmt:message></a></td>
					</tr>
				</table>
			</form>
		</c:if>

		<jsp:include page="_footer.jsp"></jsp:include>
	</center>
</body>
</html>