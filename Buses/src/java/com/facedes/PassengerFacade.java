package com.facedes;

import com.models.Passenger;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PassengerFacade extends AbstractFacade<Passenger> {

    @PersistenceContext(unitName = "BusesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PassengerFacade() {
        super(Passenger.class);
    }

    public Passenger findPassanger(String firstName, String lastName, Date birthday) {
        return (Passenger) em.createNamedQuery("find-passenger")
                             .setParameter("firstName", firstName)
                             .setParameter("lastName", lastName)
                             .setParameter("birthday", birthday)
                             .getSingleResult();
    }
}
