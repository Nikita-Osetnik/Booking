package com.booking.controllers;

import com.facedes.StationFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/clients"})
public class Clients extends HttpServlet {

    @EJB
    private StationFacade stationFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List stations = stationFacade.findAll();
        request.setAttribute("stations", stations);
        
        request.getRequestDispatcher("/WEB-INF/clients.jsp").forward(request, response);
    }

}
