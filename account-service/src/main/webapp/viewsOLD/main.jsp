<%@ page import="by.it.academy.Mk_JD2_88_22.homework.model.User" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!doctype html>
<html>
<head>
    <title>Main page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5">
        </div>
        <div class="col-sm-6">
            <b>Welcome, <c:choose>
                <c:when test="${user!=null}">
                    <%= user.getUsername()%>
                </c:when>
                <c:otherwise>
                    <a style="isolation: revert" href="signUp.jsp">stranger</a>
                </c:otherwise>
            </c:choose>!
            </b>
        </div>
        <div class="col-sm-1">
            <c:if test="${user==null}">
                <a href="signIn.jsp"><input class="btn-primary" type="button" value="Login"></a>
            </c:if>
            <c:if test="${user!=null}">
                <form action="${pageContext.request.contextPath}/views/logout" method="get">
                    <input class="btn-dark" type="submit" value="Logout">
                </form>
            </c:if>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-1">
            <c:if test="${user!=null}">
                <form action="${pageContext.request.contextPath}/views/message" method="get">
                    <input class="btn-secondary" type="submit" value="Print message">
                </form>
            </c:if>
            <c:if test="${user!=null}">
                <form action="${pageContext.request.contextPath}/views/chats" method="get">
                    <input class="btn-secondary" type="submit" value="Chats">
                </form>
            </c:if>
        </div>
        <div class="col-sm-10">
        </div>
        <div class="col-sm-1">
            <c:if test="${user==null}">
                <a href="signUp.jsp"><input class="btn-primary" type="button" value="Register"></a>
            </c:if>
        </div>
    </div>
</div>
<c:if test="${user!=null}">
    <b><%=user.toString()%>
    </b>
</c:if>
</body>
</html>
