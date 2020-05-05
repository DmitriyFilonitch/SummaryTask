<%@ include file="/WEB-INF/include/head.jspf"%>
<div style="height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;">
<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resources"/>

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<%-- goto back to the settings--%>
<jsp:forward page="settings.jsp"/>

</div>