<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of messages</title>
</head>
<body>

	<c:forEach items="${messageList}" var="message">
		<li id="message_<c:out value="message.id"/>">
			<div class="messageText">
				<c:out value="${message.message}" />
				<c:out value="${message.author}" />
				<c:out value="${message.date}" />
			</div>
		</li>
	</c:forEach>

</body>
</html>