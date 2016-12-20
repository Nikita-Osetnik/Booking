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
        <h1>Маршрут автобуса №${bus.busNumber}</h1>
        <form action="/bus" method="post">
            <select name="station_id" size="5">
                <c:forEach items="${listStations}" var="station">
                    <option value="${station.id}">${station.name}</option>
                </c:forEach>
            </select>
            <input name="bus_id" value="${bus.id}" type="hidden"/>
            <button type="submit">Добавить</button>
        </form>
        <span style="color: red">${error}</span>
        <br>
        <c:if test="${not empty bus.stations}">
              <select name="busStations" size="5">
                  <c:forEach items="${bus.stations}" var="station">
                      <option disabled value="${station.id}">${station.name}</option>
                  </c:forEach>
              </select>
        </c:if>
    </body>
</html>
