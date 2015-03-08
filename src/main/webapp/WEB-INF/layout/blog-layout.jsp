<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/blog.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
![endif]-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Fugiczek">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
	<div class="blog-masthead">
		<div class="container">
			<nav class="blog-nav">
				<a class="blog-nav-item active" href="<spring:url value="/blog" />">Home</a>
				<a class="blog-nav-item" href="#">O blogu</a>
				<a class="blog-nav-item" href="<spring:url value="/" />">Hlavni sranka</a>
				<security:authorize access="! isAuthenticated()">
					<a class="blog-nav-item" href="<spring:url value="/login" />">Login</a>
					<a class="blog-nav-item" href="<spring:url value="/register" />">Register</a>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<a class="blog-nav-item" href="<spring:url value="/logout" />">Logout</a>
				</security:authorize>
			</nav>
		</div>
	</div>

	<div class="container">

		<div class="blog-header">
			<h1 class="blog-title">
				<tiles:getAsString name="title"></tiles:getAsString>
			</h1>
			<p class="lead blog-description">Nejaky psani od demonu a
				parku...</p>
		</div>

		<c:if test="${success eq true}">
			<div class="alert alert-success">Registration successful! You can login now.</div>
		</c:if>

		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<footer class="blog-footer">
		<p>&copy; 2015 Fugiczek</p>
		<p>
			<a href="#">Back to top</a>
		</p>
	</footer>
</body>
</html>