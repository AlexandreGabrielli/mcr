package model.state.concreteState;

import model.state.State;

import java.awt.*;

/**
 * cette class represente l'etat platinium
 */
class PlatiniumState extends State {
    private boolean permanent;
    private final static String name = "PLATINIUM";

    PlatiniumState(State oldState) {
        super(oldState, Color.CYAN);
    }

    protected double getMilesCoef() {
        return 0.5;
    }

    protected void stateChangeCheck() {
        if (!permanent) {
            if (miles < 10000) {
                client.setState(new GoldState(this));
            } else if (miles >= 100000) {
                this.permanent = true;
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
