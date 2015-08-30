<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 30.07.2015
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Edit book</title>
</head>
<body>
    <h1>Edit Book</h1>
    <c:url var="saveUrl" value="/books/edit?id=${bookAttribute.id}" />
    <form:form method="post" modelAttribute="bookAttribute" action="${saveUrl}">
        <table>
            <tr>
                <td><form:label path="name">Name:</form:label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><form:label path="year">Year:</form:label></td>
                <td><form:input path="year"/></td>
            </tr>
        </table>
        <input type="submit" value="Save" />
    </form:form>
</body>
</html>
