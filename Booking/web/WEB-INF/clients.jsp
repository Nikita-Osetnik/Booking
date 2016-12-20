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
        <h1>Для клиентов</h1>
        <%@include file="../WEB-INF/fragments/error.jspf" %>
        <form action="/search-result" method="post">
            <fieldset>
                <legend style="font-weight: bold">Поиск автобуса</legend>
            <span>Выберите пункт А и пункт Б</span>
            <br>
            <select name="station_id_A" size="1">
                <c:forEach items="${stations}" var="station">
                    <option value="${station.id}">${station.name}</option>
                </c:forEach>
            </select>
            <select name="station_id_B" size="1">
                <c:forEach items="${stations}" var="station">
                    <option value="${station.id}">${station.name}</option>
                </c:forEach>
            </select>
            <br>
            <span>Введите промежуток времени</span>
            <br>
            <input type="time" name="arrivalA" placeholder="Время прибытия на станцию А"/>
            <input type="time" name="arrivalB" placeholder="Время прибытия на станцию Б"/>
            <button type="submit">Поиск!</button>
            </fieldset>
        </form>
        <br>
        <a href="/shedules">Список станций</a>
    </body>
</html>
