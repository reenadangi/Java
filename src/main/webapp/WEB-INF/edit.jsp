<%@ page isErrorPage="true" %>    

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<t:wrapper>

	<form:form action="/events/${event.id}" method="post" modelAttribute="event">
	<input type="hidden" name="_method" value="put">
	<form:hidden value="${ userId }" path="user"/>
		<div class="form-group">
			<form:label path="name">Name</form:label>
			<form:errors class="text-danger" path="name" />
			<form:textarea class="form-control" path="name"></form:textarea>
		</div>

		<div class="form-group">
			<form:label path="eventDate">Event Date</form:label>
			<form:errors class="text-danger" path="eventDate" />
			<form:input type="date" class="form-control" path="eventDate" />

		</div>

		<div class="form-group">
			<form:label path="location">Location</form:label>
			<form:errors class="text-danger" path="location" />
			<form:select path="location">
			
				<c:forEach items="${ locations }" var="location">
					<option value="${ location.id}">${ location.location } </option>
					
					
				</c:forEach>
				<option selected value="${ event.location.id }">${event.location.location }</option>
			</form:select>
		</div>


		<button>Submit</button>
	</form:form>
</t:wrapper>