<%@page import="javax.servlet.jsp.jstl.core.Config"%>
<%@ include file="/WEB-INF/include/head.jspf"%>

<html>
<body>
<div style="height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;">
    
    <input type="button" value="<fmt:message key="index_jsp.link.settings"/>"
						onclick=" location.href='settings.jsp' ">
    

	</div>
	
</body>
</html>
