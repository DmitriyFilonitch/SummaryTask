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
	<div style="text-align: center">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />
		<h3><fmt:message key="userinfo_hello"></fmt:message> ${user.userName}</h3>

		<fmt:message key="userinfo_username"></fmt:message>: <b>${user.userName}</b> <br /> <fmt:message key="userinfo_fullname"></fmt:message>: ${user.fullname } <br />
		<fmt:message key="userinfo_gender"></fmt:message>: ${user.gender } <br />  
		<fmt:message key="userinfo_rolename"></fmt:message>: ${user.nameRole } <br />


		<%@page import="ua.nure.filonitch.summarytask.beans.*,java.util.*"%>

		<h3><fmt:message key="admininfo_servicelist"></fmt:message></h3>

		<p style="color: red;">${errorString}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th>service_id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${serviceList}" var="service">
				<tr>
					<td>${service.service_id}</td>
					<td>${service.service_name}</td>
					<td>${service.service_description}</td>
					<td><input type="button" value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editService?service_id=${service.service_id}' "></td>
					<td><input type="button" value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteService?service_id=${service.service_id}' "></td>
				</tr>
			</c:forEach>

		</table>
		<input type="button" value="<fmt:message key="admininfo_newservice"></fmt:message>"
			onclick=" location.href='createService' ">
		<form action="adminInfo" method="get">
			<select name="op">
				<option value="1">Sort By Name(A-Z)
				<option value="2">Sort By Name(Z-A)
			</select> <input type="submit" value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

		<h3><fmt:message key="admininfo_tarifList"></fmt:message></h3>

		<p style="color: red;">${errorString}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Service_id</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${tarifList}" var="tarif">
				<tr>
					<td>${tarif.code}</td>
					<td>${tarif.name}</td>
					<td>${tarif.price}</td>
					<td>${tarif.description}</td>
					<td>${tarif.service_id}</td>
					<td><input type="button" value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editTarif?code=${tarif.code}' "></td>
					<td><input type="button" value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteTarif?code=${tarif.code}' "></td>
				</tr>
			</c:forEach>
		</table>

		<input type="button" value="<fmt:message key="admininfo_newTarif"></fmt:message>"
			onclick=" location.href='createTarif' ">
		<form action="adminInfo" method="get">
			<select name="opT">
				<option value="1">Sort By Price(9-1)
				<option value="2">Sort By Price(1-9)
				<option value="3">Sort By Code(A-Z)
				<option value="4">Sort By Code(Z-A)
				<option value="5">Sort By Service ID (9-1)
				<option value="6">Sort By Service ID(1-9)
				<option value="7">Sort By Name(A-Z)
				<option value="8">Sort By Name(Z-A)
			</select> <input type="submit" value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

		<h3><fmt:message key="admininfo_userlist"></fmt:message></h3>

		<p style="color: red;">${errorString1}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th>ID</th>
				<th>username</th>
				<th>Fullname</th>
				<th>Gender</th>
				<th>Balance</th>
				<th>Block Status</th>
				<th>Role</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.user_id}</td>
					<td>${user.userName}</td>
					<td>${user.fullname}</td>
					<td>${user.gender}</td>
					<td>${user.balance}</td>
					<td>${user.block_status}</td>
					<td>${user.nameRole }</td>
					<td><input type="button" value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editUser?user_id=${user.user_id}' "></td>
					<td><input type="button" value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteUser?user_id=${user.user_id}' "></td>
				</tr>
			</c:forEach>
		</table>

		<input type="button" value="<fmt:message key="admininfo_newUser"></fmt:message>"
			onclick=" location.href='createUser' ">
		<form action="adminInfo" method="get">
			<select name="opU">
				<option value="1">Sort By ID(9-1)
				<option value="2">Sort By ID(1-9)
				<option value="3">Sort By username(A-Z)
				<option value="4">Sort By username(Z-A)
			</select> <input type="submit" value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

		<h3><fmt:message key="admininfo_userstariffs"></fmt:message></h3>

		<p style="color: red;">${errorString1}</p>

		<table border="1" cellpadding="5" cellspacing="1" align="center">
			<tr>
				<th>ID User</th>
				<th>Tarif code</th>
				<th>Payment Status</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${userTarifList}" var="userTarif">
				<tr>
					<td>${userTarif.id_user}</td>
					<td>${userTarif.code}</td>
					<td>${userTarif.payment_status}</td>
					<td><input type="button" value="<fmt:message key="admininfo_edit"></fmt:message>"
						onclick=" location.href='editUserTarif?id_user=${userTarif.id_user}&code=${userTarif.code }' "></td>
					<td><input type="button" value="<fmt:message key="admininfo_delete"></fmt:message>"
						onclick=" location.href='deleteUserTarif?id_user=${userTarif.id_user}&code=${userTarif.code }' "></td>

				</tr>
			</c:forEach>
		</table>

		<input type="button" value="<fmt:message key="admininfo_connectuser"></fmt:message>"
			onclick=" location.href='createUserTarif' ">
		<form action="adminInfo" method="get">
			<select name="opUT">
				<option value="1">Sort By ID User(9-1)
				<option value="2">Sort By ID User(1-9)
				<option value="3">Sort By Code(A-Z)
				<option value="4">Sort By Code(Z-A)
			</select> <input type="submit" value="<fmt:message key="admininfo_sort"></fmt:message>">
		</form>

	</div>
	<br />
	<br />
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>