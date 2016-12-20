<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="/">Главная</a>
        <a href="/buses">Назад</a>
        <h1>Пассажиры автобуса №${bus.busNumber}</h1>
        <%@include file="../WEB-INF/fragments/error.jspf" %>
        <c:if test="${not empty tickets}">
            <table>
                <tr>
                    <th>Пассажир</th>
                    <th>Дата рождения</th>
                </tr>
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                        <td>
                            <fmt:formatDate value="${ticket.passenger.birthday}" var="ticketBirthday" pattern="yyyy-MM-dd"/>
                            <span>${ticket.passenger.firstName} ${ticket.passenger.lastName}</span>
                        </td>
                        <td align="center">
                            <span>${ticketBirthday}</span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
