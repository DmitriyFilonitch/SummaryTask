<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

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
		<h3>
			<fmt:message key="user_edit"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<c:if test="${not empty user}">
			<form method="POST"
				action="${pageContext.request.contextPath}/editUser">
				<input type="hidden" name="user_id" value="${user.user_id}" />
				<table border="0">
					<tr>
						<td><fmt:message key="common_id"></fmt:message></td>
						<td style="color: red;"><b>${user.user_id}</b></td>
					</tr>
					<tr>
						<td><fmt:message key="userinfo_username"></fmt:message></td>
						<td><input type="text" name="userName"
							value="${user.userName}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="userinfo_fullname"></fmt:message></td>
						<td><input type="text" name="fullname"
							value="${user.fullname}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="userinfo_gender"></fmt:message></td>
						<td><select name="gender" size="1">


								<c:choose>
									<c:when test="${user.gender == 'Female' }">
										<option selected="selected" value="Female"><fmt:message
												key="female"></fmt:message></option>
										<option value="Male"><fmt:message key="male"></fmt:message></option>
									</c:when>
									<c:when test="${user.gender == 'Male' }">
										<option selected="selected" value="Male"><fmt:message
												key="male"></fmt:message></option>
										<option value="Female"><fmt:message key="female"></fmt:message></option>
									</c:when>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td><fmt:message key="userinfo_balance"></fmt:message></td>
						<td><input type="text" name="balance" value="${user.balance}" /></td>
					</tr>
					</tr>
					<tr>
						<td><fmt:message key="userinfo_block_status"></fmt:message></td>
						<td><select name="block_status" size="1">


								<c:choose>
									<c:when test="${not user.block_status }">
										<option selected="selected" value="false"><fmt:message
												key="no"></fmt:message></option>
										<option value="true"><fmt:message key="yes"></fmt:message></option>
									</c:when>
									<c:when test="${user.block_status }">
										<option selected="selected" value="true"><fmt:message
												key="yes"></fmt:message></option>
										<option value="false"><fmt:message key="no"></fmt:message></option>
									</c:when>
								</c:choose>
						</select></td>
					</tr>
					
					
					<tr>
						<td><fmt:message key="active_status"></fmt:message></td>
						<td><select name="active_status" size="1">


								<c:choose>
									<c:when test="${not user.active_status }">
										<option selected="selected" value="false"><fmt:message
												key="no"></fmt:message></option>
										<option value="true"><fmt:message key="yes"></fmt:message></option>
									</c:when>
									<c:when test="${user.active_status }">
										<option selected="selected" value="true"><fmt:message
												key="yes"></fmt:message></option>
										<option value="false"><fmt:message key="no"></fmt:message></option>
									</c:when>
								</c:choose>
						</select></td>
					</tr>
					

					<tr>
						<td colspan="3"><input type="submit"
							value="<fmt:message key="common_save"></fmt:message>" /> <a
							href="${pageContext.request.contextPath}/adminInfo"><fmt:message
									key="login_cancel"></fmt:message></a></td>
					</tr>
				</table>
			</form>
		</c:if>

		<jsp:include page="_footer.jsp"></jsp:include>
	</center>
</body>
</html>