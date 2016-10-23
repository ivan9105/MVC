<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 15.10.2016
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>CRUD main</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <c:url var="back" value="/"/>
    <h2 class="book">Справочники авторов и книг</h2>
    <ul class="index">
        <li><a class="index" href="${pageContext.request.contextPath}/generateTestData">Сгенерировать данные</a></li>
        <li><a class="index" href="${pageContext.request.contextPath}/books">Книги</a></li>
        <li><a class="index" href="${pageContext.request.contextPath}/authors">Авторы</a></li>
    </ul>
    <div style="height: 30px"></div>
    <a class="book" href="${back}">Назад</a>
</div>
</body>
</html>
