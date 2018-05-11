<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:directive.attribute name="page" type="java.lang.Integer" required="true" rtexprvalue="true"/>
<jsp:directive.attribute name="pageSize" type="java.lang.Integer" required="true" rtexprvalue="true"/>
<jsp:directive.attribute name="totalRecords" type="java.lang.Integer" required="true" rtexprvalue="true"/>
<jsp:directive.attribute name="pageVar" type="java.lang.String" />
<jsp:directive.attribute name="pageSizeVar" type="java.lang.String" />
<jsp:directive.attribute name="baseUrl" type="java.lang.String" required="true" rtexprvalue="true"/>
<jsp:directive.attribute name="isAjax" type="java.lang.Boolean" required="false" rtexprvalue="true"/>
<jsp:directive.attribute name="tableContainerDiv" type="java.lang.String" required="true"/>
<jsp:directive.attribute name="additionalParams" type="java.lang.String" required="false" rtexprvalue="true"/>



<c:if test="${empty isAjax}">
    <c:set var="isAjax" value="false"/>
</c:if>
<c:if test="${empty pageVar}">
    <c:set var="pageVar" value="page"/>
</c:if>
<c:if test="${empty pageSizeVar}">
    <c:set var="pageSizeVar" value="pageSize"/>
</c:if>

<c:choose>
    <c:when test="${totalRecords%pageSize == 0}">
        <fmt:parseNumber value="${totalRecords/pageSize}" integerOnly="true" var="totalPagesTruncated"/>
        <c:set var="totalPages" value="${totalPagesTruncated}"/>
    </c:when>
    <c:otherwise>
        <fmt:parseNumber value="${totalRecords/pageSize + 1}" integerOnly="true" var="totalPagesTruncated"/>
        <c:set var="totalPages" value="${totalPagesTruncated}"/>
    </c:otherwise>
</c:choose>

<div class="paginator">

    <nav>
        <ul class="pager">
            <li class="previous ${page == 1 ? 'disabled' : ''}"><a class="paginatorHref ${page == 1 ? 'paginator-href-disabled' : ''}"
                                                                   href="${baseUrl}?${pageVar}=${page-1}&amp;${pageSizeVar}=${pageSize}&amp;${additionalParams}"><span aria-hidden="true">&larr;</span>
                <spring:message code="paginator.previous"/> </a></li>
            <li>
                <span>
                    <spring:message code="paginator.current.page"/>
                    <select class="form-control browser-default">
                        <c:forEach var="p" begin="${1}" end="${totalPages}">
                            <option value="${p}" <c:out value="${p == page ? 'selected=selected': ''}"/>
                                    data-href="${baseUrl}?${pageVar}=${p}&amp;${pageSizeVar}=${pageSize}&amp;${additionalParams}" >
                                    ${p}
                            </option>
                        </c:forEach>
                    </select>&nbsp;
                    <spring:message code="paginator.of.total"/>&nbsp;<b>${totalPages}</b>
                </span>
            </li>
            <li>
                <span>
                    <spring:message code="paginator.current.page.size"/>
                    <select class="form-control browser-default">
                        <option value="5" <c:out value="${pageSize == 5 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=5&amp;${additionalParams}">
                            5
                        </option>
                        <option value="10" <c:out value="${pageSize == 10 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=10&amp;${additionalParams}">
                            10
                        </option>
                        <option value="20" <c:out value="${pageSize == 20 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=20&amp;${additionalParams}">
                            20
                        </option>
                        <option value="50" <c:out value="${pageSize == 50 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=50&amp;${additionalParams}">
                            50
                        </option>
                        <option value="100" <c:out value="${pageSize == 100 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=100&amp;${additionalParams}">
                            100
                        </option>
                        <option value="500" <c:out value="${pageSize == 500 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=500&amp;${additionalParams}">
                            500
                        </option>
                        <option value="1000" <c:out value="${pageSize == 1000 ? 'selected=selected': ''}"/>
                                data-href="${baseUrl}?${pageVar}=1&amp;${pageSizeVar}=1000&amp;${additionalParams}">
                            1000
                        </option>
                    </select>
                </span>
            </li>

            <li class="next ${page == totalPages or totalPages ==0 ? 'disabled' : ''}"><a class="paginatorHref ${page == totalPages ? 'paginator-href-disabled' : ''}"
                                                                        href="${baseUrl}?${pageVar}=${page+1}&amp;${pageSizeVar}=${pageSize}&amp;${additionalParams}">
                <spring:message code="paginator.next"/> <span aria-hidden="true">&rarr;</span></a></li>
        </ul>
    </nav>

    <a class="optionHref paginatorHref " href="#" style="display: none">Hidden</a>

    <script type="text/javascript">
        $("#${tableContainerDiv} .paginator select").on("change", function(e){
            var optionHref = $(this).find("option:selected").attr("data-href");
            var hiddenA = $("#${tableContainerDiv} .paginator a.optionHref")[0];
            $(hiddenA).attr("href", optionHref);
            $(hiddenA).click();
        });

    </script>

    <c:if test="${isAjax}">
        <script type="text/javascript">
            $("#${tableContainerDiv} .paginator a.paginatorHref").on("click", function(e){
                e.preventDefault();
                if($(this).hasClass("paginator-href-disabled")){
                    return false;
                }
                var callUrl =$(this).attr("href");
                $('.preloader').show();
                $.ajax({
                    url: callUrl,
                    method: 'post',
                    data: "partial=true"
                }).done(function(html){
                    $("#${tableContainerDiv}").html(html);
                    $('.preloader').hide();
                }).fail(function(data, e){
                    alert("${errorMessage} ");
                    $('.preloader').hide();
                });
            });
        </script>
    </c:if>

</div>
