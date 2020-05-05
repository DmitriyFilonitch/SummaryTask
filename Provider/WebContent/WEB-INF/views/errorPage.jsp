<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>

<body>
	<center>
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h1>
			<fmt:message key="error_page_text"></fmt:message>
		</h1>

		<p style="color: red;">${errorString}</p>

		<jsp:include page="_footer.jsp"></jsp:include>
	</center>
</body>
</html>