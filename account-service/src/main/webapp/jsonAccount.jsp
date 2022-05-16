<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%><%@ page import="by.itacademy.accountservice.model.entity.AccountEntity"%><%@ page import="com.fasterxml.jackson.datatype.jsr310.JavaTimeModule"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="by.itacademy.accountservice.model.entity.AccountEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
ObjectMapper mapper = new ObjectMapper();
    AccountEntity accountEntity = (AccountEntity) request.getAttribute("accountEntity");
    mapper.registerModule(new JavaTimeModule());
    String string = mapper.writeValueAsString(accountEntity);
    PrintWriter writer = response.getWriter();
    writer.write(string);
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Информация о счёте</title>
</head>
<body>
<c:choose>
    <c:when test="${accountEntity!=null}">
        <p style="color:red;"><%=string%></p>
    </c:when>
    <c:otherwise>
        <p>Ничего не вышло!</p>
    </c:otherwise>
</c:choose>
<form action="${pageContext.request.contextPath}/accountEntity" method=""
</body>
</html>
