package com.booking.controllers;

import com.facedes.BusFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/buses")
public class Buses extends HttpServlet{

    @EJB
    private BusFacade busFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List listBuses = busFacade.findAll();
        request.setAttribute("listBuses", listBuses);
        
        request.getRequestDispatcher("/WEB-INF/buses.jsp").forward(request, response);
    }

    
}
