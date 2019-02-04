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
            <span><spring:message code="title.forgotten.password"/></span>
        </div>
    </div>
</div>


<div class="container main-content ">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px"
             src="<c:url value="/resources/images/forgotten-password.png"/>"/>
        <div class="section-title"><spring:message code="title.forgotten.password"/></div>

        <c:if test="${sendEmailSuccessFully eq true}">
            <div>
                <div class="alert alert-success info-tab align-center">
                    <spring:message code="text.change.password.mail"/>
                </div>
            </div>
        </c:if>
        <form method="post" action="<c:url value="/forgotten-password"/>">
            <div class="row">
                <div class="input-field col s12">
                    <label for="fp-email" class="">Имейл</label>
                    <input id="fp-email" type="text" name="email">
                    <c:if test="${emptyEmailValidation eq true}">
                        <div class="message-error mB1em"><spring:message code="empty.field.valid"/></div>
                    </c:if>
                    <c:if test="${emptyUserValidation eq true}">
                        <div class="message-error mB1em"><spring:message code="fp.empty.user.valid"/></div>
                    </c:if>
                    <c:if test="${isTokenMissing eq true}">
                        <div class="message-error mB1em"><spring:message code="fp.missing.token.valid"/></div>
                    </c:if>
                    <c:if test="${isExpired eq true}">
                        <div class="message-error mB1em"><spring:message code="fp.expired.token.valid"/></div>
                    </c:if>
                </div>
            </div>
            <div class="mB1em">
                <button type="submit" class="waves-effect waves-light btn light-blue darken-4">Изпрати</button>
            </div>
        </form>

    </div>
</div>

<%@ include file="../common/footer.jsp" %>