package com.booking.controllers;

import com.facedes.BusFacade;
import com.facedes.StationFacade;
import com.models.Bus;
import com.models.Station;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/bus")
public class ShowBus extends HttpServlet{

    @EJB
    BusFacade busFacade;
    
    @EJB
    StationFacade stationFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List listStations = stationFacade.findAll();
        request.setAttribute("listStations", listStations);
        
        long busId = Long.parseLong(request.getParameter("bus_id"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);
        
        request.getRequestDispatcher("/WEB-INF/bus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long stationId = Long.parseLong(request.getParameter("station_id"));
        Station station = stationFacade.find(stationId);
       
        List listStations = stationFacade.findAll();
        request.setAttribute("listStations", listStations);
        
        long busId = Long.parseLong(request.getParameter("bus_id"));
        Bus bus = busFacade.find(busId);
        if (bus.getStations().contains(station)) {
            request.setAttribute("bus", bus);
            request.setAttribute("error", "Такая станция уже существует!");
            request.getRequestDispatcher("/WEB-INF/bus.jsp").forward(request, response);
        }
        bus.getStations().add(station);
        busFacade.edit(bus);
        request.setAttribute("bus", bus);

        request.getRequestDispatcher("/WEB-INF/bus.jsp").forward(request, response);
    }
    
}
