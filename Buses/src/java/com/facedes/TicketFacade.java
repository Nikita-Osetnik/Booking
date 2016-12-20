package com.facedes;

import com.models.Ticket;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TicketFacade extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "BusesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }

    public long calculateTicketsOnBus(long busId) {
        return (long) em.createNamedQuery("calcuate-tickets-on-bus")
                 .setParameter("busId", busId)
                 .getSingleResult();
    }
    
    public List findTicketByBus(long busId) {
        return em.createNamedQuery("find-tickets-by-bus")
                 .setParameter("busId", busId)
                 .getResultList();
    }
}
