<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/common/css/signin.css"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="container">

		<form:form class="form-signin" id="loginForm" method="post" action="./login" modelAttribute="loginBean">
			<font color="red">${message}</font>
			<h2 class="form-signin-heading">Please sign in</h2>
			
			<label for="txtID" class="sr-only">id</label> 
			
			<input class="form-control" 
				id="txtID" 
				name="id" 
				placeholder="id"
				value="${loginBean.id}"
				required autofocus/> 
				
			<label for="inputPassword" class="sr-only">Password</label> 
			<input class="form-control" 
				id="txtPassword"
				name="password" 
				placeholder="Password"
				type="password" 
				required />
				
			<div class="checkbox">
				<label> 
					<input type="checkbox" value="remember-me">Remember me
				</label>
			</div>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			
		</form:form>

	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>

<script type="text/javascript">
	
</script>