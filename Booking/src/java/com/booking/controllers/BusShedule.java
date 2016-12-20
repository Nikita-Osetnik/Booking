package com.booking.controllers;

import com.facedes.BusFacade;
import com.facedes.SheduleFacade;
import com.facedes.StationFacade;
import com.models.Bus;
import com.models.Shedule;
import com.models.Station;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/bus-shedule")
public class BusShedule extends HttpServlet {

    @EJB
    private BusFacade busFacade;

    @EJB
    private StationFacade stationFacade;

    @EJB
    private SheduleFacade sheduleFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long busId = Long.parseLong(request.getParameter("bus_id"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);

        if (bus.getStations().isEmpty()) {
            request.setAttribute("error", "В маршруте автобуса нет станций!");
            request.getRequestDispatcher("/WEB-INF/bus-shedule.jsp").forward(request, response);
        }
        List shedules = sheduleFacade.findSheduleByBusId(busId);
        request.setAttribute("shedule", shedules);
        
        request.getRequestDispatcher("/WEB-INF/bus-shedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long busId = Long.parseLong(request.getParameter("bus_id"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);

        long stationId = Long.parseLong(request.getParameter("station_id"));
        Station station = stationFacade.find(stationId);

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        try {
            Date arrival = parser.parse(request.getParameter("arrival"));
            Shedule shedule = new Shedule(bus, arrival, station);
            sheduleFacade.create(shedule);
            request.setAttribute("msg", "Станция добавлена в расписание!");
        } catch (ParseException e) {
            request.setAttribute("error", "Введите время!");
        } catch (EJBException e) {
            request.setAttribute("error", "Такая станция в расписании уже есть!");
        }
        request.setAttribute("shedule", sheduleFacade.findSheduleByBusId(busId));
        
        request.getRequestDispatcher("/WEB-INF/bus-shedule.jsp").forward(request, response);
    }

}
