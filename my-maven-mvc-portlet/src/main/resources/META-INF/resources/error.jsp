<%@ include file="init.jsp" %>
<%
   String backURL	= (String) request.getAttribute("backURL");  
%>
<p>  Sorry the service is temporarily unavailable<p>
<a href=<%=backURL %>>back</a>