<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> --%>   

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/bongComm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/comm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/bootstrap.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/npm.js"></script> --%>

<link href="${pageContext.request.contextPath}/common/css/bootstrap.min.css" rel="stylesheet">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script type="text/javascript">
	<!--
	$(document).ready(function(){
		//우클릭 방지
		/*
		$(document).on("contextmenu", function(e){
			console.log("c" + e);
			return false;
		});
		
		//드래그 방지
		$(document).on("dragstart", function(e){
			console.log("d" + e);
			return false;
		});
		
		//선택 방지
		$(document).on("selectstart", function(e){
			console.log("s" + e);
			return false;
		});
		*/
		
		/*
		$(document).on("contextmenu dragstart selectstart", function(e){
		    return false;
		});
		*/
	});
	//-->
</script>
<%

	//baseUrl 
	final String ROOT_URL = request.getContextPath() == null || request.getContextPath().equals("")? "/" : request.getContextPath();
	request.setAttribute("ROOT_URL", ROOT_URL);
	
	// curent_url	
	final String current_url = (String) request.getAttribute("current_url");
	//final String current_url = (String) request.getRequestURI();

	// full_url
	String  full_url = (String) request.getAttribute("full_current_url");
	//final String full_current_url = Base64.encodeBase64String(full_url.getBytes()).trim();
	
	// 편재 페이지 js include
	String JS_NAME = request.getRequestURI().substring(ROOT_URL.length() - 1);
	JS_NAME = JS_NAME.replace("/WEB-INF/jsp/", "").replace(".jsp", ".js");
	
	// 현재 날짜
	//final String current_date = StringUtil.getCurrentDate();
%>

