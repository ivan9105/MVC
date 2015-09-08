<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 06.09.2015
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather</title>
</head>
<body>
<p>${weather}</p>
<c:url var="mainUrl" value="/" />
<p>Return to <a href="${mainUrl}">Main Page</a></p>
</body>
</html>
