<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="col-sm-8 blog-main">

	<div class="blog-post">
		<h2 class="blog-post-title">
			<c:out value="${post.title}" />
		</h2>
		<p class="blog-post-meta">
			${post.publishedDate} by <a href="#"><c:out
					value="${post.user.name}" /></a>
		</p>
		<c:out value="${post.text}" />
	</div>


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