<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 13.11.2016
  Time: 18:14
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
    <title>Login page</title>
</head>
<body>
<div class="content">
    <c:url var="back" value="/security"/>
    <h2 class="book">Авторизация</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="message">${msg}</div>
    </c:if>

    <form name="loginForm" action="login" method="POST">
        <table>
            <tr>
                <td><label class="info">Пользователь:</label></td>
                <td><input class="bookInput" type='text' name='username' value='' title=""></td>
            </tr>
            <tr style="background: white">
                <td><label class="info">Пароль:</label></td>
                <td><input class="bookInput" type='password' name='password' title=""/></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <div style="height: 30px"></div>
                    <input name="submit" type="submit" value="Вход" class="book"/>
                    <a class="book" href="${back}" style="margin-left: 5px;">Назад</a>
                </td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
</div>
</body>
</html>
