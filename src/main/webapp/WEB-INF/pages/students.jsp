<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of students</title>
</head>
<body>
<h1>Showing Students List</h1>
<c:if test="${not empty studentList}">
	<c:forEach items="${studentList}" var="student">
		<li id="student_<c:out value="student.id"/>">
			<div class="studentAttributes">
				<c:out value="${student.number}" />
				<c:out value="${student.id}" />
				<c:out value="${student.name}" />
				<c:out value="${student.surname}" />
				<c:out value="${student.city}" />
				<c:out value="${student.address}" />
				<c:out value="${student.sex}" />
			</div>
		</li>
	</c:forEach>
</c:if>
<c:if test="${empty studentList}">
<h1>Empty List</h1>
</c:if>
</body>
</html>