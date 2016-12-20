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
        <a href="/clients">Поиск</a>
        <h1>Расписание автобуса №${bus.busNumber}</h1>
        <table>
            <tr>
                <th>Станция</th>
                <th>Время прибытия</th>
            </tr>
            <c:forEach items="${shedules}" var="shedule">
                <tr align="center">
                    <td>
                        <span>${shedule.station.name}</span>
                    </td>
                    <td>
                        <fmt:formatDate value="${shedule.arrival}" var="sheduleDate" pattern="HH:mm"/>
                        <span>${sheduleDate}</span>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:url value="/buy" var="buyURL">
            <c:param name="busId" value="${bus.id}"/>
        </c:url>
        <a href="<c:out value="${buyURL}"/>">Купить билет</a>
    </body>
</html>
