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
        <a href="/clients">Назад</a>
        <h1>Список станций</h1>
        <table>
            <tr>
                <th>Станция</th>
                <th></th>
            </tr>
            <c:forEach items="${listStations}" var="station">
                <tr>
                    <td>
                        <span>${station.name}</span>
                    </td>
                    <td>
                        <c:url value="/shedule" var="showURL">
                            <c:param name="station_id" value="${station.id}"/>
                        </c:url>
                        <a href="<c:out value="${showURL}"/>">Расписание</a>
                    </td>
                </tr>
            </c:forEach> 
        </table>
    </body>
</html>
