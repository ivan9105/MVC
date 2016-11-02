<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 30.07.2015
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>Books</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <h2 class="book">Книги</h2>
    <c:url var="addUrl" value="books/add"/>
    <c:url var="back" value="/crudMain"/>
    <c:if test="${!empty books}">
        <form:form method="post"  action="${pageContext.request.contextPath}/books">
            <p class="index">Фильтр: </p>
            <input type="text" name="searchText" value="" class="bookInput" title="">
            <input type="submit" value="Поиск" class="book"/>
        </form:form>
        <div style="height: 15px"></div>

        <table class="book">
            <tr>
                <th class="book">Наименование</th>
                <th class="book">Год</th>
                <th class="book">Автор</th>
                <th colspan="3" class="book"></th>
            </tr>
            <tbody>
            <c:forEach items="${books}" var="book">
                <c:url var="editUrl" value="/books/edit?id=${book.id}"/>
                <c:url var="deleteUrl" value="/books/delete?id=${book.id}"/>
                <tr>
                    <td class="book"><c:out value="${book.name}"/></td>
                    <td class="book"><c:out value="${book.year}"/></td>
                    <td class="book">
                        <c:if test="${!empty book.author}">
                            <c:out value="${book.author.instanceName}"/>
                        </c:if>
                    </td>
                    <td class="action"><a class="book" href="${editUrl}">Редактировать</a></td>
                    <td class="action"><a class="book" href="${deleteUrl}">Удалить</a></td>
                    <td class="action"><a class="book" href="${addUrl}">Добавить</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:if test="${pageableInfo.lastValue > 1}">
            <br/>
            <c:url var="booksUrl" value="/books"/>
            <c:forEach begin="1" end="${pageableInfo.lastValue}" var="i">
                <c:if test="${i eq pageableInfo.currentValue}">
                    <a href="${booksUrl}?page=${i}&size=${pageableInfo.size}" class="pagination_"><c:out
                            value="${i}"/></a>
                </c:if>
                <c:if test="${i ne pageableInfo.currentValue}">
                    <a href="${booksUrl}?page=${i}&size=${pageableInfo.size}" class="pagination"><c:out
                            value="${i}"/></a>
                </c:if>
            </c:forEach>
        </c:if>
    </c:if>
    <div class="info">
        <c:if test="${empty books}">
            <br/>
            Список книг пустой.<br/> <a class="index" href="${addUrl}">Добавьте</a> книгу.
        </c:if>
    </div>

    <div style="height: 30px"></div>
    <a class="book" href="${back}">Назад</a>
</div>
</body>
</html>
