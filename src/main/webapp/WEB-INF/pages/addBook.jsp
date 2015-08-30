<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>
  <h1>Create New Book</h1>
  <c:url var="saveUrl" value="/books/add" />
  <form:form modelAttribute="bookAttribute" method="POST" acceptCharset="${saveUrl}">
    <table>
      <tr>
        <td><form:label path="name">Name:</form:label></td>
        <td><form:input path="name"/></td>
      </tr>
      <tr>
        <td><form:label path="year">Year:</form:label></td>
        <td><form:input path="year" /></td>
      </tr>
    </table>
    <input type="submit" value="Save">
  </form:form>
</body>
</html>
