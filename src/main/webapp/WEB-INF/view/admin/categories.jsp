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
            <span><spring:message code="title.categories"/></span>
        </div>
    </div>
</div>

<div class="container main-content">
    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/list-icon.png"/>"/>
        <div class="section-title">
            <spring:message code="label.categories"/>
        </div>
        <blockquote><spring:message code="text.categories.warning"/></blockquote>

        <div class="mB1em">
            <div id="categories-table-div">
                <jsp:include page="categories_table.jsp"/>
            </div>
        </div>
        <div>
            <button data-target="add-category-form" type="button" class="waves-effect waves-light btn light-blue darken-4 modal-trigger">
                <i class="material-icons left">add</i><spring:message code="btn.add.category"/>
            </button>
        </div>
    </div>
</div>
<div id="add-category-form" class="modal">
    <div class="modal-header align-center">
        <h5 class="mT1em"><spring:message code="title.add.new.category"/></h5>
    </div>
    <div class="modal-content">
        <div class="row">
            <div class="input-field col s12">
                <input type="text" id="category-name-inp">
                <label for="category-name-inp" class=""><spring:message code="label.name"/></label>
                <div id="category-name-valid" class="message-error display-none"><spring:message code="empty.field.valid"/></div>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input type="text" id="category-nameEn-inp">
                <label for="category-nameEn-inp" class=""><spring:message code="label.name.en"/></label>
                <div id="category-nameEn-valid" class="message-error display-none"><spring:message code="empty.field.valid"/></div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a id="add-category-submit-btn" href="javascript:void(0);" class="waves-effect waves-light btn-flat"><spring:message code="btn.add"/></a>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>
<script>
    $("body").on("click", "#add-category-submit-btn", function (e) {
        $('#category-nameEn-valid').addClass('display-none');
        $('#category-name-valid').addClass('display-none');

        var name = $('#category-name-inp').val().trim();
        var nameEn = $('#category-nameEn-inp').val().trim();
        if (name.length < 1) {
            $('#category-name-valid').removeClass('display-none');
        } else if (nameEn.length < 1) {
            $('#category-nameEn-valid').removeClass('display-none');
        } else {
            $('.preloader').show();
            $.ajax({
                url: '/e-library/admin/add-category',
                type: 'POST',
                data: {
                    name: name,
                    nameEn: nameEn
                },
                success: function (data) {
                    $('#categories-table-div').empty();
                    $('#categories-table-div').append(data);
                    $('.preloader').hide();
                    $('#add-category-form').modal('close');

                    $('#category-nameEn-valid').addClass('display-none');
                    $('#category-name-valid').addClass('display-none');
                },
                error: function () {
                    $('.preloader').hide();
                    $('#add-category-form').modal('close');
                    $('#error-modal').modal('open');

                    $('#category-nameEn-valid').addClass('display-none');
                    $('#category-name-valid').addClass('display-none');
                }
            });
        }
    });

    $("body").on("click", "#delete-category-btn", function (e) {
        var id = $(this).data('id');

        $('.preloader').show();
        $.ajax({
            url: '/e-library/admin/delete-category',
            type: 'POST',
            data: {
                id: id
            },
            success: function (data) {
                $('#categories-table-div').empty();
                $('#categories-table-div').append(data);
                $('.preloader').hide();
            },
            error: function () {
                $('.preloader').hide();
                $('#error-modal').modal('open');
            }
        });
    });


</script>