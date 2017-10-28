<%-- 
    Document   : wareedit
    Created on : 27.10.2017, 18:49:04
    Author     : Harionovsky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/cookies.png" type="image/png" />
        <title>${title}</title>
    </head>
    <body>
        <h2>${title}</h2>
        <form action="${act}" method="post">
            <input id="ID" name="ID" type="hidden" value="${id}">
            <table>
                <tr>
                    <td>Код</td>
                    <td><input id="Code" name="Code" type="text" value="${code}"></td>
                </tr>
                <tr>
                    <td>Название</td>
                    <td><input id="Name" name="Name" type="text" value="${name}"></td>
                </tr>
                <tr>
                    <td>Описание</td>
                    <td><input id="Description" name="Description" type="text" value="${desc}"></td>
                </tr>
                <tr>
                    <td><a href="../ware">Отмена</a></td>
                    <td><input type="submit" value="Сохранить"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
