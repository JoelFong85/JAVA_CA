<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "dd/mm/yy",
			minDate: 0
		});
	});
</script>

<h3>Edit Booking Page</h3>
<form:form method="POST" modelAttribute="booking"
	action="${pageContext.request.contextPath}/admin/booking/current/edit/${booking.bookingId}.html">

	<table>
		<tbody>
			<form:input type="hidden" path="bookingId" />
			<form:input type="hidden" path="user.userId" />
			<form:input type="hidden" path="facility.facilityId" />
			<form:input type="hidden" path="type" />

			<tr>
				<td><spring:message code="fieldLabel.bFacilityName" /></td>
				<td><form:input path="facility.facilityName" readonly="true" /></td>
				<%-- <td><form:errors path="facilityName" cssStyle="color: red;" /></td> --%>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.dateTimePicker" /></td>
				<td><fmt:formatDate value="${booking.date}"
						pattern="dd/MM/yyyy" var="formattedDate" /> <form:input
						type="text" path="date" value="${formattedDate}" id="datepicker" /></td>
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

