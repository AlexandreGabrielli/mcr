package model;

import model.subject.concreteSubject.Client;
import model.subject.Subject;
import Observer.Observer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ces tests permettent de tester la class client et surtout notre modèle observateur
 */
class ClientTest {

    //intern observeur test
    class ObserveurTest implements Observer {

        public String clientFirstName;
        public String clientLastName;
        public String clientLastAction;

        public void update(Subject observable) {
            this.clientFirstName = ((Client) observable).getFirstName();
            this.clientLastAction = ((Client) observable).getLastAction();
            this.clientLastName = ((Client) observable).getLastName();
        }
    }

    @Test
    void setLastAction() {
        Client client = new Client("Crystal", "Clearwaters");
        client.setLastAction("action test");
        assertEquals(client.getLastAction(), "action test");
    }


    @Test
    void notifyObserver() {
        Client client = new Client("Crystal", "Clearwaters");
        ObserveurTest observer = new ObserveurTest();

        client.attach(observer);
        //test si l'observeur a bien été notifier a la création de l'observation
        assertEquals(client.getFirstName(), observer.clientFirstName);
        assertEquals(client.getLastName(), observer.clientLastName);
        assertEquals(client.getLastAction(), observer.clientLastAction);

        client.setLastAction("action test");
        client.notifyObservers();
        //test si l'observeur a bien été notifer des modifications
        assertEquals(observer.clientLastAction, observer.clientLastAction);
    }

    @Test
    void detach() {
        Client client = new Client("Crystal", "Clearwaters");
        ObserveurTest observer = new ObserveurTest();
        client.attach(observer);
        client.detach(observer);
        client.setLastAction("action test");
        client.notifyObservers();
        //l'observateur ne doit pas être notifier des changements après avoir été détaché

        assertEquals("account created successfully", observer.clientLastAction);
    }


    @Test
    void request() {
        Client client = new Client("Crystal", "Clearwaters");

    }

    @Test
    void toStringTest() {
        Client client = new Client("Crystal", "Clearwaters");
        assertEquals(client.toString(), "Crystal Clearwaters");
    }

}