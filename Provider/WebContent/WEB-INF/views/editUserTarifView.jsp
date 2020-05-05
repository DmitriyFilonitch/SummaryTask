<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

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
		<h3>
			<fmt:message key="edit_usertarif"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty userTarif}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editUserTarif">
				<input type="hidden" name="id_user" value="${userTarif.id_user}" />
				<table border="0">
					<tr>
						<td><fmt:message key="id_user"></fmt:message></td>
						<td style="color: red;"><b>${userTarif.id_user}</b></td>
					</tr>
					<tr>
						<td><fmt:message key="tarif_code"></fmt:message></td>
						<td><input type="text" name="code" value="${userTarif.code}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="payment_status"></fmt:message></td>
						<c:choose>
							<c:when test="${userTarif.payment_status == 1 }">
								<td><select name="payment_status" size="1">
										<option selected="selected" value="1"><fmt:message
												key="yes"></fmt:message></option>
										<option value="2"><fmt:message key="no"></fmt:message></option>
								</select></td>
							</c:when>
							<c:when test="${userTarif.payment_status == 2 }">
								<td><select name="payment_status" size="1">
										<option selected="selected" value="2"><fmt:message
												key="no"></fmt:message></option>
										<option value="1"><fmt:message key="yes"></fmt:message></option>
								</select></td>

							</c:when>
						</c:choose>


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
	</center>
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>