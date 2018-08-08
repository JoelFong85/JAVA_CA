<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>New Member page</h3>
<font color="#bb7777" size="3px">${message}</font>
<form:form method="POST" modelAttribute="user"
	action="${pageContext.request.contextPath}/admin/member/create.html">
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
				<td><spring:message code="fieldLabel.role" /></td>
				<td><form:input path="role" value="member" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.phone" /></td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.email" /></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create" class="blcass"
					name="create" /></td>
				<td><input type="submit" value="Cancel" class="blcass"
					name="cancel" /></td>
			</tr>
		</tbody>
	</table>
</form:form>

