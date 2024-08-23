<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" />
<title>Welcoming ${name} to Xerox</title>
</head>
<body>
<div class="container">
<h1><p>Welcoming ${name} to Xerox User page</p></h1>
<h3><p>${message}</p></h3>

<form action="getUser" method="get">
<h2><p>
<input type="submit" name="action" value="FetchUser" class="btn btn-success">
</p></h2>
</form>

<form action="createUser" method="get">
<h2><p>
<input type="submit" name="action" value="CreateUser" class="btn btn-success">
</p></h2>
</form>

<form action="updateUser"  method="get">
<h2><p>
<input type="submit" name="action" value="UpdateUser" class="btn btn-success">
</p></h2>
</form>

<form action="removeUser" method="get">
<h2><p>
<input type="submit" name="action" value="RemoveUser" class="btn btn-success">
</p></h2>
</form>

<form action="listAllUsers"  method="get">
<h2><p>
<input type="submit" name="action" value="ListAllUsers" class="btn btn-success">
</p></h2>
</form>
</div>
</body>


</html>