<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>


<%
	JSONArray states = (JSONArray)request.getAttribute("states");
	 System.out.println("states::::::"+states);
 %>

<%-- <portlet:resourceURL var="testAjaxResourceUrl"></portlet:resourceURL> --%>

<portlet:renderURL var="getStatesJsonURL">
	<portlet:param name="mvcRenderCommandName"
		value="/state/get" />
	<portlet:param name="redirect" value="${currentURL}" />
</portlet:renderURL>

<p>
	<b><liferay-ui:message key="my-maven-mvc-portlet.caption"/></b>
	
	<table class="table">
    <thead>
      <tr>
        <th>State</th>
        </tr>
       </thead> 
       <tbody>
      <tr>
        <td><%=states %></td>
        </tr>
       </tbody>
    </table>
</p>
<input class="btn btn-primary" onClick="location.href = '${getStatesJsonURL}'" value="Show States"/>
	

<script src="https://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/main.js" ></script> --%>
<link href="https://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css" rel="stylesheet"></link>

<div id="shipping">
  <p>
  
    <select name="state" id="state">
      <option value="">Choose a state...</option>
<%--       <c:forEach var="states" items="${states}" > --%>
<%--             <option value="${states.code}"> --%>
<%--                 <c:out value="${states.name}"/> --%>
<!--             </option> -->
<%-- 	 </c:forEach>	 --%>
<%-- 	 <c:forEach var="states" items="${states}" > --%>
<%--     <td align="center"><c:out value="${states.code}" /></td> --%>
<%--     <td align="center"><c:out value="${states.name}" /></td>   --%>
<%-- </c:forEach>  <!-- you are missing this bit --> --%>
    </select>
  </p>
  <p>
    <select name="city" id="city">
      <option value="">Choose a city...</option>
    </select>
  </p>
</div>