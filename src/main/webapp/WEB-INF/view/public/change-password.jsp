<%@ taglib prefix="спринг" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sprng" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<c:url value="/feedback" var="feedbackURL"/>
<div class="bg_breadcrumbs hidden-xs hidden-sm">
    <div class="container">
        <div class="breadcrumbs clearfix">
            <a class="breadcrumb-link" href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
            <span><spring:message code="title.change.password"/></span>
        </div>
    </div>
</div>


<div class="container main-content ">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px"
             src="<c:url value="/resources/images/forgotten-password.png"/>"/>
        <div class="section-title"><spring:message code="title.change.password"/></div>
        <form method="post" action="<c:url value="/forgotten-password/change-password"/>">
            <div class="row">
                <div class="input-field col s12">
                    <label for="fp-password"><spring:message code="label.password"/></label>
                    <input id="fp-password" type="password" name="password">
                    <c:if test="${isEmptyPassword eq true}">
                        <div class="message-error mB1em"><spring:message code="empty.field.valid"/></div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <label for="fp-password-confirm"><spring:message code="label.password.confirm"/></label>
                    <input id="fp-password-confirm" type="password" name="confirmPassword">
                    <c:if test="${isDifferentPasswords eq true}">
                        <div class="message-error mB1em"><spring:message code="fp.passwords.not.equals"/></div>
                    </c:if>
                </div>
            </div>
            <input type="hidden" name="token" value="${token}">
            <div class="mB1em">
                <button type="submit" class="waves-effect waves-light btn light-blue darken-4"><spring:message code="btn.save"/></button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>