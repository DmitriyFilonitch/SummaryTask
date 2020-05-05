<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h3>Edit Tarif</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty tarif}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editTarif">
				<input type="hidden" name="code" value="${tarif.code}" />
				<table border="0">
					<tr>
						<td>Code</td>
						<td style="color: red;"><b>${tarif.code}</b></td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="name" value="${tarif.name}" /></td>
					</tr>
					<tr>
						<td>Price</td>
						<td><input type="text" name="price" value="${tarif.price}" /></td>
					</tr>
					<tr>
						<td>Description</td>
						<td><input type="text" name="description"
							value="${tarif.description}" /></td>
					</tr>
					<tr>
						<td>Service_id</td>
					<td><select name="service_id" size="1">

							<option selected="selected">${tarif.service_id}</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
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