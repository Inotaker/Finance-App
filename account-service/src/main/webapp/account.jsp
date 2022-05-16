<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Главная страница входа</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${requestScope.create}">
            <p style="color:red;">Добавили...вроде</p>
        </c:when>
        <c:otherwise>
            <p>Ничего не вышло!</p>
        </c:otherwise>
    </c:choose>
        <p>Заходи быстрее!</p>
        <form action="${pageContext.request.contextPath}/accountEntity" method="POST">
            <table>
                <tbody>
                <tr>
                    <td>Title:</td>
                    <td>
                        <input type="text" name="title">
                    </td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td>
                        <input type="text" name="description">
                    </td>
                </tr>
                <tr>
                    <td>Type:</td>
                    <td>
                        <input type="text" name="type">
                    </td>
                </tr>
                <tr>
                    <td>Currency:</td>
                    <td>
                        <input type="text" name="currency">
<%--                        <input type="radio" id="currencyChoice1"--%>
<%--                               name="currency" value="BYN">--%>
<%--                        <label for="currencyChoice1">BYN</label>--%>

<%--                        <input type="radio" id="currencyChoice2"--%>
<%--                               name="currency" value="EUR">--%>
<%--                        <label for="currencyChoice2">EUR</label>--%>

<%--                        <input type="radio" id="currencyChoice3"--%>
<%--                               name="currency" value="USD">--%>
<%--                        <label for="currencyChoice3">USD</label>--%>
                    </td>
                </tr>
                </tbody>
            </table>
            <p><input type="submit" value="Sign Up" /></p>
        </form>
    </body>
</html>