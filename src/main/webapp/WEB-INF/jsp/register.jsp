<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Fugiczek">

<title><tiles:getAsString name="title"></tiles:getAsString></title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="/resources/css/signin.css" rel="stylesheet">

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
    <![endif]-->
</head>

<body>	
<div class="container">
	<form:form commandName="user" cssClass="form-horizontal registrationForm">
	
		<h1>Registration</h1>
	
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control"/>
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email:</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email" />
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password:</label>
			<div class="col-sm-10">
				<form:password path="password" cssClass="form-control"/>
				<form:errors path="password" />
			</div>
		</div>
		<div class="form-group">
			<label for="password_again" class="col-sm-2 control-label">Password Again:</label>
			<div class="col-sm-10">
				<input type="password" name="password_again" id="password_again" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="submit" value="Save" class="btn btn-lg btn-primary" />
			</div>
		</div>
	</form:form>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('.registrationForm').validate(
			{
				rules: {
					name: {
						required: true,
						minlength: 3,
						remote: {
							url: "<spring:url value='/register/available' />",
							type: "get",
							data: {
								username: function() {
									return $('#name').val();
								}
							}
						}
					},
					email: {
						required: true,
						email: true
					},
					password: {
						required: true,
						minlength: 5
					},
					password_again: {
						required: true,
						minlength: 5,
						equalTo: "#password"
					}
				},
				highlight: function(element) {
					$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
				},
				unhighlight: function(element) {
					$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
				},
				messages: {
					name: {
						remote: "Such username already exists!"
					}
				}
			}		
		);
	});
	</script>
</div>
</body>
</html>
