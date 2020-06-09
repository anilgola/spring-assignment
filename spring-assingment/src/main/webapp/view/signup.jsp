<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>

<div class="container">
	<h3>Sign up</h3>
	<form:form modelAttribute="signup" method="POST"
		action="/submit-signup">
		
		<div class="form-group">
			<label for="username">username</label>
			<form:input class="form-control" path="username" />
		</div>

		<div class="form-group">
			<label for="password">password</label>
			<form:password class="form-control" path="password" />
		</div>
		
		
		<input type="submit" class="btn btn-primary" value="submit" />

	</form:form>
	</div>
</html>

