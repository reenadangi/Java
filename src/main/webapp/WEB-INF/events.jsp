<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<t:wrapper>
	<h3>
		Welcome <span class="text-info"> ${user.firstName},
			${user.location.location}</span>
	</h3>
	<h3>List of events in your area</h3>
	<table class="table-dark table">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">Location</th>
				<th scope="col">Host</th>
				<th scope="col">Attendees</th>
				<th scope="col">Action/status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${events}" var="event">
				<c:if test="${event.location.location==user.location.location }">
					<tr>
						<td><a href="/events/${event.id}">${ event.id} ${ event.name }</a></td>
						<td><fmt:formatDate value="${event.eventDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${ event.location.location}</td>
						<td>${ event.user.firstName }</td>
						<td class="text-warning">${event.attendees.size() }</td>

						<c:choose>
							<c:when test="${ event.user.id == user.id }">
								<td>
								<a class="text-info" href="/events/edit/${ event.id }">Edit</a>
								
								<form action="/events/${ event.id }" method="post">
									<input type="hidden" name="_method" value="delete" />
									<button class="btn btn-danger">Delete</button>
								</form>
								</td>
							</c:when>
							<c:when test="${ event.attendees.contains(user) }">
								<td><a href="/events/cancel/${ event.id }">Cancel</a></td>
							</c:when>
							<c:otherwise>
								<td><a href="/events/join/${event.id}">Join</a></td>
							</c:otherwise>
						</c:choose>

					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>

	<h3>List of events in other areas</h3>
	<table class="table-dark table">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
				<th scope="col">Location</th>
				<th scope="col">Host</th>
				<th scope="col">Attendees</th>
				<th scope="col">Action/status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${events}" var="event">
				<c:if test="${event.location.location!=user.location.location }">
					<tr>
						<td><a href="/events/${event.id}">${ event.id} ${ event.name }</a></td>
						<td><fmt:formatDate value="${event.eventDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${ event.location.location}</td>
						<td>${ event.user.firstName }</td>
						<td class="text-warning">${event.attendees.size() }</td>

						<c:choose>
							<c:when test="${ event.user.id == user.id }">
								<form action="/events/${ event.id }" method="post">
									<input type="hidden" name="_method" value="delete" />
									<td>
										<button class="btn btn-danger">Delete</button>
									</td>
								</form>
							</c:when>
							<c:when test="${ event.attendees.contains(user) }">
								<td><a href="/events/cancel/${ event.id }">Cancel</a></td>
							</c:when>
							<c:otherwise>
								<td><a href="/events/join/${event.id}">Join</a></td>
							</c:otherwise>
						</c:choose>

					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>

	<h3>Add your event ${user.id}</h3>
	<form:form action="/events" method="post" modelAttribute="event">
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
					<option value="${ location.id}">${ location.location }</option>
				</c:forEach>
			</form:select>
		</div>


		<button>Submit</button>
	</form:form>




</t:wrapper>