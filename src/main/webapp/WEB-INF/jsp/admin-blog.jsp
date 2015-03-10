<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1 class="page-header">Blog</h1>

<c:if test="${success eq true}">
	<div class="alert alert-success">Blog Post was added successfully.</div>
</c:if>
<c:if test="${success eq false}">
	<div class="alert alert-failure">Blog Post was NOT added. Something went wrong.</div>
</c:if>

<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">New Blog Post</button>

<form:form commandName="blogPost" cssClass="form-horizontal blogForm" action="/admin/blog/add">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">New Post</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Title:</label>
						<div class="col-sm-10">
							<form:input path="title" cssClass="form-control" />
							<form:errors path="title" />
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-2 control-label">Text:</label>
						<div class="col-sm-10">
							<form:textarea path="text" cssClass="form-control" />
							<form:errors path="text" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$(document).ready(
			function() {
				$('.blogForm').validate(
						{
							rules : {
								title : {
									required : true,
									minlength : 1
								},
								text : {
									required : true,
									minlength : 50
								}
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}
						});
			});
</script>

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
					<td><a href='<spring:url value="/blog/show/${post.id}" />'>
							<c:out value="${post.title}" />
					</a></td>
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

			if (currentPage == 1) {
				out.println("<li><a class=\"disable\">&laquo;</a></li>");
			} else {
				out.println("<li><a href=\"/admin/blog/" + (currentPage - 1)
						+ "\">&laquo;</a></li>");
			}

			for (int i = 1; i <= Math.ceil(count / 10.0); i++) {
				out.println("<li"
						+ ((i == currentPage) ? " class=\"active\"" : "")
						+ "><a href=\"/admin/blog/" + i + "\">" + i
						+ "</a></li>");
			}

			if (currentPage == Math.ceil(count / 10.0)) {
				out.println("<li><a class=\"disable\">&raquo;</a></li>");
			} else {
				out.println("<li><a href=\"/admin/blog/" + (currentPage + 1)
						+ "\">&raquo;</a></li>");
			}
		%>
	</ul>
</div>