package com.booking.controllers;

import com.facedes.SheduleFacade;
import com.facedes.StationFacade;
import com.models.Station;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/shedule")
public class ShowShedule extends HttpServlet{

    @EJB
    private StationFacade stationFacade;
    
    @EJB
    private SheduleFacade sheduleFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long stationId = Long.parseLong(request.getParameter("station_id"));
        Station station = stationFacade.find(stationId);
        request.setAttribute("station", station);
        
        List shedules = sheduleFacade.findSheduleByStation(stationId);
        request.setAttribute("shedules", shedules);
        
        request.getRequestDispatcher("/WEB-INF/shedule.jsp").forward(request, response);
    }

    
}
