<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/dologin">

		<table>

			<tr>
				<td>username :</td>
				<td><input name="username" type="text"> </input></td>
			</tr>

			<tr>
				<td>password :</td>
				<td><input name="password" type="text"> </input></td>
			</tr>

			<tr>
				<td></td>
				<td><input value="login" type="submit" /></td>
			</tr>
		</table>

	</form>


</body>
</html>