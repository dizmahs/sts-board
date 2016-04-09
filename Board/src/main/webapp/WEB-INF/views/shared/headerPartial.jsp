<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>

<!-- header-left-menu -->
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="index.html">SB Admin v2.0</a>
</div>
<!-- /.navbar-header -->

<!-- header-right-menu -->
<ul class="nav navbar-top-links navbar-right">
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
			<i class="fa fa-user fa-fw"></i>
			<i class="fa fa-caret-down"></i>
		</a>
		<ul class="dropdown-menu dropdown-user">
			<c:choose>
    			<c:when test="${not empty sessionScope.sessionInfo.id}">
    				<li>
    					<a href="#">
							<i class="fa fa-user fa-fw"></i> ${sessionScope.sessionInfo.name}
						</a>
					</li>
				
					<li class="divider"></li>
					
					<li>
						<a href="/bbs/join/logout.html">
							<i class="fa fa-sign-out fa-fw"></i>Logout
						</a>
					</li>
    			</c:when>
    			<c:otherwise>
    				<li>
						<a href="/bbs/join/login.html">
							<i class="fa fa-sign-in fa-fw"></i>Login
						</a>
					</li>    			
    			</c:otherwise>
			</c:choose>
		</ul>
		<!-- /.dropdown-user -->
	</li>
</ul>
