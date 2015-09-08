<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 05.09.2015
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:url var="saveUrl" value="/weather"/>
<h1>City</h1>
Select city:
<form:form modelAttribute="table" method="POST" acceptCharset="${saveUrl}">
    <table>
        <tbody>
        <tr>
            <td>
                <ul>
                    <form:select path="city" items="${data}">
                    </form:select></ul>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit">
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>
