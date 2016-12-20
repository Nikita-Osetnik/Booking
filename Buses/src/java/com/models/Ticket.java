package com.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@NamedQueries({
    @NamedQuery(name = "calcuate-tickets-on-bus", query = "SELECT COUNT(t) FROM Ticket t WHERE t.bus.id =:busId"),
    @NamedQuery(name = "find-tickets-by-bus", query = "SELECT t FROM Ticket t WHERE t.bus.id =:busId")
})
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bus_id", "passenger_id"})})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Bus bus;

    @OneToOne
    @JoinColumn(nullable = false)
    private Passenger passenger;

    public Ticket() {
    }

    public Ticket(Bus bus, Passenger passenger) {
        this.bus = bus;
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buses.Ticket[ id=" + id + " ]";
    }

}
