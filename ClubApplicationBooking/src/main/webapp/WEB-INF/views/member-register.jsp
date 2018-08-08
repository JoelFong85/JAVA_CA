<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>New Member page</h3>
<form:form method="POST" modelAttribute="user"
	action="${pageContext.request.contextPath}/member/register.html">
	
	<table>
		<tbody>
			<tr>
				<td><spring:message code="fieldLabel.name" /></td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.password" /></td>
				<td><form:input path="password" type="password"/></td>
				<td><form:errors path="password" cssStyle="color: red;" /></td>
			</tr>			
			<tr>
				<td><spring:message code="fieldLabel.phone" /></td>
				<td><form:input path="phone" pattern="\d*" /></td>
				<td><form:errors path="phone" cssStyle="color: red;" /></td>
			</tr>
				<tr>
				<td><spring:message code="fieldLabel.email" /></td>
				<td><form:input path="email"/></td>
				<td><form:errors path="email" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sign Up" class="blcass" name="register"/></td>
				<td><input type="submit" value="Cancel" class="blcass" name="cancel"/></td>
			</tr>
		</tbody>
	</table>
</form:form>

