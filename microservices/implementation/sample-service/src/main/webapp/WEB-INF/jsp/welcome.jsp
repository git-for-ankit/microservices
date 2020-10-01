<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<center>
		<h2>Hi ${USERNAME}</h2>
		<h1>Welcome to sample-service!</h1>
		<h2>Session id :- ${SESSION}</h2>
		<form action="/logout" method="post">
			<input value="Logout" type="submit">
		</form>
	</center>
</body>
</html>