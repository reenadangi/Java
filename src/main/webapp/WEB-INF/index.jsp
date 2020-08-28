<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:wrapper>
	<p class="h3">Register here!</p>
	<div class="d-flex p-2">

		<div class="d-inline-block align-top w-50 p-2">
			<form:form id='demo-form' action="/" method="post"
				modelAttribute="user">

				<div class="form-group">
					<form:label path="firstName">First Name</form:label>
					<form:errors class="text-danger" path="firstName" />
					<form:input class="form-control" path="firstName" />
				</div>
				<div class="form-group">
					<form:label path="lastName">Last Name</form:label>
					<form:errors class="text-danger" path="lastName" />
					<form:input class="form-control" path="lastName" />
				</div>

				<div class="form-group">
					<form:label path="location">Location</form:label>
					<form:errors path="location" />
					<form:select path="location">
						<c:forEach items="${ locations }" var="location">
							<option value="${ location.id}">${ location.location }</option>
						</c:forEach>
					</form:select>
				</div>

				<div class="form-group">
					<form:label path="email">Email</form:label>
					<form:errors class="text-danger" path="email" />
					<form:input class="form-control" path="email" />
				</div>
				<div class="form-group">
					<form:label path="password">Password</form:label>
					<form:errors class="text-danger" path="password" />
					<form:input class="form-control" type="password" path="password" />
				</div>
				<div class="form-group">
					<form:label path="passwordConfirmation">Password Confirmation</form:label>
					<form:errors class="text-danger" path="passwordConfirmation" />
					<form:input type="password" class="form-control"
						path="passwordConfirmation" />
				</div>

				<div class="g-recaptcha"
					data-sitekey="6Lep19kUAAAAAEEfZw0mw698C6oCLDBYuv98my6M"
					data-callback='onSubmit'></div>
				<button id="btnSubmit" disabled>Submit</button>
			</form:form>

		</div>
		<div
			class="d-inline-block bg-warning align-top w-50 p-2 border border-dark">
				<span class="text-danger">${ error }</span>
			<form action="/login" method="post">
				<div class="form-group">
					<label for="email">Email</label> <input class="form-control"
						type="text" id="email" name="email" />
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input class="form-control"
						type="password" id="password" name="password" />

				</div>

				<button type="submit">Login</button>
				<div class="form-group"></div>

			</form>
			<div id="my-signin2"></div>


		</div>
	</div>
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>

	<script>
		function onSubmit(token) {
			document.getElementById("btnSubmit").disabled = false;
		}
	</script>

	<script>
		function onSuccess(googleUser) {
			console.log('Logged in as: '
					+ googleUser.getBasicProfile().getName()
					+ googleUser.getBasicProfile().getEmail());
		}
		function onFailure(error) {
			console.log(error);
		}
		function renderButton() {
			gapi.signin2.render('my-signin2', {
				'scope' : 'profile email',
				'width' : 240,
				'height' : 50,
				'longtitle' : true,
				'theme' : 'dark',
				'onsuccess' : onSuccess,
				'onfailure' : onFailure
			});
		}
	</script>

	<script
		src="https://apis.google.com/js/platform.js?onload=renderButton" async
		defer></script>


</t:wrapper>