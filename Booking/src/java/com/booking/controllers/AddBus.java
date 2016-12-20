package com.booking.controllers;

import com.facedes.BusFacade;
import com.models.Bus;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/add-bus")
public class AddBus extends HttpServlet {

    @EJB
    private BusFacade busFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busNumber = request.getParameter("busNumber");
        if (busNumber.trim().length() == 0) {
            request.setAttribute("error", "Автобус должен иметь название!");
            request.getRequestDispatcher("/WEB-INF/employers.jsp").forward(request, response);
        }
        try {
            int seatsNumber = Integer.parseInt(request.getParameter("seatsNumber"));
            if (seatsNumber < 1) {
                request.setAttribute("error", "Количество мест должно быть положительным!");
                request.getRequestDispatcher("/WEB-INF/employers.jsp").forward(request, response);
            }
            Bus bus = new Bus(busNumber, seatsNumber);
            busFacade.create(bus);
            request.setAttribute("msg", "Автобус добавлен!");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Количество мест не может быть пустым!");
        } catch (EJBException e) {
            request.setAttribute("error", "Такой автобус уже есть!");
        }

        request.getRequestDispatcher("/WEB-INF/employers.jsp").forward(request, response);
    }

}
