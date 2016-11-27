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
    <title>JMS main</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <c:url var="back" value="/"/>
    <h2 class="book">Jms sample</h2>
    <c:if test="${empty error}">
        <p>Broker настроен правильно, используется url, для того чтобы проверить список сообщений broker_host:broker_port/admin/queues.jsp, <br/> логин пароль по умолчанию admin/admin</p>
    </c:if>
    <c:if test="${!empty error}">
        <p>Broker не настроен, например можно использовать Active MQ</p>
    </c:if>
    <div style="height: 30px"></div>
    <a class="book" href="${back}">Назад</a>
</div>
</body>
</html>
