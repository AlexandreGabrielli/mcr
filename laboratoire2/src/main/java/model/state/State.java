package model.state;

import model.subject.concreteSubject.Client;

import java.awt.*;

/**
 * cette class represente l'état de nos client, elle gére aussi ses miles
 */
public abstract class State {
    protected Client client;
    protected double miles;
    private Color color;

    /**
     * lier un compte a un etat
     *
     * @param account le client
     * @param color   la couleur
     */
    protected State(Client account, Color color) {
        this.client = account;
        this.color = color;
    }

    /**
     * promotion d'etat
     *
     * @param oldState l'ancienne état
     * @param color    la nouvelle couleur associer
     */
    protected State(State oldState, Color color) {
        this.client = oldState.client;
        this.miles = oldState.miles;
        this.color = color;

    }

    /**
     * @return le nombre de miles
     */
    public double getMiles() {
        return miles;
    }

    /**
     * depose des miles
     *
     * @param miles a deposer
     */
    public void deposit(double miles) {
        this.miles += miles;
        stateChangeCheck();
    }

    /**
     * retrait de miles
     *
     * @param miles a retirer
     */
    public void payWithMiles(double miles) {
        this.miles -= miles * getMilesCoef();
        stateChangeCheck();
    }

    /**
     * @return le coeficient multiplicateur de miles lier a l'etat
     */
    protected abstract double getMilesCoef();

    /**
     * check si l'état doit etre modifier et le change
     */
    protected abstract void stateChangeCheck();

    /**
     * @return la couleur
     */
    public Color getColor() {
        return color;
    }
}