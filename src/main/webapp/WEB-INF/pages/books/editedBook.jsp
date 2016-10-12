<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 30.08.2015
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <link href="${jspCSS}" rel="stylesheet"/>
    <title>Edited Page</title>
</head>
<body>
<div class="content">
    <h2 class="book">Книги</h2>

    <p>Вы внесли изменения в книгу, id ${id}, текущая дата ${date}
    </p>
    <c:url var="mainUrl" value="/books"/>
    <p>Вернуться к <a class="small" href="${mainUrl}">списку книг</a></p>
</div>
</body>
</html>
