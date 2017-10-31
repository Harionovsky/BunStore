<%-- 
    Document   : orderedit
    Created on : 30.10.2017, 17:23:47
    Author     : Harionovsky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="../img/cookies.png" type="image/png" />
        <title>Интернет-магазин булочек</title>
    </head>
    <body>
        <h2>Оформление заказа</h2>
        <form action="save" method="post">
            <input id="ID" name="ID" type="hidden" value="${id}">
            <table>
                <tr>
                    <td>ФИО</td>
                    <td><input id="FIO" name="FIO" type="text" value="${fio}"></td>
                </tr>
                <tr>
                    <td>Телефон</td>
                    <td><input id="Phone" name="Phone" type="text" value="${phone}"></td>
                </tr>
                <tr>
                    <td>Адрес</td>
                    <td><textarea id="Address" name="Address">${address}</textarea>
                </tr>
                <tr>
                    <td><a href="../home/basket">Отмена</a></td>
                    <td><input type="submit" value="Готово"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
