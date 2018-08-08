<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>New Facility page</h3>
<form:form method="POST" modelAttribute="facility"
	action="${pageContext.request.contextPath}/admin/facility/create.html">
	<table>
		<tbody>
			<tr>
				<td><spring:message code="fieldLabel.bFacilityName" /></td>
				<td><form:input path="facilityName" /></td>
				<td><form:errors path="facilityName" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.facilityDes" /></td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.facilityPrice" /></td>
				<td><form:input path="price" pattern="\d*" value="100"/></td>
				<td><form:errors path="price" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create" class="blcass" name="create"/></td>
				<td><input type="submit" value="Cancel" class="blcass" name="cancel"/></td>
			</tr>
		</tbody>
	</table>
</form:form>

