<%@ include file="/WEB-INF/include/head.jspf"%>

<html>
<body>
<div style="height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;">
	<form action="changeLocale.jsp" method="post">
		<fmt:message key="settings_jsp.label.set_locale"/>:
		<select name="locale">
			<c:forEach items="${applicationScope.locales}" var="locale">
				<c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
				<option value="${locale.key}" ${selected}>${locale.value}</option>
			</c:forEach>
		</select>
		
		
		<input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
		
	</form>
	
	<input type="button" value="<fmt:message key="settings_jsp.link.back_to_main_page"/>"
						onclick=" location.href='index.jsp' ">
	
<br/>
	
	 <input type="button" value="<fmt:message key="settings_jsp.link.homeView"/>"
						onclick=" location.href='${pageContext.request.contextPath}/home' ">
	
	</div>
</body>
</html>