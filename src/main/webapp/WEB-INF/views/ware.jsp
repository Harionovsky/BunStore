<%-- 
    Document   : ware
    Created on : 24.10.2017, 15:29:55
    Author     : Harionovsky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Товары</title>
    </head>
    <body>
        <h2>Товары</h2>
        <input type="button" value="Добавить" />
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
                <tr>
                    <td>${dd}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
