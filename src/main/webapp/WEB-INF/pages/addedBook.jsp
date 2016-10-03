<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 01.08.2015
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <link href="${jspCSS}" rel="stylesheet"/>
    <title>Added page</title>
</head>
<body>
<h2 class="book">Книги</h2>
<div class="content">
    <p>You have added a new book at <%= new java.util.Date() %>
    </p>
    <c:url var="mainUrl" value="/books"/>
    <p>Return to <a href="${mainUrl}">Main List</a></p>
</div>
</body>
</html>
