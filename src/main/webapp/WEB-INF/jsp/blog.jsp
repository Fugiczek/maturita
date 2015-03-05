<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="col-sm-8 blog-main">

	<c:forEach items="${posts}" var="post">
		<div class="blog-post">
			<h2 class="blog-post-title">
				<a href='<spring:url value="/blog/show/${post.id}" />'> <c:out
						value="${post.title}" />
				</a>
			</h2>
			<p class="blog-post-meta">
				${post.publishedDate} by <a href="#"><c:out
						value="${post.user.name}" /></a>
			</p>
			<c:out value="${post.text}" />
		</div>
	</c:forEach>

	<ul class="pagination">
		<%
			long count = (long) request.getAttribute("count");
			int currentPage = (int) request.getAttribute("currentPage");

			if (currentPage == 1) {
				out.println("<li><a class=\"disable\">&laquo;</a></li>");
			} else {
				out.println("<li><a href=\"/blog/" + (currentPage - 1)
						+ "\">&laquo;</a></li>");
			}

			for (int i = 1; i <= Math.ceil(count / 10.0); i++) {
				out.println("<li"
						+ ((i == currentPage) ? " class=\"active\"" : "")
						+ "><a href=\"/blog/" + i + "\">" + i + "</a></li>");
			}

			if (currentPage == Math.ceil(count / 10.0)) {
				out.println("<li><a class=\"disable\">&raquo;</a></li>");
			} else {
				out.println("<li><a href=\"/blog/" + (currentPage + 1)
						+ "\">&raquo;</a></li>");
			}
		%>
	</ul>

</div>
<!-- /.blog-main -->

<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
	<div class="sidebar-module sidebar-module-inset">
		<h4>O mne</h4>
		<p>Tady napisu neco peknyho o sobe a budu to nacitat z DB :))))</p>
	</div>
	<div class="sidebar-module">
		<h4>Archiv(nefunguje to)</h4>
		<ol class="list-unstyled">
			<li><a href="#">February 2015</a></li>
			<li><a href="#">January 2015</a></li>
		</ol>
	</div>
</div>
<!-- /.blog-sidebar -->