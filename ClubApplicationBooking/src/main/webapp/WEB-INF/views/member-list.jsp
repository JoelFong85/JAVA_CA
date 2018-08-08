<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>Member List page</h3>
<font color="#bb7777" size="3px">${message}</font>
<form:form method="GET"
	action="${pageContext.request.contextPath}/admin/member/create">
	<input type="submit" value="Add Member" class="blcass" />
	<c:if test="${fn:length(memberList) gt 0}">
		<table style="cellspacing: 2; cellpadding: 2; border: 1;">
			<tr class="listHeading">
				<th><spring:message code="fieldLabel.userId" /></th>
				<th><spring:message code="fieldLabel.name" /></th>
				<th><spring:message code="fieldLabel.phone" /></th>
				<th><spring:message code="fieldLabel.email" /></th>
				<th><spring:message code="caption.operations" /></th>
			</tr>
			<c:forEach var="member" items="${memberList}">
				<tr class="listRecord">
					<td align="left">${member.userId}</td>
					<td align="left">${member.userName}</td>
					<td align="left">${member.phone}</td>
					<td align="left">${member.email}</td>
					<td align="center"><a
						href="${pageContext.request.contextPath}/admin/member/edit/${member.userId}.html"><spring:message
								code="caption.edit" /></a> <a
						href="${pageContext.request.contextPath}/admin/member/delete/${member.userId}.html"><spring:message
								code="caption.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</form:form>