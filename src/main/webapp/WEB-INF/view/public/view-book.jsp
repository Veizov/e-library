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
            <a class="breadcrumb-link" href="<c:url value="/books"/>"><spring:message code="title.books"/></a>
            <span><c:out value="${book.title}"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/open-book.png"/>"/>
        <div class="section-title">
            <c:out value="${book.title}"/>
        </div>
        <div>
            <div>
                <div class="left w15">
                    <c:choose>
                        <c:when test="${not empty book.cover.id}">
                            <img class="materialboxed view-book-cover cover-border"
                                 src='<c:url value="/cover-image/${book.cover.id}"/>'/>
                        </c:when>
                        <c:otherwise>
                            <img class="materialboxed view-book-cover cover-border"
                                 src='<c:url value="/resources/images/NoBookCover.png"/>'/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="left view-tab">
                    <div class="row">
                        <div class="col s4">
                            <label><spring:message code="label.isbn"/></label>
                            <p><c:out value="${book.isbn}"/></p>
                        </div>
                        <div class="col s4">
                            <label><spring:message code="label.author"/></label>
                            <p><c:out value="${book.author}"/></p>
                        </div>

                        <div class="col s4">
                            <label><spring:message code="label.language"/></label>
                            <p><c:out value="${book.language.name}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s4">
                            <label><spring:message code="label.size"/></label>
                            <p><fmt:formatNumber type="number" maxFractionDigits="2" value="${(book.file.filesize / 1024)/1024}"/> MB</p>
                        </div>
                        <div class="col s4">
                            <label><spring:message code="label.page.number"/></label>
                            <p><c:out value="${book.numberOfPages}"/></p>
                        </div>
                        <div class="col s4">
                            <label><spring:message code="label.year"/></label>
                            <p><c:out value="${book.year}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s4">
                            <label><spring:message code="label.category"/></label>
                            <p><c:out value="${book.category.name}"/></p>
                        </div>
                        <div class="col s4">
                            <label><spring:message code="label.date.created"/></label>
                            <p><fmt:formatDate timeZone="Europe/Sofia" pattern="dd.MM.yyyy HH:mm:ss" value="${book.createdDate}"/></p>
                        </div>
                        <div class="col s4">
                            <label><spring:message code="label.format"/></label>
                            <p><spring:message code="text.isbn.format"/></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear">
                <div>
                    <div class="row">
                        <div class="col s12">
                            <label><spring:message code="label.descripition"/></label>
                            <p><c:out value="${book.description}"/></p>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <a href="<c:url value="/download-file/${book.file.id}"/>" class="waves-effect waves-light btn light-blue darken-4">
                    <i class="material-icons left">file_download</i>Свали
                </a>
            </div>
        </div>
    </div>

</div>
<%@ include file="../common/footer.jsp" %>