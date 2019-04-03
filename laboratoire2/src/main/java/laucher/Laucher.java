package laucher;

import Observer.concreteObserver.ClientManager;
import model.subject.concreteSubject.Client;
import model.subject.concreteSubject.ClientList;
import model.subject.concreteSubject.Flight;
import model.subject.concreteSubject.FlightList;

import java.util.Timer;
import java.util.TimerTask;

public class Laucher extends TimerTask {
    private ClientList clientList;
    private FlightList flightList;
    private static int i;

    public Laucher() {
        this.clientList = ClientList.getInstance();
        this.flightList = FlightList.getInstance();
    }

    public void addClient(Client client) {
        clientList.addClient(client);
    }

    public void addFlight(Flight flight) {
        flightList.addFlight(flight);
    }

    @Override
    public void run() {
        System.out.println(i);
        clientList.addClient(new Client("new", "client" + i++));
    }


    public static void main(String... args) {
        Laucher laucher = new Laucher();
        laucher.addClient(new Client("Justin", "Case"));
        laucher.addClient(new Client("Emerald", "Stone"));
        laucher.addClient(new Client("Alex", "Baldouine"));
        laucher.addFlight(new Flight("A23b", 345, 1000));
        laucher.addFlight(new Flight("3416b", 200, 20.23));


        ClientManager clientmanager = new ClientManager();

        // Lancement du Timer
        Timer timer = new Timer();
        TimerTask task = new Laucher();

        timer.schedule(task, 2000, 5000);


    }


}
