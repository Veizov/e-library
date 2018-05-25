<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<c:url value="/admin/create-book" var="createBookURL"/>

<div class="bg_breadcrumbs z-depth-1">
    <div class="container">
        <div class="breadcrumbs clearfix">
            <a class="breadcrumb-link" href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
            <a class="breadcrumb-link" href="<c:url value="/admin/menu"/>"><spring:message code="title.administration.module"/></a>
            <span><spring:message code="title.roles"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/human-resources.png"/>"/>
        <div class="section-title">
            <spring:message code="title.roles"/>
        </div>

        <div>
            <div class="row">
                <div class="input-field col s4">
                    <select id="users-select">
                        <option value=''><spring:message code="label.select.default"/></option>
                        <c:forEach var="user" items="${users}">
                            <option value='${user.email}'>${user.email}</option>
                        </c:forEach>
                    </select>
                    <label><spring:message code="label.users"/></label>
                </div>
            </div>

            <div id="roles-table-div"></div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jsp" %>
<script src='<c:url value="/resources/js/roles.js"/>' type="text/javascript"></script>