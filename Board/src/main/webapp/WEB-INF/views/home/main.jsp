<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">	  
	<title>${title}</title>
	
<!-- BEGIN css -->
	<!-- Bootstrap Core CSS -->
	<link href="${pageContext.request.contextPath}/common/css/bootstrap.min.css" rel="stylesheet">

	<!-- MetisMenu CSS 
	<link href="${pageContext.request.contextPath}/common/css/metisMenu.min.css" rel="stylesheet">
	-->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/jquery.metismenu/2.5.0/metisMenu.min.css">
    
    
    <!-- Admin-2 CSS -->    
	<link href="${pageContext.request.contextPath}/common/css/sb-admin-2.css" rel="stylesheet">
	
	
    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<!-- Bootstrap-table CSS -->
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">

	<!-- 
	<link href="${pageContext.request.contextPath}/common/css/signin.css"rel="stylesheet">
	 -->
		
<!-- END css -->

<!-- BEGIN scripts -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/bongComm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/comm.js"></script>
	
	<script src="//cdn.jsdelivr.net/jquery/2.2.1/jquery.min.js"></script>
	<%-- 
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery-1.9.1.js"></script>
	--%>
	<!-- Bootstrap Core JavaScript -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript 
		<script src="text/javascript" src="${pageContext.request.contextPath}/common/js/metisMenu.min.js"></script>
	-->
    
    <script src="//cdn.jsdelivr.net/jquery.metismenu/2.5.0/metisMenu.min.js"></script>

	<!-- Admin-2 JavaScript -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/sb-admin-2.js"></script>
	
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/npm.js"></script> --%>

	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	
	<!-- Bootstrap-table JavaScript -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
	
	<!-- Bootstrap-table Locales 
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/locale/bootstrap-table-zh-CN.min.js"></script>
	-->
<!-- END scripts -->
</head>

<body>

	<div id="wrapper">
			
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

			<!-- header -->
			<%@ include file="/WEB-INF/views/shared/headerPartial.jsp" %>
			
			
			<!-- left sidebar -->
			<%@ include file="/WEB-INF/views/shared/leftMenuPartial.jsp" %>			
		</nav>
		<!-- /.navbar-static-side -->

		<!-- body jsp:include page="/bbs/content.html" flush="false"/ -->
		<div id="page-wrapper">
			<c:import url = "/bbs/content.html"></c:import>
		</div>

	</div>


	<!-- footer -->
	<%@ include file="/WEB-INF/views/shared/footerPartial.jsp" %>



</body>

</html>