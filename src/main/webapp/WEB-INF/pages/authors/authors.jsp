
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 12.10.2016
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>Authors</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <h2 class="book">Авторы</h2>
    <c:url var="addUrl" value="/authors/add"/>
    <c:url var="back" value="/crudMain"/>
    <c:if test="${!empty authors}">
        <table class="book">
            <tr>
                <th class="book">Имя</th>
                <th class="book">Фамилия</th>
                <th class="book">Отчество</th>
                <th colspan="3" class="book"></th>
            </tr>
            <tbody>
            <c:forEach items="${authors}" var="author">
                <c:url var="editUrl" value="/authors/edit?id=${author.id}"/>
                <c:url var="deleteUrl" value="/authors/delete?id=${author.id}"/>
                <tr>
                    <td class="book"><c:out value="${author.name}"/></td>
                    <td class="book"><c:out value="${author.lastName}"/></td>
                    <td class="book"><c:out value="${author.middleName}"/></td>
                    <td class="action"><a class="book" href="${editUrl}">Редактировать</a></td>
                    <td class="action"><a class="book" href="${deleteUrl}">Удалить</a></td>
                    <td class="action"><a class="book" href="${addUrl}">Добавить</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="info">
        <c:if test="${empty authors}">
            <br/>
            Список авторов пустой.<br/> <a class="index" href="${addUrl}">Добавьте</a> автора.
        </c:if>
    </div>

    <div style="height: 30px"></div>
    <a class="book" href="${back}">Назад</a>
</div>
</body>
</html>
