package model.subject.concreteSubject;


import model.state.concreteState.SilverState;
import model.state.State;
import model.subject.Subject;

/**
 * Cette class represente un client
 */
public class Client extends Subject implements Comparable<Client> {
    // compteur pour creer l'id
    private static int compteur = 0;

    private int id;
    private String firstName;
    private String lastName;
    private double credit;
    private String lastAction;
    private State state;

    /**
     * @return l'id
     */
    public int getId() {
        return id;
    }

    /**
     * constructeur
     *
     * @param lastName le nom
     * @param surname  le prenom
     */
    public Client(String lastName, String surname) {
        super();
        id = compteur++;
        this.lastName = lastName;
        this.firstName = surname;
        this.setState(new SilverState(this));
        this.lastAction = "account created successfully";
    }

    /**
     * @return first name of client
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return last name of client
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return last action of client
     */
    public String getLastAction() {
        return lastAction;
    }

    /**
     * @return nubers's miles of client
     */
    public double getMiles() {
        return state.getMiles();
    }

    /**
     * changer la dernier action
     *
     * @param lastAction action qu'on desire ajouter
     */
    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
        setChanged();
    }

    /**
     * @return le nombre de credit
     */
    public double getCredit() {
        return credit;
    }

    /**
     * ajoute du credit
     *
     * @param credit a ajouter
     */
    public void addCredit(double credit) {
        this.credit += credit;
        setChanged();
    }

    /**
     * achat d'un billet en $
     *
     * @param price  prix du billet
     * @param flight vols
     */
    public void buyTicketsWithCredit(double price, Flight flight) {
        if (credit < price) {
            lastAction = "not enought credit";
        } else {
            credit -= price;
            state.deposit(flight.getMiles());
            lastAction = "buy " + flight + " with " + price + " credits";
        }
        setChanged();
    }

    /**
     * achat d'un billet en miles
     *
     * @param miles  miles du billet
     * @param flight vol
     */
    public void buyTicketsWithMiles(double miles, Flight flight) {
        if (state.getMiles() < miles) {
            lastAction = "not enought miles";
        } else {
            state.payWithMiles(miles);
            lastAction = "buy " + flight + " with " + miles + " miles";
        }
        setChanged();
    }

    /**
     * change l'etat courrant du client
     *
     * @param state nouvelle etat
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return l'etat actuel du client
     */
    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    /**
     * compare les client en se basant sur sa representation sous forme de String
     *
     * @param b autre client
     * @return si le client est plus petit, plus grand ou egal
     */
    @Override
    public int compareTo(Client b) {
        return toString().compareTo(b.toString());
    }


}