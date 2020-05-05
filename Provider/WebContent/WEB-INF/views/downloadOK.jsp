<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<div style="text-align: center">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3>Download OK!</h3>

<input type="button" value="Вернуться в Личный Кабинет"
					onclick=" location.href='${pageContext.request.contextPath}/userInfo' ">
	</div>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>