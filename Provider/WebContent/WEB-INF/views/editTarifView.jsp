<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<center>
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>
			<fmt:message key="tarif_edit"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty tarif}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editTarif">
				<input type="hidden" name="code" value="${tarif.code}" />
				<table border="0">
					<tr>
						<td><fmt:message key="common_id"></fmt:message></td>
						<td style="color: red;"><b>${tarif.code}</b></td>
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
							value="${tarif.description}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="service_id"></fmt:message></td>
						<td><select name="service_id" size="1">
						<c:choose>
								<c:when test="${tarif.service_id == 1 }">
									<option selected="selected" value="1"><fmt:message
											key="internet"></fmt:message></option>
									<option value="2"><fmt:message key="tv"></fmt:message></option>
									<option value="3"><fmt:message key="calling"></fmt:message></option>
								</c:when>
								<c:when test="${tarif.service_id == 2 }">
									<option selected="selected" value="2"><fmt:message
											key="tv"></fmt:message></option>
									<option value="1"><fmt:message key="internet"></fmt:message></option>
									<option value="3"><fmt:message key="calling"></fmt:message></option>
								</c:when>

								<c:when test="${tarif.service_id == 3 }">
									<option selected="selected" value="3"><fmt:message
											key="calling"></fmt:message></option>
									<option value="1"><fmt:message key="internet"></fmt:message></option>
									<option value="2"><fmt:message key="tv"></fmt:message></option>
								</c:when>

							</c:choose>
							</select>
							</td>
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