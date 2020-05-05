<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tarif List</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
<div style="text-align:center">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3><fmt:message key="tariflist_tariflist"></fmt:message></h3>

		<p style="color: red;">${errorString}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Service_id</th>
			</tr>
			<c:forEach items="${tarifList}" var="tarif">
				<tr>
					<td>${tarif.code}</td>
					<td>${tarif.name}</td>
					<td>${tarif.price}</td>
					<td>${tarif.description}</td>
					<td>${tarif.service_id}</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<h2><fmt:message key="tariflist_message1"></fmt:message> <a href="userInfo">↗️</a><fmt:message key="tariflist_message2"></fmt:message></h2>

		
</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	
</body>
</html>