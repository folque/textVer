<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h2>
	<spring:message code="login.page.title" />
</h2>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<c:if test="${not empty error}">
	<div class="errorblock">
		<spring:message code="message.login.error"/><br/> <spring:message code="message.login.error.reason" />
		: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</c:if>

<form name='f' action="<c:url value='j_spring_security_check' />"
	method='POST'>

	<table>
		<tr>
			<td><spring:message code="label.user" /></td>
			<td><input type='text' name='j_username' value=''></td>
		</tr>
		<tr>
			<td><spring:message code="label.password" /></td>
			<td><input type='password' name='j_password' /></td>
		</tr>
		<tr>
		<td><spring:message code="label.rememberme" />:</td>
		<td><input type='checkbox' name='_spring_security_remember_me'/></td>
		</tr>
		<tr>
		<td colspan='1'><input name="reset" type="reset" value="<spring:message code="action.reset" />"/></td>
			<td colspan='1' align="right"><input name="submit" type="submit"
				value="<spring:message code="action.login" />" /></td>
		</tr>
	</table>

</form>


                    
                    