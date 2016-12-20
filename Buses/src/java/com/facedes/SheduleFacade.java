package com.facedes;

import com.models.Shedule;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SheduleFacade extends AbstractFacade<Shedule> {

    @PersistenceContext(unitName = "BusesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SheduleFacade() {
        super(Shedule.class);
    }

    public List findSheduleByBusId(Long id) {
        return em.createNamedQuery("find-shedule-by-bus-id")
                 .setParameter("id", id)
                 .getResultList();
    }
    
    public Date findArrivalByBusAndByStation(long busId, long stationId) {
        return (Date) em.createNamedQuery("find-arrival-by-bus-and-by-station")
                        .setParameter("busId", busId)
                        .setParameter("stationId", stationId)
                        .getSingleResult();
    }
    
    public List findSheduleByStation(long stationId) {
        return em.createNamedQuery("find-shedule-by-station")
                 .setParameter("stationId", stationId)
                 .getResultList();
    }
}
