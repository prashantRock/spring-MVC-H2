<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC + Gradle + H2</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/spring-mvc-db/dashboard/index">Home</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%
                if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
            %>
            <li><a href="/spring-mvc-db/login/signUpPage"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/spring-mvc-db/login/loginPage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <%} else {
            %>
            <li class="nav-item"><a href="#">Welcome <%=session.getAttribute("userid")%></a></li>
            <li class="nav-item"><a href='/spring-mvc-db/login/logout'>Log out</a></li>
            <%
                }
            %>
        </ul>
    </div>
</nav>

<br>
<c:if test="${userDetail.isEmpty()}">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <div class="alert alert-danger">No Student Record's Found</div>
        </div>
    </div>
</c:if>
<c:if test="${!userDetail.isEmpty()}">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <table class="table table-responsive">
                <tr>
                    <th>Email</th>
                    <th>Login Time</th>
                    <th>Login Status</th>
                </tr>
                <c:forEach var="user" items="${loginDetail}" >
                    <tr>
                        <td>${user.getUsername()}</td>
                        <td>${user.getLastLogin()}</td>
                        <td>${user.getLoginStatus()}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
</c:if>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
