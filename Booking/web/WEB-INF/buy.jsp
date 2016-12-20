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
        <h1>Купить билет на автобус №${bus.busNumber}</h1>
        <%@include file="../WEB-INF/fragments/error.jspf" %>
        <%@include file="../WEB-INF/fragments/msg.jspf" %>
        <form action="/buy" method="post">
            <p>
                <span>Выберите станцию отправления</span>
                <select name="stationId" size="1">
                    <c:forEach items="${bus.stations}" var="station">
                        <option value="${station.id}">${station.name}</option>
                    </c:forEach>
                </select>
            </p>
            <span>Введите имя, фамилию и дату рождения</span>
            <p>
                <input name="lastName" placeholder="Фамилия"/>
                <input name="firstName" placeholder="Имя"/>
            </p>
            <input type="date" name="birthday" placeholder="Дата рождения"/>
            <input type="hidden" name="busId" value="${bus.id}"/>
            <button type="submit">Купить!</button>
        </form>
    </body>
</html>
