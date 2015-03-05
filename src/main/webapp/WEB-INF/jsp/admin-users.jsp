<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	$('.triggerRemove').click(function(e) {
		e.preventDefault();
		$('#modalRemove .removeBtn').attr("href", $(this).attr("href"));
		$('#modalRemove').modal();
	});
});
</script>

<h1 class="page-header">Users</h1>

<h2 class="sub-header">List of Users</h2>

<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
			<tr>				
				<td>
					<c:out value="${user.name}" />
				</td>
				<td>
					<c:out value="${user.email}" />
				</td>
				<td>
					<a
						href='<spring:url value="/admin/users/remove/${user.id}" />'
						class="btn btn-danger triggerRemove"> Remove User </a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove User</h4>
      </div>
      <div class="modal-body">
        Really remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>