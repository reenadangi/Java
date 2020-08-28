<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:wrapper>


	<div class="d-flex p-2">
		
		<div class="d-inline-block bg-warning align-top m-1 w-50 p-2">
		<div>
			
			<p class="h3">All about <span class="text-info">${event.name}</span></p>
			<p>Location: ${event.location }</p>
			
		
			<p>Date:<fmt:formatDate value="${event.eventDate}"
							pattern="yyyy-MM-dd" /></p>
			<p>Host: ${event.user.firstName }</p>
		</div>
		<h3>Guest List</h3>
		<c:forEach items="${event.attendees}" var="attendee">
			<p> ${attendee.firstName}</p>
		</c:forEach>
		</div>
		
		<div class="d-inline-block align-top m-1 w-50">
			<h4>Messages</h4>
			<c:forEach items="${event.messages}" var="message">
				<p>${message.comment}
					<span class="text-warning"> --${message.messenger.firstName}</span>
				</p>
				<hr />
			</c:forEach>

			<form:form action="/events/message" method="post"
				modelAttribute="message">
				<div class="form-group">
					<form:label path="comment">Message</form:label>
					<form:errors path="comment" />
					<form:textarea class="form-control" path="comment"></form:textarea>
				</div>
				<form:input type="hidden" class="form-control" path="event"
					value="${event.id}" />
				<form:input type="hidden" class="form-control" path="messenger"
					value="${user.id}" />
					<button type="submit">add</button>
			</form:form>


		</div>
	</div>
</t:wrapper>