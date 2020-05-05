<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="CSS/st.css" type="text/css">
<meta charset="UTF-8">
<title>Admin Page</title>
</head>

<body>
	<div style="text-align: center; align: center">
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


		<%@page import="ua.nure.filonitch.summarytask.beans.*,java.util.*"%>

		<h3>
			<fmt:message key="admininfo_servicelist"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th><fmt:message key="service_id"></fmt:message></th>
				<th><fmt:message key="service_name"></fmt:message></th>
				<th><fmt:message key="service_description"></fmt:message></th>
				<th><fmt:message key="admininfo_edit"></fmt:message></th>
				<th><fmt:message key="admininfo_delete"></fmt:message></th>
			</tr>
			<c:forEach items="${serviceList}" var="service">
				<tr>
					<td style="text-align: center">${service.service_id}</td>
					<td style="text-align: center">${service.service_name}</td>
					<td style="text-align: center">${service.service_description}</td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editService?service_id=${service.service_id}' "></td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteService?service_id=${service.service_id}' "></td>
				</tr>
			</c:forEach>

		</table>
		<input type="button"
			value="<fmt:message key="admininfo_newservice"></fmt:message>"
			onclick=" location.href='createService' ">
		<form action="adminInfo" method="get">
			<select name="op">
				<option value="1"><fmt:message key="sort_by_name"></fmt:message>(A-Z)

				
				<option value="2"><fmt:message key="sort_by_name"></fmt:message>(Z-A)

				
			</select> <input type="submit"
				value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

		<h3>
			<fmt:message key="admininfo_tarifList"></fmt:message>
		</h3>

		<p style="color: red;">${errorString}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th><fmt:message key="tarif_code"></fmt:message></th>
				<th><fmt:message key="common_name"></fmt:message></th>
				<th><fmt:message key="price"></fmt:message></th>
				<th><fmt:message key="common_description"></fmt:message></th>
				<th><fmt:message key="service_id"></fmt:message></th>
				<th><fmt:message key="admininfo_edit"></fmt:message></th>
				<th><fmt:message key="admininfo_delete"></fmt:message></th>
			</tr>
			<c:forEach items="${tarifList}" var="tarif">
				<tr>
					<td style="text-align: center">${tarif.code}</td>
					<td style="text-align: center">${tarif.name}</td>
					<td style="text-align: center">${tarif.price}</td>
					<td style="text-align: center">${tarif.description}</td>
					<td style="text-align: center">${tarif.service_id}</td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editTarif?code=${tarif.code}' "></td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteTarif?code=${tarif.code}' "></td>
				</tr>
			</c:forEach>
		</table>

		<input type="button"
			value="<fmt:message key="admininfo_newTarif"></fmt:message>"
			onclick=" location.href='createTarif' ">
		<form action="adminInfo" method="get">
			<select name="opT">
				<option value="1"><fmt:message key="sort_by_price"></fmt:message>(9-1)

				
				<option value="2"><fmt:message key="sort_by_price"></fmt:message>(1-9)

				
				<option value="3"><fmt:message key="sort_by_code"></fmt:message>(A-Z)

				
				<option value="4"><fmt:message key="sort_by_code"></fmt:message>(Z-A)

				
				<option value="5"><fmt:message key="sort_by_service_id"></fmt:message>(9-1)

				
				<option value="6"><fmt:message key="sort_by_service_id"></fmt:message>(1-9)

				
				<option value="7"><fmt:message key="sort_by_name"></fmt:message>(A-Z)

				
				<option value="8"><fmt:message key="sort_by_name"></fmt:message>(Z-A)

				
			</select> <input type="submit"
				value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

		<h3>
			<fmt:message key="admininfo_userlist"></fmt:message>
		</h3>

		<p style="color: red;">${errorString1}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th><fmt:message key="common_id"></fmt:message></th>
				<th><fmt:message key="userinfo_username"></fmt:message></th>
				<th><fmt:message key="userinfo_fullname"></fmt:message></th>
				<th><fmt:message key="userinfo_gender"></fmt:message></th>
				<th><fmt:message key="userinfo_balance"></fmt:message></th>
				<th><fmt:message key="userinfo_block_status"></fmt:message></th>
				<th><fmt:message key="userinfo_rolename"></fmt:message></th>
				<th><fmt:message key="active_status"></fmt:message></th>
				<th><fmt:message key="admininfo_edit"></fmt:message></th>
				<th><fmt:message key="admininfo_delete"></fmt:message></th>
			</tr>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td style="text-align: center">${user.user_id}</td>
					<td style="text-align: center">${user.userName}</td>
					<td style="text-align: center">${user.fullname}</td>
					<td style="text-align: center">${user.gender}</td>
					<td style="text-align: center">${user.balance}</td>
					<td style="text-align: center">${user.block_status}</td>
					<td style="text-align: center">${user.nameRole }</td>
					<td style="text-align: center">${user.active_status}</td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editUser?user_id=${user.user_id}' "></td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteUser?user_id=${user.user_id}' "></td>
				</tr>
			</c:forEach>
		</table>

		<input type="button"
			value="<fmt:message key="admininfo_newUser"></fmt:message>"
			onclick=" location.href='createUser' ">
		<form action="adminInfo" method="get">
			<select name="opU">
				<option value="1"><fmt:message key="sort_by_id_common"></fmt:message>(9-1)

				
				<option value="2"><fmt:message key="sort_by_id_common"></fmt:message>(1-9)

				
				<option value="3"><fmt:message key="sort_by_username"></fmt:message>(A-Z)

				
				<option value="4"><fmt:message key="sort_by_username"></fmt:message>(Z-A)

				
			</select> <input type="submit"
				value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

		<h3>
			<fmt:message key="admininfo_userstariffs"></fmt:message>
		</h3>

		<p style="color: red;">${errorString1}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th><fmt:message key="id_user"></fmt:message></th>
				<th><fmt:message key="tarif_code"></fmt:message></th>
				<th><fmt:message key="payment_status"></fmt:message></th>
				<th><fmt:message key="admininfo_edit"></fmt:message></th>
				<th><fmt:message key="admininfo_delete"></fmt:message></th>
			</tr>
			<c:forEach items="${userTarifList}" var="userTarif">
				<tr>
					<td style="text-align: center">${userTarif.id_user}</td>
					<td style="text-align: center">${userTarif.code}</td>
					<td style="text-align: center">${userTarif.payment_status}</td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editUserTarif?id_user=${userTarif.id_user}&code=${userTarif.code }' "></td>
					<td style="text-align: center"><input type="button"
						value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteUserTarif?id_user=${userTarif.id_user}&code=${userTarif.code }' "></td>

				</tr>
			</c:forEach>
		</table>

		<input type="button"
			value="<fmt:message key="admininfo_connectuser"></fmt:message>"
			onclick=" location.href='createUserTarif' ">
		<form action="adminInfo" method="get">
			<select name="opUT">
				<option value="1"><fmt:message key="sort_by_user_id"></fmt:message>(9-1)

				
				<option value="2"><fmt:message key="sort_by_user_id"></fmt:message>(1-9)

				
				<option value="3"><fmt:message key="sort_by_code"></fmt:message>(A-Z)

				
				<option value="4"><fmt:message key="sort_by_code"></fmt:message>(Z-A)

				
			</select> <input type="submit"
				value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

	</div>
	<br />
	<br />
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>