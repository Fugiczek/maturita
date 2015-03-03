<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<h1 class="page-header">Users</h1>

<h2 class="sub-header">List of Users</h2>

<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>Name</th>
				<th>Email</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
			<tr>
				<td>
					<c:out value="${user.id}" />
				</td>
				<td>
					<c:out value="${user.name}" />
				</td>
				<td>
					<c:out value="${user.email}" />
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
