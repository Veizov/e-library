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
            <a href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="mT2em">
        <form method="POST" action="<c:url value="/login"/>">
            <div>
                <div class="row ">
                    <div class="p50px-i col s6 offset-s3 z-depth-1 white" id="panell">
                        <form>
                            <div class="input-field" id="username">
                                <input type="text" name="email"/>
                                <label for="username"><spring:message code="label.email"/></label>
                            </div>
                            <div class="input-field" id="password">
                                <input type="password" name="password"/>
                                <label for="password"><spring:message code="label.password"/></label>
                            </div>
                            <c:if test="${error eq true}">
                                <div class="message-error mB1em">${sessionScope.get("SPRING_SECURITY_LAST_EXCEPTION")}</div>
                            </c:if>
                            <div class="mB1em">
                                <button type="submit" class="waves-effect waves-light btn light-blue darken-4"><spring:message code="label.log.in"/></button>
                            </div>
                            <div>
                                <div>
                                    <a href="<c:url value="/verification/new-verification-code"/>"><spring:message code="btn.verification.code"/></a>
                                </div>
                                <div>
                                    <a href="<c:url value="/registration"/>" title="<spring:message code="title.register"/>"><spring:message code="btn.create.account"/></a>
                                </div>
                                <div>
                                    <a href="#"><spring:message code="btn.forgotten.pass"/></a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>