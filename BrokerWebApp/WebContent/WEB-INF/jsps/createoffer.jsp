<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
</head>

<body>

	<form method="post"
		action="${pageContext.request.contextPath}/docreate">

		<table class="formtable">

			<tr>
				<td class="label">Name :</td>
				<td class="control"><input name="name" type="text"> </input></td>
			</tr>

			<tr>
				<td class="label">email :</td>
				<td class="control"><input name="email" type="text"> </input></td>
			</tr>

			<tr>
				<td class="label">Your Offer :</td>
				<td class="control"><textarea name="text" rows="10" cols="10"></textarea>
					</input></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="Create advert" type="submit" />
				</td>
			</tr>
		</table>

	</form>

</body>
</html>