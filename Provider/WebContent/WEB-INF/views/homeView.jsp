<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
.fig {
	text-align: center; /* Выравнивание по центру */
}
</style>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
</head>

<body>
	<br />

	<h1 style="text-align: center; margin-left: 0; margin-reght: 0">
		<strong><fmt:message key="home_head"></fmt:message></strong>
	</h1>
	<br />
	<br />
	<p class="fig">
		<img src="Data/www.jpg" width="960" height="600"
			alt="альтернативный текст">
	</p>
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>