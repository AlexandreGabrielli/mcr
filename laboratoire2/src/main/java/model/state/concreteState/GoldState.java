package model.state.concreteState;

import model.state.State;

import java.awt.*;

/**
 * cette class represente l'etat gold
 */
class GoldState extends State {
    private final static String name = "GOLD";

    GoldState(State oldState) {
        super(oldState, Color.ORANGE);
    }

    protected double getMilesCoef() {
        return 0.5;
    }

    protected void stateChangeCheck() {
        if (miles < 1000) {
            client.setState(new SilverState(this));
        } else if (miles >= 10000) {
            client.setState(new PlatiniumState(this));
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
