<%-- 
    Document   : basket
    Created on : 30.10.2017, 16:04:50
    Author     : Harionovsky
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="../img/cookies.png" type="image/png" />
        <title>Интернет-магазин булочек</title>
    </head>
    <body>
        <h2>Корзина</h2>
        <a href="save?go=home">Вернуться к покупкам</a>
        <table border="1">
            <c:forEach var="itemW" items="${listW}" >
                <tr>
                    <td>
                        <c:out value="${itemW[1]}"/>
                    </td>
                    <td>
                        <input value="${itemW[2]}"> шт.
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="save?go=home/order">Оформить заказ</a>
    </body>
</html>
