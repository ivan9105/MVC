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
        fillTable();
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
<!-- Header Carousel -->
<header id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class=""></li>
        <li data-target="#myCarousel" data-slide-to="1" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&amp;text=Slide One');"></div>
            <div class="carousel-caption">
                <h2>Caption 1</h2>
            </div>
        </div>
        <div class="item active">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&amp;text=Slide Two');"></div>
            <div class="carousel-caption">
                <h2>Caption 2</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&amp;text=Slide Three');"></div>
            <div class="carousel-caption">
                <h2>Caption 3</h2>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="icon-prev"></span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="icon-next"></span>
    </a>
</header>
<!-- Page Content -->
<div class="container" style="margin-top: 90px; padding-top: 5px;">
    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation" style="margin-left: -90px;">
        <div id="menuWrapper" style="padding: 5px;" onmousemove="onMouseMenuWrapper(event)">
            <div id="categoryMenu" class="list-group">
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
                <table id="itemsTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <!-- Footer -->
    <div class="footer">
        <p>Copyright © Shop Sample <strong>2017</strong></p>
    </div>
</div>

<script src="${bootstrapJs}"></script>
</body>
</html>