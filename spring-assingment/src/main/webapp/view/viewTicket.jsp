
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
<h3>
	Tickets raised by you are as follow
</h3>
	<table class="table">


		<thead>
			<tr>
				<th>Subject</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="ticket" items="${ticketResponseModelList}">
				<tr>
					<td>${ticket.subject}</td>
					<td>${ticket.description}</td>
					<td><a href ="/get-ticket/${ticket.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>



	</table>
	
	<br/>
	
	<a href="/dashboard">Dashboard</a>

</div>
</body>
</html>