<%-- 
    Document   : warehouseedit
    Created on : 28.10.2017, 13:46:14
    Author     : Harionovsky
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="../img/cookies.png" type="image/png" />
        <title>${title}</title>
    </head>
    <body>
        <h2>${title}</h2>
        <form action="${act}" method="post">
            <table>
                <tr>
                    <td>Товар</td>
                    <td>
                        <select id="WareID" name="WareID">
                            <c:forEach var="itemWare" items="${listWare}">
                                <option value="${itemWare.getId()}">(${itemWare.getCode()}) ${itemWare.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Количество</td>
                    <td><input id="Quantity" name="Quantity" type="text"></td>
                </tr>
                <tr>
                    <td><a href="../warehouse">Отмена</a></td>
                    <td><input type="submit" value="Сохранить"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
