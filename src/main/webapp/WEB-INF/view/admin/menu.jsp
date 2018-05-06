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
            <span><spring:message code="title.administration.module"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img class="right mT-15px img70" src="<c:url value="/resources/images/human-resources.png"/>"/>
        <div class="section-title"><spring:message code="title.administration.module"/></div>

        <ul class="collapsible popout" data-collapsible="accordion">
            <li>
                <div class="collapsible-header">
                    <i class="material-icons">book</i>
                    <span><spring:message code="label.new.book"/></span>
                </div>
                <div class="collapsible-body">
                    <span><spring:message code="text.add.new.book.admin"/></span>
                    <a href="<c:url value="/admin/create-book"/>" class="right waves-effect waves-light btn light-blue darken-4" id="add-book-btn"><spring:message code="btn.add"/></a>
                </div>
            </li>
            <li>
                <div class="collapsible-header">
                    <i class="material-icons">mode_edit</i>
                    <span><spring:message code="label.edit.book"/></span>
                </div>
                <div class="collapsible-body">
                    <span><spring:message code="text.edit.book.admin"/></span>
                    <a href="#" class="right waves-effect waves-light btn light-blue darken-4" id="edit-book-btn"><spring:message code="btn.edit"/></a>
                </div>
            </li>
            <li>
                <div class="collapsible-header">
                    <i class="material-icons">assignment_ind</i>
                    <span><spring:message code="label.roles"/></span>
                </div>
                <div class="collapsible-body">
                    <span><spring:message code="text.roles.admin"/></span>
                    <a href="#" class="right waves-effect waves-light btn light-blue darken-4" id="add-role-btn"><spring:message code="btn.edit"/></a>
                </div>
            </li>
        </ul>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>