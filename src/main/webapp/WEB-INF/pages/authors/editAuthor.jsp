<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 12.10.2016
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <link href="${jspCSS}" rel="stylesheet"/>
    <title>Edit author</title>
</head>
<body>
<div class="content">
    <h2 class="book">Редактор автора</h2>
    <c:url var="saveUrl" value="/authors/edit?id=${authorAttribute.id}"/>
    <form:form modelAttribute="authorAttribute" method="POST" acceptCharset="${saveUrl}">
        <form:label path="name" cssClass="info">Имя: </form:label>
        <form:errors path="name" cssClass="error"/><br/>
        <form:input path="name" cssClass="bookInput"/><br/>
        <form:label path="lastName" cssClass="info">Фамилия: </form:label>
        <form:errors path="lastName" cssClass="error"/><br/>
        <form:input path="lastName" cssClass="bookInput"/><br/>
        <form:label path="middleName" cssClass="info">Отчество: </form:label>
        <form:errors path="middleName" cssClass="error"/><br/>
        <form:input path="middleName" cssClass="bookInput"/><br/>

        <div style="height: 30px"></div>
        <input type="submit" value="Сохранить" class="book">
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </form:form>
</div>
</body>
</html>
