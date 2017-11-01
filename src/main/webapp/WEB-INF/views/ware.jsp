<%-- 
    Document   : ware
    Created on : 24.10.2017, 15:29:55
    Author     : Harionovsky
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/cookies.png" type="image/png" />
        <title>Товары</title>
    </head>
    <body>
        <h2>Товары</h2>
        <a href="ware/add">Добавить</a>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Артикул</th>
                    <th>Наименование</th>
                    <th>Описание</th>
                    <th>Действия</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="itemWare" items="${listWare}" >
                    <tr>
                        <td><c:out value="${itemWare.getId()}"/></td>
                        <td><c:out value="${itemWare.getCode()}"/></td>
                        <td><c:out value="${itemWare.getName()}"/></td>
                        <td><c:out value="${itemWare.getDescription()}"/></td>
                        <td>
                            <a href="ware/edit?id=${itemWare.getId()}">Изменить</a>
                            <a href="ware/delete?id=${itemWare.getId()}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="warehouse">Склад</a>&nbsp;<a href="order">Заказы</a>
    </body>
</html>
