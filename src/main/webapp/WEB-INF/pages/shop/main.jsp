<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrapJs"/>
<spring:url value="/resources/jquery.js" var="jqueryJs"/>
<spring:url value="/resources/img/cart.gif" var="cart"/>
<spring:url value="/resources/shop.js" var="shopJs"/>
<html>
<head>
    <title>Shop</title>
    <spring:url value="/resources/bootstrap/css/bootstrap.css" var="bootstrapCss"/>
    <spring:url value="/resources/jsp.css" var="jspCss"/>
    <spring:url value="/resources/shop.css" var="shopCss"/>
    <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontAwesomeCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${jspCss}" rel="stylesheet"/>
    <link href="${shopCss}" rel="stylesheet"/>
    <link href="${fontAwesomeCss}" rel="stylesheet"/>
    <script src="${jqueryJs}"></script>
    <script src="${shopJs}"></script>
</head>
<script>
    window.onload = function () {
        //Todo fill then load div
        fillMenu();
        fillTable(1);
    };
</script>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a class="navbar-brand" href="index.html">Start Bootstrap</a>--%>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="login_.html">Login</a>
                </li>
                <li>
                    <a href="registration.html">Registration</a>
                </li>
                <%--<li>--%>
                <%--<a href="cart.html"><img src="${cart}"/></a>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Content -->
<div class="container" style="margin-top: 90px; padding-top: 5px;">
    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation" style="position: absolute; left:25px;">
        <div id="menuWrapper" style="padding: 5px;" onmousemove="onMouseMenuWrapper(event)">
            <div id="categoryMenu" class="list-group">
            </div>
        </div>
    </div>

    <%--Fake--%>
    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="_sidebar" role="navigation" style="margin-left: -90px;">
        <div id="_menuWrapper" style="padding: 5px;" onmousemove="onMouseMenuWrapper(event)">
            <div id="_categoryMenu" class="list-group">
            </div>
        </div>
    </div>

    <div class="row row-offcanvas row-offcanvas-right" style="padding: 5px;">
        <div class="col-xs-12 col-sm-9">
            <div class="jumbotron">
                <h1>Shop Sample</h1>

                <p>In this sample use: jquery, js, bootstrap, font-awesome</p>
            </div>

            <div style="margin:2px;">
                <h2 style="color: #222222;">Items</h2>
                <hr/>
                <div id="tablePagination" class="center"></div>
                <table id="itemsTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Count</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>Copyright © Shop Sample <strong>2017</strong></p>
</div>

<script src="${bootstrapJs}"></script>
</body>
</html>