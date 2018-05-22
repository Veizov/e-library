<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url value="/book-list-filter" var="bookListFilter"/>
<sec:authorize access="hasAuthority('ADMIN')" var="isUserAdmin"/>

<c:if test="${empty books}">
    <div>
        <div class="alert alert-info info-tab align-center">
            <spring:message code="empty.table"/>
        </div>
    </div>
</c:if>

<c:if test="${not empty books}">
    <div class="mB-5px font-size-15"><spring:message code="table.records.count"/>:
        <span class="bold">${booksTotal}</span>
    </div>

    <table class="responsive-table striped">
        <thead>
        <tr>
            <th class="w15px"><spring:message code="table.number"/></th>
            <th class="w150px"><spring:message code="label.cover"/></th>
            <th data-sort="title" class="table-sorter"><spring:message code="label.title"/></th>
            <th data-sort="category" class="table-sorter"><spring:message code="label.category"/></th>
            <th data-sort="size" class="table-sorter"><spring:message code="table.size"/></th>
            <th data-sort="date" class="table-sorter"><spring:message code="label.date.created"/></th>
            <th class="align-center-i"><spring:message code="table.options"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}" varStatus="status">
            <tr>
                <td>${(page*1 - 1*1)*(pageSize*1)+status.count*1}</td>
                <td class="w150px">
                    <div>
                        <c:choose>
                            <c:when test="${not empty book.cover.id}">
                                <img class="table-cover-image" src='<c:url value="/cover-image/${book.cover.id}"/>'/>
                            </c:when>
                            <c:otherwise>
                                <img class="table-cover-image" src='<c:url value="/resources/images/NoBookCover.png"/>'/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.category.name}"/></td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${(book.file.filesize / 1024)/1024}" /> MB</td>
                <td><fmt:formatDate timeZone="Europe/Sofia" pattern="dd.MM.yyyy HH:mm:ss" value="${book.createdDate}"/></td>
                <td class="">
                    <div>
                        <a class="mR5px" href="<c:url value="/book/${book.id}"/>"
                           title="<spring:message code="btn.view"/>">
                            <img class="img24 " src="<c:url value="/resources/images/preview-icon.png"/>"/>
                        </a>
                        <a class="mR5px" href="<c:url value="/download-file/${book.file.id}"/>"
                           title="<spring:message code="btn.download"/>">
                            <img class="img24" src="<c:url value="/resources/images/download-icon.png"/>"/>
                        </a>
                        <c:if test="${isUserAdmin}">
                            <a class="mR5px" href="<c:url value="/admin/create-book?id=${book.id}"/>"
                               title="<spring:message code="btn.edit"/>">
                                <img class="img24" src="<c:url value="/resources/images/edit-icon.png"/>"/>
                            </a>
                            <a id="delete-book-btn" class="mR5px" href="javascript:void(0)"
                               title="<spring:message code="btn.delete"/>" data-id="${book.id}">
                                <img class="img24" src="<c:url value="/resources/images/delete-icon.png"/>"/>
                            </a>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <custom:paginator baseUrl="${bookListFilter}" page="${page}" pageSize="${pageSize}"
                      tableContainerDiv="search-book-div" totalRecords="${booksTotal}" isAjax="true"
                      additionalParams="&isFilter=true"/>
    <div>
        <input type="hidden" value="${sortOrder}" id="book-sort-order"/>
        <input type="hidden" value="${sessionScope.get('searchBookFilter').title}" id="session-filter-title"/>
        <input type="hidden" value="${sessionScope.get('searchBookFilter').author}" id="session-filter-author"/>
        <input type="hidden" value="${sessionScope.get('searchBookFilter').yearFrom}" id="session-filter-yearFrom"/>
        <input type="hidden" value="${sessionScope.get('searchBookFilter').yearTo}" id="session-filter-yearTo"/>
        <input type="hidden" value="${sessionScope.get('searchBookFilter').category}" id="session-filter-category"/>
        <input type="hidden" value="${page}" id="request-page"/>
        <input type="hidden" value="${pageSize}" id="request-page-size"/>
    </div>
</c:if>