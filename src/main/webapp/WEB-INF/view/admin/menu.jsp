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
            <a class="breadcrumb-link" href="<c:url value="/"/>">Начало</a>
            <span>Административен модул</span>
        </div>
    </div>
</div>
<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img class="right mT-15px img70" src="<c:url value="/resources/images/human-resources.png"/>"/>
        <div class="section-title">Административен модул</div>

        <ul class="collapsible popout" data-collapsible="accordion">
            <li>
                <div class="collapsible-header">
                    <i class="material-icons">book</i>
                    <span>Нова електронна книга</span>
                </div>
                <div class="collapsible-body">
                    <span>Добавяне на нова електронна книга</span>
                </div>
            </li>
            <li>
                <div class="collapsible-header">
                    <i class="material-icons">mode_edit</i>
                    Редактиране на електронна книга
                </div>
                <div class="collapsible-body">
                    <span>Редактиране на информацията на същестуваща електронна книга или изтриване.</span>
                </div>
            </li>
            <li>
                <div class="collapsible-header">
                    <i class="material-icons">assignment_ind</i>
                    Потребителски роли
                </div>
                <div class="collapsible-body">
                    <span>Lorem ipsum dolor sit amet.</span>
                </div>
            </li>
        </ul>

    </div>
</div>
<%@ include file="../common/footer.jsp" %>