package com.booking.controllers;

import com.facedes.BusFacade;
import com.facedes.SheduleFacade;
import com.models.Bus;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/bus-client")
public class BusClient extends HttpServlet{

    @EJB
    private SheduleFacade sheduleFacade;
    
    @EJB
    private BusFacade busFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long busId = Long.parseLong(request.getParameter("busId"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);
        
        List shedules = sheduleFacade.findSheduleByBusId(busId);
        request.setAttribute("shedules", shedules);
        
        request.getRequestDispatcher("/WEB-INF/bus-client.jsp").forward(request, response);
    }

    
}
