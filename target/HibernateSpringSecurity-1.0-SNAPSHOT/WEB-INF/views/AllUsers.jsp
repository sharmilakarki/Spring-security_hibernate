<%-- 
    Document   : AllUsers
    Created on : May 22, 2016, 12:38:46 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All User Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="pull-left">
            <p>
                <a href="${URL}/user/login" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored"></a>
            </p>
        </div>
        <div class="container">
            <table class="" id="tblData">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>USER NAME</th>
                        <th>EMAIL</th>
                        <th>ADDRESS</th>                   

                        <th>ADDED DATE</th>                   
                        <th>MODIFIED DATE</th>
                        <th>STATUS</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody>


                    <c:forEach var="user" items="${user}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                            <td>${user.address}</td>

                            <td>${user.addedDate}</td>
                            <td>${user.modifiedDate}</td>
                            <td>${user.status}</td>

                            <td><a href="${URL}/edit?id=${user.id}" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-edit">Edit  </span></a>
                                &nbsp;&nbsp;
                                <a href="${URL}/delete?id=${user.id}" id="deleteBtn" class="btn btn-sm btn-danger" onclick="return check();"><span class="glyphicon glyphicon-remove">Delete</span></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
    </body>
</html>
