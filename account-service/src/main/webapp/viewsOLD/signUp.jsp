<%--
  Created by IntelliJ IDEA.
  User: Inotak
  Date: 2/16/2022
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/views/signUp" method="post">
    <table>
        <tr>
            <td>Username: <input type="text" name="username"></td>
            <c:if test="${usernameEmpty!=null}">
                <c:if test="${usernameEmpty}">
                    <td><p style="color: red"><b>Field is empty!</b></p></td>
                </c:if>
            </c:if>
        </tr>
        <tr>
            <td>Password: <input type="password" name="password"></td>
            <c:if test="${passwordEmpty!=null}">
                <c:if test="${passwordEmpty}">
                    <td><p style="color: red"><b>Field is empty!</b></p></td>
                </c:if>
            </c:if>
        </tr>
        <tr>
            <td>Full name: <input type="text" name="fio"></td>
            <td> <c:if test="${fioEmpty!=null}">
                <c:if test="${fioEmpty}">
                    <td><p style="color: red"><b>Field is empty!</b></p></td>
                </c:if>
            </c:if>
            <td>Birthday: <input type="date" name="birthday"></td>
            <c:if test="${birthdayEmpty!=null}">
                <c:if test="${birthdayEmpty}">
                    <td><p style="color: red"><b>Field is empty!</b></p></td>
                </c:if>
            </c:if>
        </tr>
        <tr>
            <td><input type="submit" name="Submit"></td>
        </tr>
    </table>
    <c:if test="${userExists!=null}">
        <c:if test="${userExists}">
            <p style="color: orangered"><b>User is already
                exists!</b></p>
        </c:if>
    </c:if>
    <c:if test="${userCreated!=null}">
        <c:if test="${userCreated}">
            <p style="color: green"><b>User created!</b></p>
        </c:if>
    </c:if>
</form>
<br><a href="main.jsp"><input type="button" value="Return to main page" style="background-color: forestgreen"></a>
<jsp:include page="returnToMain.html"></jsp:include>
</body>
</html>
