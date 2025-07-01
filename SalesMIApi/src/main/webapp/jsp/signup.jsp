<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>Signup Form</title>
</head>
<body>

	<h2>Signup Form</h2>
	<form:form method="POST" action="/sbhima/signup"
		modelAttribute="signupform">
		<table>
			<tr>
				<td><form:label path="firstName">First Name</form:label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name</form:label></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><form:label path="emailOrPhone">Email/Phone</form:label></td>
				<td><form:input path="emailOrPhone" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" name="password" /></td>
			</tr>
			<tr>
				<td><form:label path="confirmPassword">Confirm Password</form:label></td>
				<td><form:password path="confirmPassword"
						name="confirmPassword" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>