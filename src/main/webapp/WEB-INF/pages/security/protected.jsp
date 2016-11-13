<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 09.11.2016
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>Protected page</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <h2 class="book">${title}</h2>
    <c:url var="back" value="/security"/>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>Вы зашли как : ${pageContext.request.userPrincipal.name}</p>
        <c:url value="login?logout" var="logoutUrl"/>
        <p><a class="index" href="${logoutUrl}">Выйти</a></p>
    </c:if>
    <div style="height: 30px"></div>
    <a class="book" href="${back}">Назад</a>
</div>
</body>
</html>