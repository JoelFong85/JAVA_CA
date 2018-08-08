<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!--  Search Function - Start-->
<script>
	$(document).ready(function() {
		$("#datepicker3").datepicker({
			dateFormat : "yy-mm-dd",
			maxDate : 0
		});
	});
	$(document).ready(function() {
		$("#datepicker4").datepicker({
			dateFormat : "yy-mm-dd",
			maxDate : 0
		});
	});
	/* To check the date input */

	/* function CheckIsNull() {
		var StartDate = document.getElementById('datepicker3').value;
		var EndDate = document.getElementById('datepicker4').value;
		if (StartDate == undefined || StartDate === '' || StartDate == null
				|| EndDate === '' || EndDate == null || EndDate == undefined) {
			alert("Start date and End date can not be null!")
			return false;
		} else {
			return true;
		}
	} */
</script>
<!--  Search Function - End-->
<h3>History Booking(s)</h3>
<font color="#bb7777" size="3px">${noRecord}</font>
<form:form method="GET"
	action="${pageContext.request.contextPath}/member/booking/history">

	<!--  Search Function - Start-->
	<table>
		<tr>
			<td><span>StartDate:</span><input type="text" name="startdate"
				id="datepicker3" /></td>
			<td><span>EndDate:</span><input type="text" name="enddate"
				id="datepicker4" /></td>
			<td><input type="submit" value="Submit" name="search" /></td>
			<!-- onclick="return CheckIsNull()" /></td> -->
			<td><font color="Red" size="3px">${message}</font></td>
		</tr>
	</table>
	<!--  Search Function - End-->

	<table style="cellspacing: 2; cellpadding: 2; border: 1;">

		<tr class="listHeading">
			<th><spring:message code="fieldLabel.bookingId" /></th>
			<th><spring:message code="fieldLabel.date" /></th>
			<th><spring:message code="fieldLabel.username" /></th>
			<th><spring:message code="fieldLabel.bFacilityName" /></th>
			<th><spring:message code="fieldLabel.description" /></th>
		</tr>

		<c:if test="${fn:length(bookingList) gt 0}">

			<c:forEach var="booking" items="${bookingList}">
				<tr class="listRecord">
					<td align="center">${booking.bookingId}</td>
					<td align="center"><fmt:formatDate value="${booking.date}"
							pattern="yyyy-MM-dd" /></td>
					<td align="center">${booking.user.userName}</td>
					<td align="center">${booking.facility.facilityName}</td>
					<td align="center">${booking.facility.description}</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</form:form>