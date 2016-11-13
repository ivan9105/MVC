<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 13.11.2016
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>Security main</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <c:url var="back" value="/"/>
    <h2 class="book">Security Sample</h2>
    <ul class="index">
        <li>Страница <a class="index" href="${pageContext.request.contextPath}/protected">protected</a> для пользователя
            admin/admin.
        </li>
        <li>Страница <a class="index" href="${pageContext.request.contextPath}/confidential">confidential</a> для
            пользователя superadmin/superadmin.
        </li>
    </ul>
    <div style="height: 30px"></div>
    <a class="book" href="${back}">Назад</a>
</div>
</body>
</html>
