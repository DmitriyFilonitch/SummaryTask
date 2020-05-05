<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ include file="/WEB-INF/include/head.jspf"%>
<center>
	<div class="page">
		<nav class="menu">
			<ul class="menu__list">
				<c:choose>
					<c:when test="${user.role_id == 1 }">
						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/" class="menu__link"><fmt:message key="navbar_home"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/tarifList"
							class="menu__link"><fmt:message key="navbar_tariflist"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/userInfo"
							class="menu__link"><fmt:message key="navbar_userinfo"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/login"
							class="menu__link"><fmt:message key="navbar_login"></fmt:message></a></li>


						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/adminInfo"
							class="menu__link"><fmt:message key="navbar_admininfo"></fmt:message></a></li>

					</c:when>
					<c:when test="${user.role_id == 2 }">
						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/" class="menu__link"><fmt:message key="navbar_home"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/tarifList"
							class="menu__link"><fmt:message key="navbar_tariflist"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/userInfo"
							class="menu__link"><fmt:message key="navbar_userinfo"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/login"
							class="menu__link"><fmt:message key="navbar_login"></fmt:message></a></li>
					</c:when>
					<c:otherwise>
						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/" class="menu__link"><fmt:message key="navbar_home"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/tarifList"
							class="menu__link"><fmt:message key="navbar_tariflist"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/userInfo"
							class="menu__link"><fmt:message key="navbar_userinfo"></fmt:message></a></li>

						<li class="menu__group"><a
							href="${pageContext.request.contextPath}/login"
							class="menu__link"><fmt:message key="navbar_login"></fmt:message></a></li>

					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</center>
