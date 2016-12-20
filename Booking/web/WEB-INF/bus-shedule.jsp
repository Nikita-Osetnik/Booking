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
        <h1>Расписание автобуса №${bus.busNumber}</h1>
        <%@include file="../WEB-INF/fragments/error.jspf" %>
        <%@include file="../WEB-INF/fragments/msg.jspf" %>
        <c:if test="${not empty bus.stations}">
            <form action="/bus-shedule" method="post">
                <fieldset>
                    <legend style="font-weight: bold">Добавление станции и времени в расписание автобуса</legend>
                    <select name="station_id" size="1">
                        <c:forEach items="${bus.stations}" var="station">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="bus_id" value="${bus.id}"/>
                    <input type="time" name="arrival" placeholder="Время прибытия"/>
                    <button type="submit">OK!</button>
                </fieldset>
            </form>
        </c:if>
        <c:if test="${not empty shedule}">
            <table>
                <tr>
                    <th>Станция</th>
                    <th>Время прибытия</th>
                </tr>
                <c:forEach items="${shedule}" var="shedule">
                    <tr>
                        <td>
                            <span>${shedule.station.name}</span>
                        </td>
                        <td align="center">
                            <fmt:formatDate value="${shedule.arrival}" var="sheduleDate" pattern="HH:mm"/>
                            <span>${sheduleDate}</span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
