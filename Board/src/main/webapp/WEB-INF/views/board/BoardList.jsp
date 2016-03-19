<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board list</title>
</head>
<body>

<div class="container">

<div id="container-fluid">
<div class="row">
    <div class="col-sm-6" style="background-color:lavender;">
    	<div class="btn-group">
		  <div class="btn-group">
		  	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
		  	10개 보기<span class="caret"></span></button>
		  	<ul class="dropdown-menu" role="menu">
		  		<li>20개 보기</li>
		  		<li>30개 보기</li>
		  	</ul>
		  </div>
		</div>
    </div>
    <div class="col-sm-6" style="background-color:lavenderblush;">
   		<div class="btn-group">
		    <button type="button" class="btn btn-primary">Sony</button>
		    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
		      <span class="caret"></span>
		    </button>
		    <ul class="dropdown-menu" role="menu">
		      <li><a href="#">Tablet</a></li>
		      <li><a href="#">Smartphone</a></li>
		    </ul>
		  </div>
    	
    	<button type="button" class="btn btn-info">
	      	<span class="glyphicon glyphicon-search"></span> Search
	    </button>
    </div>
  </div>
</div>

<table class="table table-striped">
  <thead>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>내용</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>테스트</td>
      <td>테스트내용</td>
      <td>작성자</td>
      <td>작성일</td>
      <td>조회수</td>
    </tr>
  </tbody>
</table>
</div>
</body>
</html>