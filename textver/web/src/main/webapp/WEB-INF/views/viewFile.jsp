<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<form:form method="POST" modelAttribute="fileForm">
	<form:input path="name" placeholder="File name" />
	<form:errors path="name"/>
	
	<form:input path="title" placeholder="Title" />
	
	<form:textarea path="content" rows="15" cols="30" placeholder="Content" />
	<form:errors path="content"/>
	
	<form:input path="version" placeholder="Version" />
	<form:errors path="version"/>

	<br/>
	<form:textarea path="description" rows="5" cols="30"
		placeholder="Description" />
	<br/>
	<input type="submit" name="action" value="Save" />
	<br/>
	<div align="right">by ${user.username }</div>
</form:form>

<br/>
<br/>
<hr>

<h3>Comments</h3>
<form:form method="POST" modelAttribute="commentForm">
	<form:textarea path="comment" placeholder="Insert comment" />
	<form:errors path="comment"/>
	
	<input type="submit" name="action" value="Comment" />
</form:form>
<br/>
<br/>
<div>
	<c:forEach items="${comments }" var="comm">
		<div align="right">${comm.user_id.username}</div>
		<div align="right">${comm.created }</div>
	${comm.comment }
	<hr>
	</c:forEach>
</div>