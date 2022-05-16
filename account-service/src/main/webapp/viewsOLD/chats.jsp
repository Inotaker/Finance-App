<%@ page import="by.it.academy.Mk_JD2_88_22.homework.model.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: Inotak
  Date: 2/17/2022
  Time: 6:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% User user = (User) session.getAttribute("user");%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>table, th, td, tr {
        border: 2px solid;
    }
    </style>
    <title>Chats</title>
</head>
<body>
<p style="background-color: royalblue"><b>Incoming messages:</b></p>
<br><a href="main.jsp"><input type="button" value="Return to main page" style="background-color: forestgreen"></a>
<jsp:include page="returnToMain.html"></jsp:include>
<table>
    <c:choose>
        <c:when test="${user!=null}">
            <c:forEach items="${incomingMessages}" var="users">
                <tr>
                    <td>
                        <p> ${users}</p>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td>
                    Please signIn!
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
<br>
<p style="background-color: indianred"><b>Outgoing messages:</b></p>
<table>
    <c:choose>
        <c:when test="${user!=null}">
            <c:forEach items="${outgoingMessages}" var="users">
                <tr>
                    <td>
                        <p> ${users}</p>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td>
                    Please signIn!
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
