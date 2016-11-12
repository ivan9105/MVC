<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <title>Index</title>
    <link href="${jspCSS}" rel="stylesheet"/>
</head>
<body>
<div class="content">
    <h2 class="book">${message}</h2>
    <ul class="index">
        <li><a class="index" href="${pageContext.request.contextPath}/crudMain">Book catalog (Spring data jpa, JDBC)</a></li>
        <li><a class="index" href="${pageContext.request.contextPath}/api/weather/weather">GlobalWeather Web Service (http://www.webservicex.net/globalweather.asmx)</a></li>
        <li><a class="index" href="${pageContext.request.contextPath}/swagger-ui.html">Swagger</a></li>
        <%--<li><a class="index" href="${pageContext.request.contextPath}/protected">Protected</a></li>--%>
        <%--<li><a class="index" href="${pageContext.request.contextPath}/confidential">Confidential</a></li>--%>
    </ul>
</div>
</body>
</html>