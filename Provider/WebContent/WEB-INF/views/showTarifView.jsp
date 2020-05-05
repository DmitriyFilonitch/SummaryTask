<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Order Page</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>

<body>
	<div style="text-align: center">

		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />

		<h2><fmt:message key="showtarif_title"></fmt:message>:</h2>
		<br />
		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Service_id</th>
				<th><fmt:message key="showtarif_choosetarif"></fmt:message></th>

			</tr>
			<c:forEach items="${tarifList}" var="tarif">
				<tr>
					<td>${tarif.code}</td>
					<td>${tarif.name}</td>
					<td>${tarif.price}</td>
					<td>${tarif.description}</td>
					<td>${tarif.service_id}</td>
					<td><input type="button" value="💡"
						onclick=" location.href='addTarif?code=${tarif.code}' "></td>
				</tr>
			</c:forEach>
		</table>
		<form action="showTarif" method="get">
			<select name="opT">
				<option value="1">Sort By Price(9-1)
				<option value="2">Sort By Price(1-9)
				<option value="3">Sort By Code(A-Z)
				<option value="4">Sort By Code(Z-A)
				<option value="5">Sort By Service ID (9-1)
				<option value="6">Sort By Service ID(1-9)
				<option value="7">Sort By Name(A-Z)
				<option value="8">Sort By Name(Z-A)
			</select> <input type="submit" value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>
		<input type="button" value="<fmt:message key="settings_jsp.link.back_to_main_page"></fmt:message>"
			onclick=" location.href=' ${pageContext.request.contextPath}/orderTarif' ">
		<jsp:include page="_footer.jsp"></jsp:include>

	</div>
</body>

</html>