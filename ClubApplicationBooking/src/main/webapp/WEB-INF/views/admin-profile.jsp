<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>Your Profile</h3>
<font color="#bb7777" size="3px">${message}</font>
<form:form method="POST" modelAttribute="user"
	action="${pageContext.request.contextPath}/admin/user/profile/edit/${user.userId}.html">
	<table>
		<tbody>
			<tr>
				<td><spring:message code="fieldLabel.userId" /></td>
				<td><form:input path="userId" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.userName" /></td>
				<td><form:input path="userName" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.phone" /></td>
				<td><form:input path="phone" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.email" /></td>
				<td><form:input path="email" readonly="true" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="Edit"
					name="editProfile" class="blcass" /></td>
			</tr>
		</tbody>
	</table>
</form:form>
