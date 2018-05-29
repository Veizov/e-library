<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>

<div class="bg_breadcrumbs z-depth-1">
    <div class="container">
        <div class="breadcrumbs clearfix">
            <a class="breadcrumb-link" href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
            <span><spring:message code="title.register"/></span>
        </div>
    </div>
</div>
<div class="container main-content ">

    <div class="z-depth-2 white mT2em p50px">
        <img class="right mT-15px img70" src="<c:url value="/resources/images/login-icon.png"/>" />
        <div class="section-title"><spring:message code="title.register.user"/></div>

        <div>
            <div class="alert alert-info info-tab align-center">
                <spring:message code="text.register.success"/>
            </div>
        </div>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>