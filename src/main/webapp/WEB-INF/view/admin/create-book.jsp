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
            <c:choose>
                <c:when test="${not empty book.id}">
                    <a class="breadcrumb-link" href="<c:url value="/books"/>"><spring:message code="title.books"/></a>
                    <span>Редактиране на ${book.title}</span>
                </c:when>
                <c:otherwise>
                    <a class="breadcrumb-link" href="<c:url value="/admin/menu"/>"><spring:message code="title.administration.module"/></a>
                    <span> <spring:message code="title.create.book"/></span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/open-book.png"/>"/>
        <div class="section-title">
            <c:choose>
                <c:when test="${not empty book.id}">
                    Редактиране на ${book.title}
                </c:when>
                <c:otherwise>
                    <spring:message code="title.create.book"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div>
            <form:form id="create-book-form" cssClass="col s12" modelAttribute="book" action="${createBookURL}" method="post" enctype="multipart/form-data">
                <div class="row float-left mB0">
                    <div class="input-field col">
                        <label id="cover-image-label" for="image-file" class="custom-file-upload mB1em cover-image-label
                           <c:if test="${not empty book.cover.filename}"><c:out value="remove-pb"/></c:if>">
                            <c:choose>
                                <c:when test="${not empty book.cover.filename}">
                                    <img id="cover-image"
                                         src='<c:out value="data:image/jpg;base64,${book.cover.encodedContent}"/>'
                                         class="cover-image">
                                </c:when>
                                <c:otherwise>
                                    <div class="cover-text"><spring:message code="label.cover"/></div>
                                </c:otherwise>
                            </c:choose>
                        </label>
                        <input class="display-none" type="file" name="imageFile" id="image-file" />
                    </div>
                </div>
                <div class="row mB0">
                    <div class="input-field col">
                        <label id="book-file-label" for="book-file" class="custom-file-upload mB1em book-file-label
                        <c:if test="${not empty book.file.filename}"><c:out value="remove-pb"/></c:if>">
                            <c:choose>
                                <c:when test="${not empty book.file.filename}">
                                    <img src="<c:url value="/resources/images/if_pdf_65920.png"/>"/>
                                    <p class="w500px"><c:out value="${book.file.filename}"/></p>
                                </c:when>
                                <c:otherwise>
                                    <div class="align-center mT30px"><spring:message code="label.file"/></div>
                                </c:otherwise>
                            </c:choose>
                        </label>
                        <input class="display-none" type="file" name="bookFile" id="book-file" />
                        <div class="m_T5px" id="book-file-error-msg">
                            <form:errors path="file.filename" cssClass="message-error"/>
                        </div>
                    </div>

                </div>
                <div class="clear" id="image-file-error-msg">
                    <form:errors path="cover.filename" cssClass="message-error"/>
                </div>
                <div class="row">
                    <div class="input-field  col s4">
                        <input type="text" id="new-book-title" name="title" value="${book.title}">
                        <label for="new-book-title" class=""><spring:message code="label.title"/></label>
                        <form:errors path="title" cssClass="message-error"/>
                    </div>
                    <div class="input-field  col s4 ">
                        <input type="text" id="new-book-author" name="author" value="${book.author}">
                        <label for="new-book-author" class=""><spring:message code="label.author"/></label>
                        <form:errors path="author" cssClass="message-error"/>
                    </div>
                    <div class="input-field  col s4 ">
                        <input type="text" id="new-book-year" name="year" value="${book.year}">
                        <label for="new-book-year" class=""><spring:message code="label.year"/></label>
                        <form:errors path="year" cssClass="message-error"/>
                    </div>
                </div>
                <div class="row mB0">
                    <div class="input-field  col s4">
                        <input type="text" id="new-book-isbn" name="isbn" value="${book.isbn}">
                        <label for="new-book-year" class=""><spring:message code="label.isbn"/></label>
                        <form:errors path="isbn" cssClass="message-error"/>
                    </div>
                    <div class="input-field col s4">
                        <select name="category.id">
                            <c:forEach var="category" items="${categories}">
                                <option value='${category.id}'
                                        <c:if test="${category.id eq book.category.id}">
                                            <c:out value="selected='selected'"/>
                                        </c:if>
                                >${category.name}</option>
                            </c:forEach>
                        </select>
                        <label><spring:message code="label.category"/></label>
                        <form:errors path="category.id" cssClass="message-error"/>
                    </div>
                    <div class="input-field col s4">
                        <select name="language.id">
                            <c:forEach var="language" items="${languages}">
                                <option value='${language.id}'
                                        <c:if test="${language.id eq book.language.id}">
                                            <c:out value="selected='selected'"/>
                                        </c:if>
                                >${language.name}</option>
                            </c:forEach>
                        </select>
                        <label><spring:message code="label.language"/></label>
                        <form:errors path="language.id" cssClass="message-error"/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 custom-textarea">
                        <textarea id="new-book-description" name="description" class="materialize-textarea"
                                  data-length="2000">${book.description}</textarea>
                        <label for="new-book-description"><spring:message code="label.short.descripition"/></label>
                        <form:errors path="description" cssClass="message-error"/>
                    </div>
                </div>
                <div>
                    <button type="submit" class="waves-effect waves-light btn light-blue darken-4">
                        <i class="material-icons left">check</i><spring:message code="btn.save"/>
                    </button>
                </div>
                <input type="hidden" name="id" value="${book.id}">
            </form:form>
        </div>
    </div>

</div>
<%@ include file="../common/footer.jsp" %>

<script src='<c:url value="/resources/js/create-book.js"/>' type="text/javascript"></script>