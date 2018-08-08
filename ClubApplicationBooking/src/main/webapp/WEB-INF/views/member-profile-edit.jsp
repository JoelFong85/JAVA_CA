<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Edit Profile</h3>
<form:form method="POST" modelAttribute="user"
	action="${pageContext.request.contextPath}/member/profile/edit/${user.userId}.html">

	<form:input type="hidden" path="userId" value="${userId}" />
	<form:input type="hidden" path="role" value="${role}" />

	<table>
		<tbody>
			<tr>
				<td><spring:message code="fieldLabel.name" /></td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.password" /></td>
				<td><form:input path="password" type="password" /></td>
				<td><form:errors path="password" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.phone" /></td>
				<td><form:input path="phone" pattern="\d*"/></td>
				<td><form:errors path="phone" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.email" /></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="update" value="Update"
					class="blcass" /></td>
				<td><input type="submit" name="cancel" value="Cancel"
					class="blcass" /></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</form:form>

