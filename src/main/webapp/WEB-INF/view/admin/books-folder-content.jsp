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

<c:choose>
    <c:when test="${alreadyStarted eq true}">
        Процесът вече е стартиран....
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col s12">
                <ul class="tabs ">
                    <li class="tab col s6">
                        <a href="#new-book"><spring:message code="label.new.books"/></a>
                    </li>
                    <li class="tab col s6">
                        <a href="#existing-books"><spring:message code="label.existing.books"/></a>
                    </li>
                </ul>
            </div>
            <div id="new-book" class="col s12 mT2em">
                <c:if test="${empty importedBooks}">
                    <div>
                        <div class="alert alert-info info-tab align-center">
                            <spring:message code="empty.list"/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty importedBooks}">
                    <div class="mB-5px font-size-15"><spring:message code="table.records.count"/>:
                        <span class="bold">${importedBooks.size()}</span>
                    </div>
                    <table class="responsive-table striped">
                        <thead>
                        <tr>
                            <th class="w5"><spring:message code="table.number"/></th>
                            <th><spring:message code="table.name"/></th>
                            <th class="w10"><spring:message code="table.size"/></th>
                            <th class="w10"><spring:message code="table.add"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="${importedBooks}" varStatus="status">
                            <tr>
                                <td class="w5">${status.count}</td>
                                <td><c:out value="${book.key}"/></td>
                                <td class="w10">
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${(book.value / 1024)/1024}" /> MB
                                </td>
                                <td class="align-center w10">
                                    <img class="img24" src="<c:url value="/resources/images/success.png"/>"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id="existing-books" class="col s12 mT2em">
                <c:if test="${empty notimported}">
                    <div>
                        <div class="alert alert-info info-tab align-center">
                            <spring:message code="empty.list"/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty notimported}">
                    <div class="mB-5px font-size-15"><spring:message code="table.records.count"/>:
                        <span class="bold">${notimported.size()}</span>
                    </div>
                    <table class="responsive-table striped">
                        <thead>
                        <tr>
                            <th class="w5"><spring:message code="table.number"/></th>
                            <th><spring:message code="table.name"/></th>
                            <th class="w10"><spring:message code="table.size"/></th>
                            <th class="w10"><spring:message code="table.add"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="${notimported}" varStatus="status">
                            <tr>
                                <td class="w5">${status.count}</td>
                                <td><c:out value="${book.key}"/></td>
                                <td class="w10">
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${(book.value / 1024)/1024}"/> MB
                                </td>
                                <td class="align-center w10">
                                    <img class="img24" src="<c:url value="/resources/images/error.png"/>"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<script>
    $(document).ready(function(){
        $('.tabs').tabs();
    });
</script>