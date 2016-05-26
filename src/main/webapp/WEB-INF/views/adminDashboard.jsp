<%-- 
    Document   : adminDashboard
    Created on : May 21, 2016, 8:56:48 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    <body
        <!-- The drawer is always open in large screens. The header is always shown,
          even in small screens. -->
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer
         mdl-layout--fixed-header">
            <header class="mdl-layout__header mdl-layout__header--scroll">
                <div class="mdl-layout__header-row">
                    <!-- Title -->

                    <!-- Add spacer, to align navigation to the right -->
                    <div class="mdl-layout-spacer"></div>
                    <!-- Navigation -->
                    <nav class="mdl-navigation">
                        <a class="mdl-navigation__link" href="">logout</a>
                        <a class="mdl-navigation__link" href=""> <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored" id="addUser">
                                <i class="material-icons">add</i>
                            </button></a>

                    </nav>
                </div>
            </header>
            <div class="mdl-layout__drawer">
                <span class="mdl-layout-title">Admin Dashboard</span>
                <nav class="mdl-navigation">
                    <a class="mdl-navigation__link" href="${URL}/user/AllUsers">User</a>
                    <a class="mdl-navigation__link" href="">User Roles</a>
                    <a class="mdl-navigation__link" href="">Employee</a>
                    <a class="mdl-navigation__link" href="">Department</a>
                </nav>
            </div>
            <main class="mdl-layout__content" >
               

       
    </main>
</div>
<script>
    $(document).on("ready", function () {
        $("#addUserForm").hide();
        $("#addUser").click(function () {
            $("#addUserForm").show();
        });
    });


</script>
</body>
</html>
