package model.state.concreteState;

import model.subject.concreteSubject.Client;
import model.state.State;

import java.awt.*;

/**
 * cette class represente l'etat SILVER
 */
public class SilverState extends State {
    private final static String name = "SILVER";

    public SilverState(Client account) {
        super(account, Color.GRAY);
    }

    SilverState(State oldState) {
        super(oldState, Color.GRAY);
    }

    protected double getMilesCoef() {
        return 0.1;
    }

    protected void stateChangeCheck() {
        if (miles >= 1000)
            client.setState(new GoldState(this));
    }

    @Override
    public String toString() {
        return name;
    }
}
