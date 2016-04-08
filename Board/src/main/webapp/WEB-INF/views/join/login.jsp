<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="row">

	<form:form class="form-signin" id="loginForm" method="post" action="/bbs/join/login.html" modelAttribute="loginBean">
		<font color="red">${message}</font>
        
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" 
										id="txtID" 
										name="id" 
										placeholder="id"
										value="${loginBean.id}"
										required autofocus/> 
                                </div>
                                <div class="form-group">
                                    <input class="form-control" 
										id="txtPassword"
										name="password" 
										placeholder="Password"
										type="password" 
										required />
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
	</form:form>

</div>
<!-- /row -->

