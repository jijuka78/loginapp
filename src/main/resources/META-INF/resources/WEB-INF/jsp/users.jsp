<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
<title>Welcoming ${name} to Xerox</title>
</head>
<body>
<div class="container">
<form method="post">
<h1><p>Welcoming ${name} to Xerox User page</p></h1>
<h3><p>${message}</p></h3>
<table class="table">
<thead>
<tr><th><h2>UserId</h2></th><th><h2>Password</h2></th></tr>
<thead>
<tbody>
<c:forEach items="${users}" var="user">
<tr>
<td>${user.userId}</td>
<td>${user.password}</td>
</tr>

</c:forEach>
</tbody>
</table>

<h2><p><input type="submit" name="Back" value="Back" class="btn btn-success" /></p></h2>
</form>
</div>
<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

</body>


</html>