<%-- 
    Document   : order
    Created on : 01.11.2017, 11:48:48
    Author     : Harionovsky
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/cookies.png" type="image/png" />
        <title>Заказы</title>
    </head>
    <body>
        <h2>Заказы</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ФИО</th>
                    <th>Телефон</th>
                    <th>Адрес</th>
                    <th>Товары</th>
                    <th>Действия</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="itemO" items="${listO}" >
                    <tr>
                        <td><c:out value="${itemO[0]}"/></td>
                        <td><c:out value="${itemO[1]}"/></td>
                        <td><c:out value="${itemO[2]}"/></td>
                        <td><c:out value="${itemO[3]}"/></td>
                        <td><c:out value="${itemO[4]}"/></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="ware">Товары</a>&nbsp;<a href="warehouse">Склад</a>
    </body>
</html>
