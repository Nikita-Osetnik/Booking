package com.booking.controllers;

import com.facedes.StationFacade;
import com.models.Station;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/add-station")
public class AddStation extends HttpServlet{

    @EJB
    private StationFacade stationFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String stationName = request.getParameter("stationName");
        if (stationName.trim().length() == 0) {
            request.setAttribute("error", "Станция должна иметь название!");
            request.getRequestDispatcher("/WEB-INF/employers.jsp").forward(request, response);
        }
        Station station = new Station(stationName);
        try {
            stationFacade.create(station);
            request.setAttribute("msg", "Станция добавлена!");
        } catch (EJBException e) {
            request.setAttribute("error", "Такая станция уже есть!");
        }
        
        request.getRequestDispatcher("/WEB-INF/employers.jsp").forward(request, response);
    }

    
}
