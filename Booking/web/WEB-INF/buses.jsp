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
        <a href="/employers">Назад</a>
        <h1>Список автобусов</h1>
        <table>
            <tr >
                <th>Автобус</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${listBuses}" var="bus">
                <tr align = "center">
                    <td>
                        <span>№${bus.busNumber}</span>
                    </td>
                    <td>
                        <c:url value="/bus" var="busURL">
                            <c:param name="bus_id" value="${bus.id}"/>
                        </c:url>
                        <a href="<c:out value="${busURL}"/>">Маршрут</a>
                    </td>
                    <td>
                        <c:url value="/bus-shedule" var="sheduleURL">
                            <c:param name="bus_id" value="${bus.id}"/>
                        </c:url>
                        <a href="<c:out value="${sheduleURL}"/>">Расписание</a>
                    </td>
                    <td>
                        <c:url value="/bus-passengers" var="passengersURL">
                            <c:param name="busId" value="${bus.id}"/>
                        </c:url>
                        <a href="<c:out value="${passengersURL}"/>">Пассажиры</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
