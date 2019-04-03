package model.subject;

import Observer.Observer;

import java.util.Vector;


/**
 * cette class reprensent un sujet pouvant être suivi par des observateurs
 */
public class Subject {
    private boolean changed = false;

    private Vector<Observer> observers;

    /**
     * default constructeur
     */
    public Subject() {
        observers = new Vector<Observer>();
    }

    /**
     * @param observer souhaitant suivre un sujet
     */
    public void attach(Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
            //quand on ajoute un observateur on le notify une première fois
            observer.update(this);
        }
    }

    /**
     * @param observer ne souhaitant plus suivre le sujet
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * notifi tout les observateur des changements
     */
    public void notifyObservers() {
        Object[] arrLocal;

        synchronized (this) {
            /* evite que les observateur loupe une notifycation lors de la création d'un nouveau membre */
            if (!changed)
                return;
            arrLocal = observers.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((Observer) arrLocal[i]).update(this);
    }

    /**
     * annonce que des changements ou été apporte
     */
    protected void setChanged() {
        this.changed = true;
        notifyObservers();
    }

    /**
     * annonce que les changements ont été traiter
     */
    private void clearChanged() {
        changed = false;
    }

}