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
    <title>Message</title>
</head>
<body>
Message page
<br>
<form action="${pageContext.request.contextPath}/views/message" method="post">
    Message: <input type="text" name="message">
    Recipient : <input type="text" name="recipient">
    <input type="submit" value="Submit">
</form>
<c:if test="${messageDeploy!=null}">
    <c:choose>
        <c:when test="${messageDeploy}">
            <p style="color: green">Message deployed!</p>
        </c:when>
        <c:otherwise>
            <p style="color: red">Recipient not found!</p>
        </c:otherwise>
    </c:choose>
</c:if>
<br><a href="main.jsp"><input type="button" value="Return to main page" style="background-color: forestgreen"></a>
<jsp:include page="returnToMain.html"></jsp:include>
</body>
</html>
