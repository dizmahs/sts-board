<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> --%>   

<%@ page session="true" %>

<c:set var="targetUrl" value="${sessionScope.sessionData.targetUrl}" />
<c:set var="date" value="<%=new java.util.Date()%>" />


<% 	
	pageContext.setAttribute("LF", "\n"); 

%>

