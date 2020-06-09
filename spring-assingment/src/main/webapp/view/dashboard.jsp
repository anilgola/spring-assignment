<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>
<body>
<h3>Welcome ${username}</h3> 
<br/>

<nav class="nav">
  <a class="nav-link active" href="/view-ticket">View Tickets</a>
  <a class="nav-link" href="/raise-ticket">Raise Tickets</a>
  <form method="post" action="/logout">
	   <input type ="submit" class="btn btn-link" value="Log out"/>
	</form>
</nav>

	
	
</body>
</html>