<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Replenish</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

<div style="height: 50vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    text-align: left">
    
	<form method="POST" action="${pageContext.request.contextPath}/bank">
		<input type="hidden" name="user_id" value="${user.user_id}" />
		<p style="color: Indigo; font-weight: bold; font-size: xx-large"><fmt:message key="bank_enteramount"></fmt:message></p>
		<table border="0" >
		
			<tr>
				<td><fmt:message key="bank_current"></fmt:message></td>
				<td style="color: red;text-align:center"><b>${startbalance}</b></td>
			</tr>
			<tr>
				<td><fmt:message key="bank_enter"></fmt:message></td>
				<td><input type="text" name="bbb" value="${bbb}" /></td>
			</tr>

			<tr>
				<td style="text-align:right" colspan="3"><input type="submit" value="<fmt:message key="bank_popolnit"></fmt:message>" /> <a
					href="${pageContext.request.contextPath}/userInfo"><fmt:message key="login_cancel"></fmt:message></a></td>
					
			</tr>
		</table>
		<p style="color: red;font-weight: bold;text-align:center">${error}</p>
	</form>


</div>


	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>