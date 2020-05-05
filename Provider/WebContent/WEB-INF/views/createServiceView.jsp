<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Service</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<div style="text-align">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
<br />
		<h3>Create Service</h3>

		<p style="color: red;">${errorString}</p>

		<form method="POST"
			action="${pageContext.request.contextPath}/createService">
			<table border="0" align="center">

				<tr>
					<td>service_id</td>
					<td><input type="text" name="service_id"
						value="${service.service_id}" /></td>
				</tr>

				<tr>
					<td>Name</td>
					<td><input type="text" name="service_name"
						value="${service.service_name}" /></td>
				</tr>

				<tr>
					<td>Description</td>
					<td><input type="text" name="service_description"
						value="${service.service_description }" /></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="Submit" /> <a
						href="adminInfo">Cancel</a></td>
				</tr>
			</table>
		</form>
</div>
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>