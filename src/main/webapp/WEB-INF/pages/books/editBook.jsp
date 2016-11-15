<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 30.07.2015
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <link href="${jspCSS}" rel="stylesheet"/>
    <title>Edit book</title>
</head>
<body>
<div class="content">
    <h2 class="book">Редактор книги</h2>
    <c:url var="saveUrl" value="/books/edit?id=${bookAttribute.id}"/>
    <form:form method="post" modelAttribute="bookAttribute" action="${saveUrl}">
        <form:label path="name" cssClass="info">Наименование:</form:label>
        <form:errors path="name" cssClass="error"/><br/>
        <form:input path="name" cssClass="bookInput"/><br/>
        <form:label path="year" cssClass="info">Год:</form:label>
        <form:errors path="year" cssClass="error"/><br/>
        <form:input path="year" onkeypress='return event.charCode >= 48 && event.charCode <= 57' cssClass="bookInput"/><br/>
        <form:label path="author" cssClass="info">Автор:</form:label>
        <form:errors path="author" cssClass="error"/><br/>
        <form:select path="author" cssClass="bookInput">
            <c:forEach items="${authorList}" var="author">
                <option <c:if test="${bookAttribute.author != null && author.id eq bookAttribute.author.id}">selected="selected"</c:if> value="${author.id}">${author.instanceName}</option>
            </c:forEach>
        </form:select>
        <div style="height: 30px"></div>
        <input type="submit" value="Сохранить" class="book">
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </form:form>
</div>
</body>
</html>
