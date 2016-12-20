package com.booking.controllers;

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

@WebServlet(urlPatterns = "/shedules")
public class Shedules extends HttpServlet{

    @EJB
    private StationFacade stationFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List listStations = stationFacade.findAll();
        request.setAttribute("listStations", listStations);
        
        request.getRequestDispatcher("/WEB-INF/shedules.jsp").forward(request, response);
    }

    
}
