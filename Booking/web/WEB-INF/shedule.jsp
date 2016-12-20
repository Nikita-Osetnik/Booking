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
        <a href="/shedules">Назад</a>
        <h1>Расписание станции ${station.name}</h1>
        <table>
            <tr>
                <th>Автобус</th>
                <th>Время прибытия</th>
            </tr>
            <c:forEach items="${shedules}" var="shedule">
                <tr>
                    <td align="center">
                        <span>№${shedule.bus.busNumber}</span>
                    </td>
                    <td align="center">
                        <fmt:formatDate value="${shedule.arrival}" var="sheduleDate" pattern="HH:mm"/>
                        <span>${sheduleDate}</span>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
