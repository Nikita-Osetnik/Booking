package com.booking.controllers;

import com.facedes.BusFacade;
import com.facedes.StationFacade;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/search-result")
public class SearchResult extends HttpServlet {

    @EJB
    private StationFacade stationFacade;

    @EJB
    private BusFacade busFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long stationIdA = Long.parseLong(request.getParameter("station_id_A"));
        long stationIdB = Long.parseLong(request.getParameter("station_id_B"));
        if (stationIdA == stationIdB) {
            request.setAttribute("error", "Станции не должны совпадать!");
            List stations = stationFacade.findAll();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("/WEB-INF/clients.jsp").forward(request, response);
        }
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        try {
            Date arrivalA = parser.parse(request.getParameter("arrivalA"));
            Date arrivalB = parser.parse(request.getParameter("arrivalB"));
            List buses = busFacade.search(stationIdA, stationIdB, arrivalA, arrivalB);
            if (buses.isEmpty()) {
                request.setAttribute("error", "Совпадений нет!");
                request.getRequestDispatcher("/WEB-INF/search-result.jsp").forward(request, response);
            }
            request.setAttribute("buses", buses);
        } catch (ParseException e) {
            request.setAttribute("error", "Время не может быть пустым!");
            List stations = stationFacade.findAll();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("/WEB-INF/clients.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/search-result.jsp").forward(request, response);
    }

}
