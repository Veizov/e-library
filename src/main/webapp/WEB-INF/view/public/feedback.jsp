<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<c:url value="/feedback" var="feedbackURL"/>
<div class="bg_breadcrumbs hidden-xs hidden-sm">
    <div class="container">
        <div class="breadcrumbs clearfix">
            <a class="breadcrumb-link" href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
            <span><spring:message code="title.feedback"/></span>
        </div>
    </div>
</div>


<div class="container main-content ">

    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/feedback-icon.png"/>"/>
        <div class="section-title"><spring:message code="title.feedback"/></div>

        <c:if test="${sendFeedbackSuccessful == true}">
            <div class="alert alert-success info-tab align-center">Съобщението е изпратено успешно !</div>
        </c:if>

        <form:form id="create-book-form" cssClass="col s12" modelAttribute="feedbackMessage" action="${feedbackURL}"
                   method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input type="text" id="feedback-title" name="title" value="<c:out value="${feedbackMessage.title}"/>"/>
                    <label for="feedback-title"><spring:message code="label.title"/></label>
                    <form:errors path="title" cssClass="message-error"/>
                </div>
                <div class="input-field col s12 custom-textarea">
                    <textarea name="text" id="feedback-body" class="materialize-textarea"
                              data-length="2000"><c:out value="${feedbackMessage.text}"/></textarea>
                    <label for="feedback-body">Текст</label>
                    <form:errors path="text" cssClass="message-error"/>

                </div>
            </div>
            <div class="mB1em">
                <button type="submit" class="waves-effect waves-light btn light-blue darken-4">Изпрати</button>
            </div>
        </form:form>


    </div>
</div>

<%@ include file="../common/footer.jsp" %>