<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 01.08.2015
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Added page</title>
</head>
<body>
  <h1>Books</h1>
  <p>You have added a new book at <%= new java.util.Date() %></p>

  <c:url var="mainUrl" value="/books" />
  <p>Return to <a href="${mainUrl}">Main List</a></p>
</body>
</html>
