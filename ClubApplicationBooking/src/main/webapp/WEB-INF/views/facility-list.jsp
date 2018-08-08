<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>Facility List page</h3>

<form:form method="GET"
	action="${pageContext.request.contextPath}/admin/facility/create">
	<input type="submit" value="Add Facility" class="blcass" />
	<font color="Blue" size="3px">${message}</font>
	<c:if test="${fn:length(facilityList) gt 0}">
		<table style="cellspacing: 2; cellpadding: 2; border: 1;">

			<tr class="listHeading">
				<th><spring:message code="fieldLabel.id" /></th>
				<th><spring:message code="fieldLabel.name" /></th>
				<th><spring:message code="fieldLabel.facilityDes" /></th>
				<th><spring:message code="fieldLabel.facilityPrice" /></th>
				<th><spring:message code="caption.operations" /></th>
			</tr>

			<c:forEach var="facility" items="${facilityList}">
				<tr class="listRecord">
					<td align="left">${facility.facilityId}</td>
					<td align="left">${facility.facilityName}</td>
					<td align="left">${facility.description}</td>
					<td align="left">${facility.price}</td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/admin/facility/edit/${facility.facilityId}.html"><spring:message
								code="caption.edit" /></a> <a
						href="${pageContext.request.contextPath}/admin/facility/delete/${facility.facilityId}.html"><spring:message
								code="caption.delete" /></a></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>
</form:form>