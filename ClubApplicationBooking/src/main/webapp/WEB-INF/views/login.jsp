<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:url value="/css/simple.css" var="style" />
<link rel="STYLESHEET" type="text/css" href="${style}" />


<h3>Login Page</h3>
<a href="${pageContext.request.contextPath}/member/register"
	style="text-decoration: none;"> <input type="submit"
	value="Sign Up" class="blcass"></a>
<font color="#bb7777"> <c:if test="${not empty errorNullMessage}">
		<c:out value="${errorNullMessage}" />
	</c:if>
</font>

<form:form modelAttribute="user" method="POST"
	action="${pageContext.request.contextPath}/authenticate">
	<table class="framed">
		<tr>
			<td><spring:message code="fieldLabel.username" /></td>
			<td colspan="3"><form:input path="userName" size="40" /></td>
		</tr>
		<tr>
			<td><spring:message code="fieldLabel.password" /></td>
			<td colspan="3"><form:password path="password" size="40" /></td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><form:button name="submit" type="submit" value="s">
					<img
						src="${pageContext.request.contextPath}/image/button_login.gif"
						alt="" align="middle">
				</form:button></td>
			<td><form:button name="clear" type="reset" value="r">
					<img
						src="${pageContext.request.contextPath}/image/button_clear.gif"
						alt="" align="middle">
				</form:button></td>
		</tr>
	</table>
</form:form>