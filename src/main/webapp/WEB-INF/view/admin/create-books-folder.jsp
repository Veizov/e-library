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
            <span><spring:message code="title.create.book.folder"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/folder-icon.png "/>"/>
        <div class="section-title">
            <spring:message code="title.create.book.folder"/>
        </div>
        <div id="books-folder-list">
            <div id="books-folder-div" class="mB1em">
                <blockquote><spring:message code="text.folder.books.path"/></blockquote>
                <blockquote><spring:message code="text.folder.books.size"/></blockquote>
                <div class="row">
                    <div class="input-field col s12">
                        <div>
                            <input type="text" id="folder-path">
                            <label for="folder-path"><spring:message code="label.folder.path"/></label>
                        </div>
                        <div class="message-error" id="folder-error-msg"></div>
                    </div>
                </div>
                <div>
                    <button id="add-books-folder-btn" type="button"
                            class="waves-effect waves-light btn light-blue darken-4">
                        <i class="material-icons left">check</i><spring:message code="btn.add"/>
                    </button>
                </div>
            </div>

            <div id="progress-div" class="display-none">
                <div class="align-center">
                    <span id="progress-percent">0%</span>
                </div>
                <div class="progress blue lighten-5 h20px">
                    <div id="books-progress-bar" class="determinate light-blue darken-4" style="width: 0%"></div>
                </div>
            </div>
        </div>
        <input type="hidden" id="in-progress" value="${inProgress}">
    </div>
</div>
<%@ include file="../common/footer.jsp" %>