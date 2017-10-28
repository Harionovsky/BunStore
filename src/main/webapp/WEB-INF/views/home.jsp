<%-- 
    Document   : home
    Created on : 24.10.2017, 15:31:57
    Author     : Harionovsky
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/cookies.png" type="image/png" />
        <title>Интернет-магазин булочек</title>
    </head>
    <body>
        <h2>Интернет-магазин булочек</h2>
        <table border="1">
            <c:forEach var="itemWare" items="${listWare}" >
                <tr>
                    <td>
                        <b><c:out value="${itemWare.getName()}"/></b> <i>на складе много</i><br>
                        <c:out value="${itemWare.getDescription()}"/><br>
                    </td>
                    <td>
                        <button type="button">В корзину</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
