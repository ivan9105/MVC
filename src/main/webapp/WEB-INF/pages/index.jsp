<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>Index</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <h2 class="book">${message}</h2>
    <a class="index" href="${pageContext.request.contextPath}/books">JDBC CRUD example</a><br/>
    <%--<a href="${pageContext.request.contextPath}/weather">Weather</a><br />--%>
    <%--<a href="${pageContext.request.contextPath}/messages">Messages</a>--%>
</div>
</body>
</html>