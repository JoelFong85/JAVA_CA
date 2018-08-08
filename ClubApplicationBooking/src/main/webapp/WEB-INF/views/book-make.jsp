<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "dd/mm/yy",
			minDate : 0
		});
	});
</script>

<h3>Maintenance Booking Page</h3>


<c:if test="${fn:length(bookingList) gt 0}">
	<table>
		<tr>
			<td><font size="2px">Unavailable Dates:</font></td>
			<c:forEach var="booking" items="${bookingList}">
				<td><font size="2px"><fmt:formatDate
							value="${booking.date}" pattern="dd-MM-yyyy" /></font>
			</c:forEach>
		</tr>
	</table>
</c:if>
<br>
<form:form method="POST" modelAttribute="booking"
	action="${pageContext.request.contextPath}/${sessionScope.USERSESSION.user.role}/booking/maintenance/book.html">
	<c:choose>
		<c:when test="${sessionScope.USERSESSION.user.role eq 'admin'}">
			<form:input type="hidden" path="type" value="A" />
		</c:when>
		<c:when test="${sessionScope.USERSESSION.user.role eq 'member'}">
			<form:input type="hidden" path="type" value="M" />
		</c:when>
	</c:choose>

	<form:input type="hidden" path="facility.facilityId" />

	<!-- For SlotId of Hourly Slots  -->
	<%-- <form:input type="hidden" path="slotId" value="1" /> --%>


	<table>
		<tbody>
			<tr>
				<td><spring:message code="fieldLabel.userId" /></td>
				<td><form:input path="user.userId"
						value="${sessionScope.USERSESSION.user.userId}" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.userName" /></td>
				<td><form:input path="user.userName"
						value="${sessionScope.USERSESSION.user.userName}" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.bFacilityName" /></td>
				<td><form:input path="facility.facilityName" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.bFacilityPrice" /></td>
				<td><form:input path="facility.price" readonly="true" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.dateTimePicker" /></td>
				<td><form:input size="16" path="date" id="datepicker" /></td>
				<td><form:errors path="date" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><spring:message code="fieldLabel.bTimeSlot" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Book" name="book"
					class="blcass" /></td>
				<td><input type="submit" value="Cancel" name="cancel"
					class="blcass" /></td>
			</tr>
		</tbody>
	</table>

</form:form>