package model.subject.concreteSubject;

import model.subject.Subject;

import java.util.Set;
import java.util.TreeSet;


/**
 * cette class reprensente une unique liste de vols trier par ordre alphabétique
 */
public class FlightList extends Subject {
    private Set<Flight> flights;
    private static FlightList instance;


    /**
     * Default constructor
     */
    public FlightList() {
        flights = new TreeSet<>();
    }


    /**
     * retourne la liste de vol
     */
    public Set<Flight> getState() {
        return flights;
    }


    /**
     * ajoute un vol a la list
     *
     * @param flight vol a ajouter a la list
     */
    public void addFlight(Flight flight) {
        flights.add(flight);
        setChanged();
    }

    /**
     * @return l'instance de la liste de vol, la créer sinon.
     */
    public static FlightList getInstance() {
        if (instance == null) {
            instance = new FlightList();
        }
        return instance;
    }


}