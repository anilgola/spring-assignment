

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
	<form:form modelAttribute="ticketRequestModel" action="/submit-ticket">
		<h3>Enter the following details to raise the tickets</h3>
		<div class="form-group">
			<label for="subject">Subject</label>
			<form:input class="form-control" path="subject" />
		</div>

		<div class="form-group">
			<label for="description">Description</label>
			<form:input class="form-control" path="description" />
		</div>



		<input type="submit" value="submit" class="btn btn-primary" />
	</form:form>
	<a href="/dashboard">Dashboard</a>
</div>

</body>
</html>


