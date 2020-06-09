
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>
<body>

<div class="container">
<h3> Tickets for updation</h3>

<form:form modelAttribute="ticketResponseModel" action="/update-ticket">
	<form:hidden path="id"/>
	
	<div class="form-group">
		<label for="subject">Subject</label> 
		<form:input class="form-control" path="subject"/>
	</div>
	
	<div class="form-group">
		<label for="subject">description</label> 
		<form:input class="form-control" path="description"/>
	</div>
	
	
	
	<input type ="submit" class="btn btn-primary" value = "submit"/>
</form:form>

<a href="/dashboard">Dashboard</a> 
</div>

</body>
</html>