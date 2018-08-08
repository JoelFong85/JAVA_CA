<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/logout" var="logout" />
<ul>

	<c:choose>
		<c:when test="${sessionScope.USERSESSION.user.role eq 'admin'}">
			<li><spring:url value="/admin/booking/current" var="clist"
					htmlEscape="true" /> <a href="${clist}"> <spring:message
						code="menu.admin.currentlist" />
			</a></li>
			<li><spring:url value="/admin/booking/history" var="hlist"
					htmlEscape="true" /> <a href="${hlist}"> <spring:message
						code="menu.admin.historylist" />
			</a></li>
			<li><spring:url value="/facilities" var="mlist"
					htmlEscape="true" /> <a href="${mlist}"> <spring:message
						code="menu.admin.maintenancelist" />
			</a></li>
			<li><spring:url value="/admin/user/list" var="ulist"
					htmlEscape="true" /> <a href="${ulist}"> <spring:message
						code="menu.admin.adminlist" />
			</a></li>
			<li><spring:url value="/admin/member/list" var="melist"
					htmlEscape="true" /> <a href="${melist}"> <spring:message
						code="menu.admin.memberlist" />
			</a></li>
			<li><spring:url value="/admin/facility/list" var="flist"
					htmlEscape="true" /> <a href="${flist}"> <spring:message
						code="menu.admin.facilitylist" />
			</a></li>
			<li><spring:url value="/admin/user/profile" var="profile"
					htmlEscape="true" /> <a href="${profile}"> <spring:message
						code="menu.profile" />
			</a></li>
			<li><spring:url value="/logout" var="logout" htmlEscape="true" />
				<a href="${logout}"> <spring:message code="menu.logout" />
			</a></li>
		</c:when>
		<c:when test="${sessionScope.USERSESSION.user.role eq 'member'}">
			<li><spring:url value="/member/booking/current" var="mclist"
					htmlEscape="true" /> <a href="${mclist}"> <spring:message
						code="menu.member.currentlist" />
			</a></li>
			<li><spring:url value="/member/booking/history" var="mhlist"
					htmlEscape="true" /> <a href="${mhlist}"> <spring:message
						code="menu.member.historylist" />
			</a></li>
			<li><spring:url value="/facilities" var="mflist"
					htmlEscape="true" /> <a href="${mflist}"> <spring:message
						code="menu.member.maintenancelist" />
			</a></li>
			<li><spring:url value="/member/profile" var="profile"
					htmlEscape="true" /> <a href="${profile}"> <spring:message
						code="menu.profile" />
			</a></li>
			<li><spring:url value="/logout" var="logout" htmlEscape="true" />
				<a href="${logout}"> <spring:message code="menu.logout" />
			</a></li>
		</c:when>
	</c:choose>
</ul>