<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<h3>Current Maintenance</h3>
<font color="#bb7777" size="3px">${noSuccessMessage}</font>
<font color="#bb7777" size="3px">${successMessage}</font>
<font color="#bb7777" size="3px">${noRecord}</font>
<form:form method="GET"
	action="${pageContext.request.contextPath}/admin/booking/current">

	<c:if test="${fn:length(bookingList) gt 0}">
		<table style="cellspacing: 2; cellpadding: 2; border: 1;">

			<tr class="listHeading">
				<th><spring:message code="fieldLabel.bookingId" /></th>
				<th><spring:message code="fieldLabel.date" /></th>
				<th><spring:message code="fieldLabel.username" /></th>
				<th><spring:message code="fieldLabel.bFacilityName" /></th>
				<th><spring:message code="fieldLabel.description" /></th>
				<th><spring:message code="caption.operations" /></th>
			</tr>

			<c:forEach var="booking" items="${bookingList}">
				<tr class="listRecord">
					<td align="center">${booking.bookingId}</td>
					<td align="center"><fmt:formatDate value="${booking.date}"
							pattern="yyyy-MM-dd" /></td>
					<td align="center">${booking.user.userName}</td>
					<td align="center">${booking.facility.facilityName}</td>
					<td align="center">${booking.facility.description}</td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/admin/booking/current/edit/${booking.bookingId}.html"><spring:message
								code="caption.edit" /></a> <a
						href="${pageContext.request.contextPath}/admin/booking/current/delete/${booking.bookingId}.html"><spring:message
								code="caption.delete" /></a></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>
</form:form>