<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info</title>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
</head>
<body>
	<form action="userInfo" method="get">
		<div style="text-align: center">
			<jsp:include page="_header.jsp"></jsp:include>
			<jsp:include page="_menu.jsp"></jsp:include>
			<br />
			<h3>
				<fmt:message key="userinfo_hello"></fmt:message>
				${user.userName}
			</h3>

			<fmt:message key="userinfo_username"></fmt:message>
			: <b>${user.userName}</b> <br />
			<fmt:message key="userinfo_fullname"></fmt:message>
			: ${user.fullname } <br />
			<fmt:message key="userinfo_gender"></fmt:message>
			: ${user.gender } <br />

			<fmt:message key="userinfo_rolename"></fmt:message>
			: ${user.nameRole } <br />
			<fmt:message key="userinfo_user_id"></fmt:message>
			: ${user.user_id } <br />
			<fmt:message key="active_status"></fmt:message>
			: ${active_status} <br />
			<fmt:message key="userinfo_block_status"></fmt:message>
			: ${block } <br /> <br /> <input type="button"
				value="<fmt:message key="userinfo_popolnit"></fmt:message>"
				onclick=" location.href='bank?user_id=${user.user_id}' "> <br />
			<br />
			<fmt:message key="userinfo_download"></fmt:message>
			:
			<form action="userInfo" method="get">
				<input type="submit" name="downloadTAriff" value="⬇️"><br>
				<p style="color: Indigo; font-weight: bold">${ok}</p>
			</form>
			<c:choose>

				<c:when test="${block }">
					<p style="color: red; font-weight: bold; font-size: xx-large">
						<fmt:message key="userinfo_banned"></fmt:message>
					</p>
				</c:when>
				<c:otherwise>

					<c:choose>
						<c:when test="${total == 0 }">
							<p style="font-weight: bold">
								<fmt:message key="userinfo_allarepaid"></fmt:message>
								<br /> <br />
						</c:when>
						<c:when test="${total > 0 }">
							<p style="font-weight: bold">
								<fmt:message key="userinfo_allarenotpaid"></fmt:message>
								<br /> <br />
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${balance > 0 }">
							<fmt:message key="userinfo_balance"></fmt:message>: ${balance } ₴ <br />
							<br />

							<c:choose>
								<c:when test="${total > 0}">
									<fmt:message key="userinfo_totaltopay"></fmt:message>: ${total} ₴
		<input type="button"
										value="<fmt:message key="userinfo_pay"></fmt:message>"
										onclick=" location.href='payTarif?user_id=${user.user_id}' ">
									<br />
									<c:choose>
										<c:when test="${balance < total}">
											<p style="color: red; font-weight: bold">${message}</p>
										</c:when>
									</c:choose>
									<br />
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<p style="color: red; font-weight: bold">
								<fmt:message key="userinfo_nomoney"></fmt:message>
							</p>
							<br />
						</c:otherwise>
					</c:choose>

					<h3>
						<fmt:message key="userinfo_yourtariffs"></fmt:message>
					</h3>

					<p style="color: red;">${errorString}</p>

					<table border="1" cellpadding="5" cellspacing="1" align="center">
						<tr>
							<th><fmt:message key="common_id"></fmt:message></th>
							<th><fmt:message key="common_name"></fmt:message></th>
							<th><fmt:message key="price"></fmt:message></th>
							<th><fmt:message key="common_description"></fmt:message></th>
							<th><fmt:message key="service_name"></fmt:message></th>
							<th><fmt:message key="service_description"></fmt:message></th>
							<th><fmt:message key="turn_off_tariff"></fmt:message></th>

						</tr>
						<c:forEach items="${tarif}" var="tarif">
							<tr>
								<td>${tarif.code}</td>
								<td>${tarif.name}</td>
								<td>${tarif.price}</td>
								<td>${tarif.description}</td>
								<td>${tarif.service_name }</td>
								<td>${tarif.service_description}</td>
								<td><input type="button"
									value="<fmt:message key="userinfo_stoptarif"></fmt:message>"
									onclick=" location.href='deleteUserInfoTarif?code=${tarif.code}' "></td>
							</tr>
						</c:forEach>
					</table>


					<input type="button"
						value="<fmt:message key="userinfo_seeotherstariffs"></fmt:message>"
						onclick=" location.href='${pageContext.request.contextPath}/orderTarif' ">
				</c:otherwise>
			</c:choose>
		</div>

		<jsp:include page="_footer.jsp"></jsp:include>
	</form>
</body>
</html>