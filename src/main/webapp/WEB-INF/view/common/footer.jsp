<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<footer class="page-footer light-blue darken-4 mT2em">
    <span class="gototop" style="display: none;"><spring:message code="footer.gototop"/></span>
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">За електронната библиотека</h5>

                <p class="grey-text text-lighten-4">Електронната библитека на Технически университет - София е създадена с цел по-лесен обмен на информация и техническа литература между преподаватели и студенти.  </p>
                <div>
                    <div class="float-left">
                        <img width="70" height="70" src="<c:url value="/resources/images/logo-tu-gray.png"/>"/>
                    </div>
                    <div class="float-left">
                        <div class="center-align">
                            <spring:message code="logo.sub.title"/>
                        </div>
                        <div class="center-align" style="font-size: 14px">
                            <spring:message code="logo.title"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Контакти</h5>
                <ul>
                    <li class="mB5px">
                        <img class="img24 v-middle" src="<c:url value="/resources/images/ico_tel.png"/>"/>
                        <span>0899838885</span>
                    </li>
                    <li class="mB5px">
                        <img class="img24 v-middle" src="<c:url value="/resources/images/ico_mail.png"/>" />
                        <span>denislav.veizov@duosoft.net</span>
                    </li>
                    <li class="mB5px">
                        <img class="img24 v-middle" src="<c:url value="/resources/images/ico_web.png"/>" />
                        <a class="grey-text text-lighten-3" href="https://tu-sofia.bg/" target="_blank">tu-sofia.bg</a>
                    </li>
                    <li class="mB5px">
                        <img class="img24 v-middle" src="<c:url value="/resources/images/ico_address.png"/>" />
                        <span>София, бул."Кл. Охридски" 8</span>
                    </li>
                    <%--<li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>--%>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2018 D.S.V
        </div>
    </div>
</footer>
<script src='<c:url value="/resources/js/main.js"/>' type="text/javascript"></script>


</body>
</html>
