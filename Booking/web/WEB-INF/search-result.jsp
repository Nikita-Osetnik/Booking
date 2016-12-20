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
        <h1>Результаты поиска</h1>
        <%@include file="../WEB-INF/fragments/error.jspf" %>
        <c:forEach items="${buses}" var="bus">
            <span>Автобус №${bus.busNumber}:</span>
            <c:url value="/bus-client" var="busClientURL">
                <c:param name="busId" value="${bus.id}"/>
            </c:url>
            <a href="<c:out value="${busClientURL}"/>">Расписание</a>
            <br>
        </c:forEach>
    </body>
</html>
