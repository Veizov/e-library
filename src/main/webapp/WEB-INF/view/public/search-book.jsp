<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<%@ include file="../common/header.jsp" %>
<div class="bg_breadcrumbs z-depth-1">
    <div class="container">
        <div class="breadcrumbs clearfix">
            <a class="breadcrumb-link" href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
            <span><spring:message code="title.books"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/archive.png"/>"/>
        <div class="section-title"><spring:message code="title.books"/></div>

        <div class="" style="padding: 20px;">
            <div class="row">
                <div class="input-field col s3">
                    <input type="text" id="title-filter" class="books-filter">
                    <label for="title-filter"><spring:message code="label.title"/></label>
                </div>
                <div class="input-field col s3">
                    <input type="text" id="author-filter" class="books-filter">
                    <label for="author-filter"><spring:message code="label.author"/></label>
                </div>
                <div class="input-field col s3">
                    <input type="text" id="year-from-filter" class="books-filter">
                    <label for="year-from-filter"><spring:message code="label.year.from"/></label>
                </div>
                <div class="input-field col s3">
                    <input type="text" id="year-to-filter" class="books-filter">
                    <label for="year-to-filter"><spring:message code="label.year.to"/></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <select id="category-filter">
                        <option value=''><spring:message code="label.select.default"/></option>
                        <c:forEach var="category" items="${categories}">
                            <option value='${category.name}'>${category.name}</option>
                        </c:forEach>
                    </select>
                    <label><spring:message code="label.category"/></label>
                </div>

            </div>
            <div class="float-right mB2em">
                <button id="search-btn" type="button" class="waves-effect waves-light btn light-blue darken-4">
                    <i class="material-icons left">search</i><spring:message code="btn.search"/>
                </button>
                <button id="clear-btn" type="button" class="waves-effect waves-light btn grey">
                    <i class="material-icons left">clear</i><spring:message code="btn.clear"/>
                </button>
            </div>
        </div>

        <div id="search-book-div" class="clear">
            <jsp:include page="search-book-table.jsp"/>
        </div>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>

<script src='<c:url value="/resources/js/book-table.js"/>' type="text/javascript"></script>