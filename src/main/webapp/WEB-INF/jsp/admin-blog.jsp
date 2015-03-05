<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1 class="page-header">Blog</h1>

<h2 class="sub-header">List of Blog Posts</h2>

<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Published</th>
				<th>Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${posts}" var="post">
				<tr>
					<td><c:out value="${post.title}" /></td>
					<td><c:out value="${post.publishedDate}" /></td>
					<td><a
						href='<spring:url value="/admin/blog/remove/${post.id}" />'
						class="btn btn-danger triggerRemove"> Remove Post </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination">		
		<%
			long count = (long) request.getAttribute("count");
			int currentPage = (int) request.getAttribute("currentPage");

			if(currentPage == 1) {
				out.println("<li><a class=\"disable\">&laquo;</a></li>");
			} else {
				out.println("<li><a href=\"/admin/blog/" + (currentPage - 1) + "\">&laquo;</a></li>");
			}
			
			for (int i = 1; i <= Math.ceil(count / 10.0); i++) {
				out.println("<li"
						+ ((i == currentPage) ? " class=\"active\"" : "")
						+ "><a href=\"/admin/blog/" + i + "\">" + i
						+ "</a></li>");
			}
			
			if(currentPage == Math.ceil(count / 10.0)) {
				out.println("<li><a class=\"disable\">&raquo;</a></li>");
			} else {
				out.println("<li><a href=\"/admin/blog/" + (currentPage + 1) + "\">&raquo;</a></li>");
			}
		%>
	</ul>
</div>