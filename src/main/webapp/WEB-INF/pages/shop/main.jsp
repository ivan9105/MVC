<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrapJs"/>
<spring:url value="/resources/jquery.js" var="jquery"/>
<spring:url value="/resources/img/cart.gif" var="cart"/>
<spring:url value="/resources/shop.js" var="shopJs"/>
<html>
<head>
    <title>Shop</title>
    <spring:url value="/resources/bootstrap/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/jsp.css" var="jsp"/>
    <link href="${bootstrap}" rel="stylesheet"/>
    <link href="${jsp}" rel="stylesheet"/>
    <script src="${shopJs}"></script>
</head>
<script>
    window.onload = function () {
        fillMenu();
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
<div class="container" style="margin-top: 90px">
    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation" style="margin-left: -90px;">
        <div id="categoryMenu" class="list-group">
        </div>
    </div>

    <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
            <div class="jumbotron">
                <h1>Content</h1>
                <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap.
                    Try some responsive-range viewport sizes to see it in action.</p>
            </div>
        </div>
    </div>
    <%--<!-- Marketing Icons Section -->--%>
    <%--<div class="row">--%>
    <%--<div class="col-lg-12">--%>
    <%--<h1 class="page-header" style="margin-top: 90px">--%>
    <%--Welcome to Modern Business--%>
    <%--</h1>--%>
    <%--</div>--%>
    <%--<div class="col-md-4">--%>
    <%--<div class="panel panel-default">--%>
    <%--<div class="panel-heading">--%>
    <%--<h4><i class="fa fa-fw fa-check"></i> Bootstrap v3.2.0</h4>--%>
    <%--</div>--%>
    <%--<div class="panel-body">--%>
    <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla--%>
    <%--aspernatur in alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus--%>
    <%--eveniet incidunt dicta nostrum quod?</p>--%>
    <%--<a href="#" class="btn btn-default">Learn More</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-4">--%>
    <%--<div class="panel panel-default">--%>
    <%--<div class="panel-heading">--%>
    <%--<h4><i class="fa fa-fw fa-gift"></i> Free &amp; Open Source</h4>--%>
    <%--</div>--%>
    <%--<div class="panel-body">--%>
    <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla--%>
    <%--aspernatur in alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus--%>
    <%--eveniet incidunt dicta nostrum quod?</p>--%>
    <%--<a href="#" class="btn btn-default">Learn More</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-4">--%>
    <%--<div class="panel panel-default">--%>
    <%--<div class="panel-heading">--%>
    <%--<h4><i class="fa fa-fw fa-compass"></i> Easy to Use</h4>--%>
    <%--</div>--%>
    <%--<div class="panel-body">--%>
    <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla--%>
    <%--aspernatur in alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus--%>
    <%--eveniet incidunt dicta nostrum quod?</p>--%>
    <%--<a href="#" class="btn btn-default">Learn More</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<!-- /.row -->--%>
    <%--<!-- Portfolio Section -->--%>
    <%--<div class="row">--%>
    <%--<div class="col-lg-12">--%>
    <%--<h2 class="page-header">Portfolio Heading</h2>--%>
    <%--</div>--%>
    <%--<div class="col-md-4 col-sm-6">--%>
    <%--<a href="portfolio-item.html">--%>
    <%--<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--<div class="col-md-4 col-sm-6">--%>
    <%--<a href="portfolio-item.html">--%>
    <%--<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--<div class="col-md-4 col-sm-6">--%>
    <%--<a href="portfolio-item.html">--%>
    <%--<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--<div class="col-md-4 col-sm-6">--%>
    <%--<a href="portfolio-item.html">--%>
    <%--<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--<div class="col-md-4 col-sm-6">--%>
    <%--<a href="portfolio-item.html">--%>
    <%--<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--<div class="col-md-4 col-sm-6">--%>
    <%--<a href="portfolio-item.html">--%>
    <%--<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<!-- /.row -->--%>

    <%--<!-- Features Section -->--%>
    <%--<div class="row">--%>
    <%--<div class="col-lg-12">--%>
    <%--<h2 class="page-header">Modern Business Features</h2>--%>
    <%--</div>--%>
    <%--<div class="col-md-6">--%>
    <%--<p>The Modern Business template by Start Bootstrap includes:</p>--%>
    <%--<ul>--%>
    <%--<li><strong>Bootstrap v3.2.0</strong>--%>
    <%--</li>--%>
    <%--<li>jQuery v1.11.0</li>--%>
    <%--<li>Font Awesome v4.1.0</li>--%>
    <%--<li>Working PHP contact form with validation</li>--%>
    <%--<li>Unstyled page elements for easy customization</li>--%>
    <%--<li>17 HTML pages</li>--%>
    <%--</ul>--%>
    <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis, omnis doloremque non cum id--%>
    <%--reprehenderit, quisquam totam aspernatur tempora minima unde aliquid ea culpa sunt. Reiciendis quia--%>
    <%--dolorum ducimus unde.</p>--%>
    <%--</div>--%>
    <%--<div class="col-md-6">--%>
    <%--<img class="img-responsive" src="http://placehold.it/700x450" alt="">--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<!-- /.row -->--%>

    <%--<hr>--%>
    <%--<!-- Call to Action Section -->--%>
    <%--<div class="well">--%>
    <%--<div class="row">--%>
    <%--<div class="col-md-8">--%>
    <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias, expedita, saepe, vero rerum--%>
    <%--deleniti beatae veniam harum neque nemo praesentium cum alias asperiores commodi.</p>--%>
    <%--</div>--%>
    <%--<div class="col-md-4">--%>
    <%--<a class="btn btn-lg btn-default btn-block" href="#">Call to Action</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

    <hr>
    <!-- Footer -->
    <div class="footer">
        <p>Copyright © Shop Sample <strong>2017</strong></p>
    </div>
</div>

<script src="${bootstrapJs}"></script>
<script src="${jquery}"></script>
</body>
</html>