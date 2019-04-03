package model.subject.concreteSubject;

/**
 * cette class represente des vols
 */
public class Flight implements Comparable<Flight> {

    private String name;
    private int miles;
    private double price; //$

    /**
     * constructeur
     *
     * @param name  le nom du vol
     * @param miles les miles du vol
     * @param price le prix de base du vol
     */
    public Flight(String name, int miles, double price) {
        this.name = name;
        this.miles = miles;
        this.price = price;
    }

    /**
     * @return le nom du vol
     */
    public String getName() {
        return name;
    }

    /**
     * @return le nombre de miles du vol
     */
    public int getMiles() {
        return miles;
    }

    /**
     * @return le prix de base du billet
     */
    public double getPrice() {
        return price;
    }

    /**
     * compare les vols selon leurs nom
     *
     * @param b un autre vol
     * @return si le vol est plus petit, plus grand ou egal
     */
    @Override
    public int compareTo(Flight b) {
        return name.compareTo(b.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
