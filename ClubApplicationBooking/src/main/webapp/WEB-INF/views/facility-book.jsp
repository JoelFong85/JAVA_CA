<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>Available Facilities for Booking</h3>
<font color="#bb7777" size="3px">${message}</font>
<form:form method="GET">

	<!--  Search Function - Start-->
	<input type="text" name="searchcontent" />
	<input type="submit" value="Submit" name="search" />
	<!--  Search Function - End-->

	<table style="cellspacing: 2; cellpadding: 2; border: 1;">
		<tr class="listHeading">
			<th><spring:message code="fieldLabel.id" /></th>
			<th><spring:message code="fieldLabel.name" /></th>
			<th><spring:message code="fieldLabel.facilityDes" /></th>
			<th><spring:message code="fieldLabel.facilityPrice" /></th>
			<th><spring:message code="caption.operations" /></th>
		</tr>

		<c:if test="${fn:length(facilityList) gt 0}">


			<c:forEach var="facility" items="${facilityList}">
				<tr class="listRecord">
					<td align="left">${facility.facilityId}</td>
					<td align="left">${facility.facilityName}</td>
					<td align="left">${facility.description}</td>
					<td align="left">${facility.price}</td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/${sessionScope.USERSESSION.user.role}/booking/${facility.facilityId}.html"><spring:message
								code="caption.book" /></a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</form:form>