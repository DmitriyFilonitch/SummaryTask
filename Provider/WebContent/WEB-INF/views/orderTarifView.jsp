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
		<h3>
			<fmt:message key="admininfo_servicelist"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th><fmt:message key="service_id"></fmt:message></th>
				<th><fmt:message key="service_name"></fmt:message></th>
				<th><fmt:message key="service_description"></fmt:message></th>
				<th><fmt:message key="ordertarif_chooseservice"></fmt:message></th>
			</tr>
			<c:forEach items="${serviceList}" var="service">
				<tr>
					<td style="text-align: center">${service.service_id}</td>
					<td style="text-align: center">${service.service_name}</td>
					<td style="text-align: center">${service.service_description}</td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="ordertarif_gototarif"></fmt:message>"
						onclick=" location.href=' showTarif?service_id=${service.service_id}'  "></td>

				</tr>
			</c:forEach>
		</table>


		<input type="button"
			value="<fmt:message key="settings_jsp.link.back_to_main_page"></fmt:message>"
			onclick=" location.href='${pageContext.request.contextPath}/userInfo' ">

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>

</html>