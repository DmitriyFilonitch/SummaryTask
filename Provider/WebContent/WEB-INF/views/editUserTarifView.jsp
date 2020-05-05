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
		<h3>Edit User Tarif</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty userTarif}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editUserTarif">
				<input type="hidden" name="id_user" value="${userTarif.id_user}" />
				<table border="0">
					<tr>
						<td>ID user</td>
						<td style="color: red;"><b>${userTarif.id_user}</b></td>
					</tr>
					<tr>
						<td>Tarif Code</td>
						<td><input type="text" name="code" value="${userTarif.code}" /></td>
					</tr>
					<tr>
						<td>Payment Status</td>
						<c:choose>
							<c:when test="${userTarif.payment_status == 1 }">
								<td><select name="payment_status" size="1">
										<option selected="selected" value="1">Yes</option>
										<option value="2">No</option>
								</select></td>
							</c:when>
							<c:when test="${userTarif.payment_status == 2 }">
								<td><select name="payment_status" size="1">
										<option selected="selected" value="2">No</option>
										<option value="1">Yes</option>
								</select></td>

							</c:when>
						</c:choose>


					</tr>

					<tr>
						<td colspan="3"><input type="submit" value="Submit" /> <a
							href="${pageContext.request.contextPath}/adminInfo">Cancel</a></td>
					</tr>
				</table>
			</form>
		</c:if>
	</center>
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>