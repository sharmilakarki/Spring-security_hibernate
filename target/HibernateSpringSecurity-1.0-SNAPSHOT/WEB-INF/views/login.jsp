<%-- 
    Document   : login
    Created on : May 21, 2016, 5:02:53 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div class="mdl-layout">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="message">${message}}</div>
            </c:if> 
            <c:url value='/j_spring_security_check' var="login" />
            <c:url var="loginUrl" value="/login" />
            <form   action="${loginUrl}" class="mdl-layout" modelAttribute="user" method="POST">
                <h3>Login Form</h3>
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="username">Username</label>
                    <input class="mdl-textfield__input" type="text" id="username" name="username"/>

                </div>
                </br>
                <div class="mdl-layout mdl-textfield mdl-js-textfield">
                    <label class="mdl-textfield__label" for="password">Password</label>
                    <input class="mdl-textfield__input" type="password" id="password" name="password"/>

                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />

                <div>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" type="submit">
                        Login
                    </button>
                </div>
            </form
        </div>
    </body>
</html>
