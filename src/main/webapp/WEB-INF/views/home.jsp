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
            <c:forEach var="itemW" items="${listW}" >
                <tr>
                    <td>
                        <b><c:out value="${itemW[0]}"/></b>
                        <c:set var="available" value="${itemW[2]}"/>                        
                        <c:choose>
                            <c:when test="${available == 2}">
                                <i><font color="green">на складе много</font></i>
                            </c:when>
                            <c:when test="${available == 1}">
                                <i><font color="orange">на складе мало</font></i>
                            </c:when>
                            <c:otherwise>
                                <i><font color="red">нет на складе</font></i>
                            </c:otherwise>
                        </c:choose>
                        <br>
                        <c:out value="${itemW[1]}"/>
                    </td>
                    <td>
                        <button type="button">В корзину</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
