<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 16.1.2018 г.
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link rel="stylesheet" type="text/css" href='<c:url value="/webjars/jquery-ui/1.12.1/jquery-ui.min.css"/>'/>
    <link rel="stylesheet" type="text/css" href='<c:url value="/webjars/materialize/0.100.2/dist/css/materialize.min.css"/>'/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/fonts/iconfont/material-icons.css"/>">
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/main.css"/>'/>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/common.css"/>'/>

    <script src='<c:url value="/webjars/jquery/3.2.1/jquery.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/webjars/materialize/0.100.2/dist/js/materialize.min.js"/>' type="text/javascript"></script>

</head>

<body background="">

<div class="preloader" style="display: none">
    <div class="pre-container">
        <div class="logo-circle">
         <img class="preloader-icon" src="<c:url value="/resources/images/logo-tu-white.png" />">
        </div>
        <%--<div class="loading-text">--%>
            <%--<p>Моля изчакайте...</p>--%>
        <%--</div>--%>
    </div>
</div>
<header>

    <sec:authorize access="hasRole('ADMIN')" var="adminViewer"/>
    <sec:authentication var="principal" property="principal"/>

    <div class="container tbl">
        <div class="float-left">
            <a href="<c:url value="/"/>">
                <img src="<c:url value="/resources/images/logo-tu.png" />" style="max-width: 100px;max-height: 100px">
            </a>
        </div>
        <div class="float-left">
            <div style="font-size: 25px;text-align: center;margin-top: 9px;">
                <spring:message code="logo.title"/>
            </div>
            <div class="center">
                <spring:message code="logo.sub.title"/>
            </div>
            <div class="center mT8px">
                <span id="currentTime"></span>
            </div>
        </div>

        <div>
            <div class="display-inline-block float-right mT1em">
                <div class="display-inline-block">
                    <a href="?lang=bg">
                        <img class="img22" src="<c:url value="/resources/images/bg-flag.png"/>">
                    </a>
                </div>
                <div class="display-inline-block">
                    <a href="?lang=en">
                        <img class="img22" src="<c:url value="/resources/images/uk_flag.png"/>">
                    </a>
                </div>
            </div>
        </div>
        <div class="login-div">
            <c:choose>
                <c:when test="${principal eq 'anonymousUser'}">
                    <div>
                        <button id="log-in-btn" data-url="<c:url value="/login"/>"
                                class="waves-effect waves-light btn light-blue darken-4 z-depth-3">
                            <i class="material-icons left ">person</i><spring:message code="header.log.in"/>
                        </button>
                    </div>
                </c:when>
                <c:otherwise>
                    <sec:authentication var="username" property="principal.username"/>
                    <button data-activates='login-dropdown'
                            class="waves-effect waves-light dropdown-button btn light-blue darken-4 z-depth-3">
                      <i class="material-icons right ">arrow_drop_down</i>${username}
                    </button>

                    <ul id='login-dropdown' class='dropdown-content'>
                        <li>
                            <a href="<c:url value="/profile"/>" class="light-blue-text darken-4">
                                <i class="material-icons">assignment_ind</i><span><spring:message code="header.menu.profile"/></span>
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/admin/menu"/>" class="light-blue-text darken-4">
                                <i class="material-icons">security</i><span><spring:message code="header.menu.admin"/></span>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value="/logout"/>" class="light-blue-text darken-4">
                                <i class="material-icons">exit_to_app</i><spring:message code="header.menu.log.out"/>
                            </a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="clear"></div>
    </div>

    <div class="main-menu">
        <nav>
            <a href="<c:url value="/"/>" title="Начало"><spring:message code="title.home"/></a>
            <a href="<c:url value="/search"/>" title=""><spring:message code="title.books"/></a>
            <a href=""><spring:message code="title.news"/></a>
            <a href="<c:url value="/feedback"/>" title=""><spring:message code="title.feedback"/></a>
            <a href="<c:url value="/contacts"/>" title=""><spring:message code="title.contacts"/></a>
            <a href="" title=""><spring:message code="title.guide"/></a>
        </nav>
    </div>
    <div class="light-blue darken-4 narrow-subheader"></div>
</header>
<script>
    $('.dropdown-button').dropdown({
            inDuration: 300,
            outDuration: 225,
            constrainWidth: true, // Does not change width of dropdown to that of the activator
            hover: true, // Activate on hover
            gutter: 0, // Spacing from edge
            belowOrigin: true, // Displays dropdown below the button
            alignment: 'right', // Displays dropdown with edge aligned to the left of button
            stopPropagation: false // Stops event propagation
        }
    );
</script>