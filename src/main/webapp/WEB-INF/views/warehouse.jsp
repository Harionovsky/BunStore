<%-- 
    Document   : warehouse
    Created on : 24.10.2017, 15:31:30
    Author     : Harionovsky
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/cookies.png" type="image/png" />
        <title>Склад</title>
    </head>
    <body>
        <h2>Склад</h2>
        <a href="warehouse/inc">Поставка</a>&nbsp;<a href="warehouse/dec">Списание</a>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Товар</th>
                    <th>Остаток</th>
                    <th>Резерв</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="itemWH" items="${listWH}" >
                    <tr>
                        <td align="center"><c:out value="${itemWH[0]}"/></td>
                        <td><c:out value="${itemWH[1]}"/></td>
                        <td align="right"><c:out value="${itemWH[2]}"/></td>
                        <td align="right"><c:out value="${itemWH[3]}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="ware">Товары</a>&nbsp;<a href="order">Заказы</a>
    </body>
</html>
