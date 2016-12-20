package com.booking.controllers;

import com.facedes.BusFacade;
import com.facedes.PassengerFacade;
import com.facedes.SheduleFacade;
import com.facedes.TicketFacade;
import com.models.Bus;
import com.models.Passenger;
import com.models.Ticket;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/buy")
public class Buy extends HttpServlet {

    @EJB
    private BusFacade busFacade;

    @EJB
    private PassengerFacade passengerFacade;

    @EJB
    private TicketFacade ticketFacade;
    
    @EJB
    private SheduleFacade sheduleFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long busId = Long.parseLong(request.getParameter("busId"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);

        request.getRequestDispatcher("/WEB-INF/buy.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        long busId = Long.parseLong(request.getParameter("busId"));
        Bus bus = busFacade.find(busId);
        request.setAttribute("bus", bus);
        
        if (ticketFacade.calculateTicketsOnBus(busId) >= bus.getSeatsNumber()) {
            request.setAttribute("error", "Билетов больше нет!");
            request.getRequestDispatcher("/WEB-INF/buy.jsp").forward(request, response);
        }

        long stationId = Long.parseLong(request.getParameter("stationId"));
        Date arrival = sheduleFacade.findArrivalByBusAndByStation(busId, stationId);
        long dif = arrival.getTime() - new Date(System.currentTimeMillis()).getTime();
        if (dif < 600000 && dif > 0) {                                                          // Если время отправления автобус 
            request.setAttribute("error", "Поезд ушел!");                                       // прошло, будем считать, что билет 
            request.getRequestDispatcher("/WEB-INF/buy.jsp").forward(request, response);
        }
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        if (firstName.trim().length() == 0 || lastName.trim().length() == 0) {
            request.setAttribute("error", "Поля Фамилия и Имя не должны быть пустыми!");
            request.getRequestDispatcher("/WEB-INF/buy.jsp").forward(request, response);
        }

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = parser.parse(request.getParameter("birthday"));
            Passenger passenger = new Passenger(firstName, lastName, birthday);
            try {
                passengerFacade.create(passenger);
            } catch (EJBException e) {
                passenger = passengerFacade.findPassanger(firstName, lastName, birthday);
            }

            try {
                Ticket ticket = new Ticket(bus, passenger);
                ticketFacade.create(ticket);
                request.setAttribute("msg", "Билет успешно куплен!");
            } catch (EJBException e) {
                request.setAttribute("error", "Такой билет уже существует!");
            }
        } catch (ParseException | NumberFormatException e) {
            request.setAttribute("error", "Дата введена не корректно!");
        } 

        
        
        request.getRequestDispatcher("/WEB-INF/buy.jsp").forward(request, response);
    }

}
