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



<c:if test="${empty categories}">
    <div>
        <div class="alert alert-info info-tab align-center">
            <spring:message code="empty.table"/>
        </div>
    </div>
</c:if>

<c:if test="${not empty categories}">
    <table class="responsive-table striped">
        <thead>
        <tr>
            <th class="w5"><spring:message code="table.number"/></th>
            <th><spring:message code="table.name"/></th>
            <th class=""><spring:message code="table.name.en"/></th>
            <th class="w50px"><spring:message code="table.options"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}" varStatus="status">
            <tr>
                <td class="w5">${status.count}</td>
                <td>${category.name}</td>
                <td>${category.nameEn}</td>
                <td class="w50px">
                    <a id="delete-category-btn" class="mR5px" href="javascript:void(0)"
                       title="<spring:message code="btn.delete"/>" data-id="${category.id}" >
                        <img class="img24" src="<c:url value="/resources/images/delete-icon.png"/>"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

