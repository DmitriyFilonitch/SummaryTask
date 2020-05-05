<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/head.jspf"%>
<div style="background: #abdfff; height: 0px; padding: 0;">
	<div style="float: left">

		<h1>
			<fmt:message key="main_name_header"></fmt:message>
		</h1>
	</div>
	
	
	
	
	<div style="float: right; padding: 5px; text-align: right;">
		<!-- User store in session with attribute: loginedUser -->

		<input type="button"
			value="<fmt:message key="header_language"></fmt:message>"
			onclick=" location.href='index.jsp' ">

		<fmt:message key="userinfo_hello"></fmt:message>
		<b>${loginedUser.userName}</b> <br />
		<fmt:message key="header_search"></fmt:message>
		<input name="search">
	</div>
</div>