package com.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
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
    @NamedQuery(name = "find-shedule-by-bus-id", query = "SELECT s FROM Shedule s WHERE s.bus.id =:id"),
    @NamedQuery(name = "find-arrival-by-bus-and-by-station", query = "SELECT s.arrival FROM Shedule s WHERE s.bus.id =:busId AND s.station.id =:stationId"),
    @NamedQuery(name = "find-shedule-by-station", query = "SELECT s FROM Shedule s WHERE s.station.id =:stationId")
})
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bus_id", "station_id"})})
public class Shedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Bus bus;

    @Column(columnDefinition = "TIME", nullable = false)
    private Date arrival;

    @JoinColumn(nullable = false)
    @OneToOne
    private Station station;

    public Shedule() {
    }

    public Shedule(Bus bus, Date arrival, Station station) {
        this.bus = bus;
        this.arrival = arrival;
        this.station = station;
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

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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
        if (!(object instanceof Shedule)) {
            return false;
        }
        Shedule other = (Shedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.buses.Shedule[ id=" + id + " ]";
    }

}
