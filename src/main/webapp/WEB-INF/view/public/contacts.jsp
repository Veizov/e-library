<%--
  Created by IntelliJ IDEA.
  User: denislav.veizov
  Date: 23.1.2018 г.
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<c:url value="/registration" var="registerURL"/>
<div class="bg_breadcrumbs z-depth-1">
    <div class="container">
        <div class="breadcrumbs clearfix">
            <a class="breadcrumb-link" href="<c:url value="/"/>"><spring:message code="breadcrumb.home"/></a>
            <span><spring:message code="title.contacts"/></span>
        </div>
    </div>
</div>
<div class="container main-content ">

    <div class="z-depth-2 white mT2em p50px">
        <img width="70" height="70" class="right mT-15px" src="<c:url value="/resources/images/maps.png"/>"/>
        <div class="section-title"><spring:message code="title.contacts"/></div>


        <div id="map" class="float-left" style="width: 70%; height: 600px;"></div>
        <div class="mB1em row">
            <div class="display-inline-block col s3">
                <div class="mB1em">
                    <i class="material-icons prefix v-middle">phone</i>
                    <span><spring:message code="contacts.phone"/></span>
                </div>
                <div class="mB1em">
                    <i class="material-icons prefix v-middle">mail</i>
                    <span><spring:message code="contacts.mail"/></span>
                </div>
            </div>
            <div class="display-inline-block col s3">
                <div class="mB1em">
                    <i class="material-icons prefix v-middle">website</i>
                    <a class="black-text text-lighten-3" href="https://tu-sofia.bg/" target="_blank"><spring:message code="contacts.websitr"/></a>
                </div>
                <div class="mB1em">
                    <i class="material-icons prefix v-middle">location_on</i>
                    <span><spring:message code="contacts.address"/></span>
                </div>
            </div>
        </div>
    </div>
    <span class="" style="clear: both;"></span>
</div>
<%@ include file="../common/footer.jsp" %>
<script type="text/javascript">

    function initMap() {
        var infoWindows = [];

        var myLatLng = {lat: 42.657061, lng: 23.355109};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 16,
            center: myLatLng
        });

        var markers = [
            createMarker({lat: 42.657061, lng: 23.355109}, map, 'бул."Кл. Охридски" 8'),
        ];

        function createMarker(latLng, map, message) {
            var marker = new google.maps.Marker({
                map: map,
                position: latLng,
                animation: google.maps.Animation.DROP,
            });

            var infowindow = new google.maps.InfoWindow({
                content: message,
            });

            infoWindows.push(infowindow);

            marker.addListener('click', function () {
                for (var i = 0; i < infoWindows.length; i++) {
                    infoWindows[i].close();
                }

                infowindow.open(map, marker);
            });

            return marker;
        }

    }
</script>

<script type="text/javascript"
        src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAhvAbK_MYERwN8WnlJLyNtZhH8txSaoQk&callback=initMap&language=bg&region=BG"></script>