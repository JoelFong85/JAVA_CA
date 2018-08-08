<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Edit Facility Page</h3>
<form:form method="POST" modelAttribute="facility"
	action="${pageContext.request.contextPath}/admin/facility/edit/${facility.facilityId}.html">

	<table>
		<tbody>
			<tr>
				<td><spring:message code="fieldLabel.facilityName" /></td>
				<td><form:input path="facilityName"/></td>
				<td><form:errors path="facilityName" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.facilityDes" /></td>
				<td><form:input path="description"/></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.facilityPrice" /></td>
				<td><form:input path="price" pattern="\d*"/></td>
				<td><form:errors path="price" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update" name="update"
					class="blcass" /></td>
				<td><input type="submit" value="Cancel" name="cancel"
					class="blcass" /></td>
			</tr>
		</tbody>
	</table>
</form:form>

