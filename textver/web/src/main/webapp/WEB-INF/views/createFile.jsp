<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<form:form method="POST" modelAttribute="fileForm">
	<form:input path="name" placeholder="File name"/>
	<form:errors path="name"/>
	
	<form:input path="title" placeholder="Title" />
	
	<form:textarea path="content" rows="15" cols="30" placeholder="Content" />
	<form:errors path="content"/>
	
	<form:input path="version" placeholder="Version"/>
	<form:errors path="version"/>
	<br/>
	<form:textarea path="description" rows="5" cols="30" placeholder="Description"/>
	<br />
	<input type="submit" value="Create" />
</form:form>