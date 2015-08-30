<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 30.08.2015
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edited Page</title>
</head>
<body>
  <h1>Books</h1>
  <p>You have edited a book with id ${id} at <%= new java.util.Date() %></p>
  <c:url var="mainUrl" value="/books" />
  <p>Return to <a href="${mainUrl}">Main List</a></p>
</body>
</html>
