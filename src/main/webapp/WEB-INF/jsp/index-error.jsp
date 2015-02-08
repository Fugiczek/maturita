<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<%@ page isErrorPage="true" %>

<div class="jumbotron">
	<h1>Error</h1>
	<p><c:out value="${errorMessage}" /></p>
</div>
