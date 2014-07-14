<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align="center" style="vertical-align: text-top;">
	<h3>Version</h3>
	<c:forEach items="${versions}" var="ver">
		<a
			href="<c:url value="/viewFile/${fileForm.documentId}/${ver.version_name }"/>">${ver.version_name}</a>
		<br />
	</c:forEach>
</div>