<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<c:url value="/registration" var="registerURL"/>
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

        <form:form cssClass="col s12" modelAttribute="user" action="${registerURL}" method="post">
            <div class="row">
                <div class="input-field col s4">
                    <input type="text" id="fname" name="fname" value="${user.fname}">
                    <label for="fname" class=""><spring:message code="label.fname"/></label>
                    <form:errors path="fname" cssClass="message-error"/>
                </div>
                <div class="input-field col s4">
                    <input type="text" id="sname" name="sname" value="${user.sname}">
                    <label for="sname"><spring:message code="label.sname"/></label>
                    <form:errors path="sname" cssClass="message-error"/>
                </div>
                <div class="input-field col s4">
                    <input type="text" id="lname" name="lname" value="${user.lname}">
                    <label for="lname"><spring:message code="label.lname"/></label>
                    <form:errors path="lname" cssClass="message-error"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s4 ">
                    <input type="text" id="email" name="email" value="${user.email}">
                    <label for="email" class=""><spring:message code="label.email"/></label>
                    <form:errors path="email" cssClass="message-error"/>
                </div>
                <div class="input-field col s4">
                    <input type="password" id="password" name="password" value="${user.password}">
                    <label for="password"><spring:message code="label.password"/></label>
                    <form:errors path="password" cssClass="message-error"/>
                </div>
                <div class="input-field col s4">
                    <input type="password" id="password-confirm" name="confirmPassword" value="${user.confirmPassword}">
                    <label for="password-confirm"><spring:message code="label.password.confirm"/></label>
                    <form:errors path="confirmPassword" cssClass="message-error"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 custom-textarea">
                    <textarea id="description" name="description" class="materialize-textarea"  data-length="300">${user.description}</textarea>
                    <label for="description"><spring:message code="label.short.descripition"/></label>
                    <form:errors path="description" cssClass="message-error"/>
                </div>
            </div>
            <div>
                <button type="submit" class="waves-effect waves-light btn light-blue darken-4">
                    <i class="material-icons left">check</i><spring:message code="btn.register"/>
                </button>
            </div>
        </form:form>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>