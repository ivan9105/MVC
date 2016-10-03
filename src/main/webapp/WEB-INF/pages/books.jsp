<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 30.07.2015
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS" />
    <title>Books</title>
    <link href="${jspCSS}" rel="stylesheet" />
</head>
<body>
<h1>Книги</h1>
<c:url var="addUrl" value="books/add"/>
<table class="book">
    <tr>
        <th class="book">Наименование</th>
        <th class="book">Год</th>
        <th colspan="3" class="book"></th>
    </tr>
    <tbody>
    <c:forEach items="${books}" var="book">
        <c:url var="editUrl" value="/books/edit?id=${book.id}"/>
        <c:url var="deleteUrl" value="/books/delete?id=${book.id}"/>
        <tr>
            <td class="book"><c:out value="${book.name}"/></td>
            <td class="book"><c:out value="${book.year}"/></td>
            <td class="action"><a class="book" href="${editUrl}">Edit</a></td>
            <td class="action"><a class="book" href="${deleteUrl}">Delete</a></td>
            <td class="action"><a class="book" href="${addUrl}">Add</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty books}">
    There are currently no books in the list. <a href="${addUrl}">Add</a> a book.
</c:if>

</body>
</html>
