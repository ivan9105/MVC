<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrapJs"/>
<spring:url value="/resources/jquery.js" var="jqueryJs"/>
<spring:url value="/resources/img/cart.gif" var="cart"/>
<spring:url value="/resources/shop.js" var="shopJs"/>
<html style="height: 100%;">
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
        fillMenu();
        alert('${id}');
    };
</script>
<body style="height: 100%;">
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
                <li>
                    <a href="cart.html">
                        <div><i class="fa fa-cart-arrow-down" style="font-size: larger;"></i></div>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Content -->
<div class="container" style="margin-top: 90px; padding-top: 5px; height: 100%;">
    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation"
         style="position: absolute; left:25px;">
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

    <div class="row row-offcanvas row-offcanvas-right" style="padding: 5px; height: 100%;">
        <div class="col-xs-12 col-sm-9" style="height: 100%;">
            <div class="jumbotron">
                <h1>Shop Sample</h1>

                <p>In this sample use: jquery, js, bootstrap, font-awesome</p>
            </div>

            <div id="itemDetailsBox" style="height: 90%;">
                <div id="detailsBreadcrumbs" style="margin-bottom: 7px; font-size: small;"></div>
                <h1>Item</h1>

                <h3>Code</h3>

                <div id="base"
                     style="border: 1px solid black; height: 30%; margin-bottom: 10px; width: 100%; display: inline-block;">
                    <div id="photo" style="border: 1px solid red; width: 30%; float: left; height: 100%;">
                        <div id="photo_" style="border: 1px solid darkred; height: 78%;"></div>
                        <div id="slide" style="border: 1px solid greenyellow; height: 22%;"></div>
                    </div>
                    <div id="info" style="border: 1px solid black; width: 25%; height: 100%; float: left;">
                        <h2>Price</h2>

                        <div id="info_" style="border: 1px solid red;">
                        </div>
                        <button id="buy" style="width: 60%; background-color: orangered;">Buy</button>
                        <div id="info__"></div>
                    </div>
                    <div id="additionalInfo"
                         style="border: 1px solid lawngreen; width: 20%; float: right; height: 100%;"></div>
                </div>

                <div id="tabSheet" style="width: 100%; border: 1px solid yellow; display: inline-block; height: 65%;">
                    <ul class="itemInfo" style="width: 100%; border: 1px solid goldenrod;">
                        <li><a href="#" class="active">Description</a></li>
                        <li><a href="#">Characteristics</a></li>
                        <li><a href="#">Reviews</a></li>
                        <li><a href="#">Comments</a></li>
                        <li><a href="#">FAQ</a></li>
                    </ul>

                    <div id="tabContent"
                         style="display: inline-block; border: 1px solid #0957a6; width: 100%; height: 90%;">
                        <div id="description"></div>
                        <div id="characteristics"></div>
                        <div id="reviews"></div>
                        <div id="comments"></div>
                        <div id="faq"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footerSpace" style="border: 1px solid blanchedalmond; height: 320px;"></div>
<div class="footer" style="display: inline-block;">
    <p>Copyright © Shop Sample <strong>2017</strong></p>
</div>

<script src="${bootstrapJs}"></script>
</body>
</html>