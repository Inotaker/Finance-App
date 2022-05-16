<%--
  Created by IntelliJ IDEA.
  User: Inotak
  Date: 2/17/2022
  Time: 6:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>SignIn</title>
</head>
<body>
Login page
<form action="${pageContext.request.contextPath}/views/signIn" method="post">
    <table>
        <tr>
            <td>Username: <input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password: <input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" name="Submit"></td>
        </tr>
    </table>
    <c:choose>
        <c:when test="${userLogin!=null}">
            <c:choose>
                <c:when test="${userLogin}">
                    <p style="color: green;"><b>Login successful!</b></p>
                </c:when>
                <c:otherwise>
                    <p style="color: red"><b>Login fail!</b></p>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:if test="${userWrongPassword}">
        <p style="color: orange"><b>Wrong password!</b></p>
    </c:if>
</form>
<br><a href="main.jsp"><input type="button" value="Return to main page" style="background-color: forestgreen"></a>
<jsp:include page="returnToMain.html"></jsp:include>
</body>
</html>
