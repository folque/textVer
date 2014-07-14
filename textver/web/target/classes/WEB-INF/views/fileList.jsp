<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align="center">
	<h2>Documents:</h2>
	<br />
	<c:forEach items="${forms}" var="form">
		<a
			href="<c:url value="/viewFile/${form.documentId}/${form.version }"/>">${form.name}</a>
		<br />
	</c:forEach>
</div>
