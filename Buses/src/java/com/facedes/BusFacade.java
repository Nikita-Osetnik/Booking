package com.facedes;

import com.models.Bus;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BusFacade extends AbstractFacade<Bus> {

    @PersistenceContext(unitName = "BusesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BusFacade() {
        super(Bus.class);
    }
    
    public List search(long stationA, long stationB, Date arrivalA, Date arrivalB) {
        return em.createNamedQuery("search")
                 .setParameter("stationA", stationA)
                 .setParameter("stationB", stationB)
                 .setParameter("arrivalA", arrivalA)
                 .setParameter("arrivalB", arrivalB)
                 .getResultList();
    }
}
