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
            <span><spring:message code="title.verification.code.send"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img class="right mT-15px img70" src="<c:url value="/resources/images/login-icon.png"/>"/>
        <div class="section-title"><spring:message code="title.verification.code.send"/></div>

        <form action="<c:url value="/verification/send-verification-code"/>" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input id="user-email" type="text" value="${email}" name="email"/>
                    <label for="user-email"><spring:message code="label.email"/></label>
                    <c:if test="${not empty errorMessage}">
                        <span class="message-error">
                            ${errorMessage}
                        </span>
                    </c:if>
                </div>
            </div>
            <div class="mB1em">
                <button type="submit" class="waves-effect waves-light btn light-blue darken-4">Изпрати</button>
            </div>
        </form>
        <c:if test="${not empty success}">
            <div class="alert alert-success info-tab align-center">
                    ${success}
            </div>
        </c:if>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>