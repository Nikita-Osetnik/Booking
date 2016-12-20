<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="/">Главная</a>
        <h1>Для сотрудников</h1>
        <%@include file="../WEB-INF/fragments/error.jspf" %>
        <%@include file="../WEB-INF/fragments/msg.jspf" %>
        <form action="/add-station" method="post">
            <fieldset>
                <legend style="font-weight: bold">Добавление станции</legend>
                <input name="stationName" placeholder="Название станции" />
                <button type="submit">Добавить</button>
            </fieldset>
        </form>
        <form action="/add-bus" method="post">
            <fieldset>
                <legend style="font-weight: bold">Добавление автобуса</legend>
                <input name="busNumber" placeholder="Номер автобуса" />
                <input type="number" name="seatsNumber" placeholder="Количество мест" />
                <button type= "submit">Добавить</button>
            </fieldset>
        </form>
        <br>
        <a href="/buses">Список автобусов</a>
    </body>
</html>
