<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>Admin List page</h3>
<font color="#bb7777" size="3px">${message}</font>
<form:form method="GET"
	action="${pageContext.request.contextPath}/admin/user/create">
	<input type="submit" value="Add Admin" class="blcass" />
	<c:if test="${fn:length(adminList) gt 0}">
		<table style="cellspacing: 2; cellpadding: 2; border: 1;">
			<tr class="listHeading">
				<th><spring:message code="fieldLabel.id" /></th>
				<th><spring:message code="fieldLabel.name" /></th>
				<th><spring:message code="fieldLabel.phone" /></th>
				<th><spring:message code="fieldLabel.email" /></th>
				<th><spring:message code="caption.operations" /></th>
			</tr>
			<c:forEach var="admin" items="${adminList}">
				<tr class="listRecord">
					<td align="left">${admin.userId}</td>
					<td align="left">${admin.userName}</td>
					<td align="left">${admin.phone}</td>
					<td align="left">${admin.email}</td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/admin/user/edit/${admin.userId}.html"><spring:message
								code="caption.edit" /></a> <a
						href="${pageContext.request.contextPath}/admin/user/delete/${admin.userId}.html"><spring:message
								code="caption.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</form:form>