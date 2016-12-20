package com.booking.controllers;

import com.facedes.BusFacade;
import com.facedes.TicketFacade;
import com.models.Bus;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/bus-passengers")
public class BusPassengers extends HttpServlet{

    @EJB
    private BusFacade busFacade;
    
    @EJB
    private TicketFacade ticketFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long busId = Long.parseLong(request.getParameter("busId"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);
        
        List tickets = ticketFacade.findTicketByBus(busId);
        if (tickets.isEmpty()) {
            request.setAttribute("error", "Еще никто не купил билеты на этот автобус!");
            request.getRequestDispatcher("/WEB-INF/bus-passengers.jsp").forward(request, response);
        }
        request.setAttribute("tickets", tickets);
        
        request.getRequestDispatcher("/WEB-INF/bus-passengers.jsp").forward(request, response);
    }
    
}
