<%-- 
    Document   : Signup
    Created on : May 26, 2016, 9:53:21 AM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup Page</title>
         <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
      
                <div class="addUserForm">
                    <c:url var="add" value="/user/add"/>
                    <form action="${add}"  class="mdl-layout" modelAttribute="userAdd" method="POST" >
                        <h6>Register User</h6>
                        <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                            <label class="mdl-textfield__label" for="userName">Username</label>
                            <input class="mdl-textfield__input" type="text" id="username" name="userName"/>
                        </div>

                        <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                            <label class="mdl-textfield__label" for="password">password</label>
                            <input class="mdl-textfield__input" type="text" id="password" name="password"/>
                        </div>

                        <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                            <label class="mdl-textfield__label" for="email">email</label>
                            <input class="mdl-textfield__input" type="email" id="email" name="email"/>
                        </div>

                        <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                            <label class="mdl-textfield__label" for="address">address</label>
                            <input class="mdl-textfield__input" type="text" id="address" name="address"/>
                        </div>



                        <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                            <label class="mdl-textfield__label" for="status">status</label>
                            <input class="mdl-textfield__input" type="checkbox" id="status" value="1" name="status"/>
                        </div>

                        <div>
                            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                                Add
                            </button>
                        </div>

                    </form>
                </div>

    </body>
</html>
