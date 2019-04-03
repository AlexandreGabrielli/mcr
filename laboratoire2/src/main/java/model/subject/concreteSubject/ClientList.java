package model.subject.concreteSubject;


import model.subject.Subject;

import java.util.Set;
import java.util.TreeSet;

/**
 * cette class represente une unique liste de client trier par ordre l'aphabétique
 */
public class ClientList extends Subject {
    private static ClientList instance;
    private Set<Client> clients;


    /**
     * Default constructor
     */
    private ClientList() {
        clients = new TreeSet<>();
    }


    /**
     * @return la liste de client
     */
    public Set<Client> getState() {
        return clients;
    }


    /**
     * ajoute un client a la list
     *
     * @param client a ajouter a la liste
     */
    public void addClient(Client client) {
        clients.add(client);
        //annonce qu'on a effectuer des chagements qui doivent être notifier
        setChanged();
    }

    /**
     * @return l'instance de la liste de client, la créer sinon
     */
    public static ClientList getInstance() {
        if (instance == null) {
            instance = new ClientList();
        }
        return instance;
    }


}