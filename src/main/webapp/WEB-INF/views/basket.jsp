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
        <form action="save" method="post">
            <table>
                <c:if test="${listW.size() == 0}">
                    <p>Ваша корзина пуста</p>
                </c:if>
                <c:forEach var="itemW" items="${listW}" >
                    <tr>
                        <td>
                            <input id="Ware" name="Ware" type="hidden" value="${itemW[0]}">
                            <c:out value="${itemW[1]}"/>
                        </td>
                        <td>
                            <input id="Count" name="Count" value="${itemW[2]}"> шт.
                        </td>
                        <td>
                            <a href="del?id=${itemW[0]}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"><a href="../home">Вернуться к покупкам</a></td>
                    <c:if test="${listW.size() > 0}">
                        <td><input type="submit" value="Оформить заказ"></td>
                    </c:if>
                </tr>
            </table>
        </form>
    </body>
</html>
