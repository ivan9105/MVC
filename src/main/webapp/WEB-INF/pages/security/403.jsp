<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 15.11.2016
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <link href="${jspCSS}" rel="stylesheet"/>
    <title>403 Page</title>
</head>
<body>
<div class="content">
    <h2 class="book">Доступ запрещен</h2>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>Вы зашли как : ${pageContext.request.userPrincipal.name}</p>
        <p>Для того чтобы получить доступ к этой странице вы должны зайти под другой ролью.</p>
    </c:if>
    <c:url var="back" value="/security"/>
    <a class="book" href="${backUrl}" style="margin-left: 5px;">Назад</a>
</div>
</body>
</html>
