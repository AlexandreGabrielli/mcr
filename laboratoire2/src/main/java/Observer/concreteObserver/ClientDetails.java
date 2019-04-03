package Observer.concreteObserver;

import model.subject.concreteSubject.Client;
import model.subject.Subject;
import Observer.Observer;

import javax.swing.*;
import java.awt.*;


/**
 * cette class représente une Frame qui affiche les informations d'un client spécifique
 */
public class ClientDetails implements Observer {
    private Client client;
    //messages
    private final static String title = "Detail of client #";
    private final static String[] labels = {"Last name : ", "First name : ", "credit : ",
            "Nb miles : ", "Status ", "Last action : "};
    //labels partagés entre tout les clients details
    private final static JLabel lastNameStaticPanel = new JLabel(labels[0]);
    private final static JLabel firstNameStaticPanel = new JLabel(labels[1]);
    private final static JLabel creditStaticPanel = new JLabel(labels[2]);
    private final static JLabel milesStaticPanel = new JLabel(labels[3]);
    private final static JLabel statusStaticPanel = new JLabel(labels[4]);
    private final static JLabel lastActionStaticPanel = new JLabel(labels[5]);

    //lables propre a chaque clientDetail
    private final JLabel lastNameDynamicPanel = new JLabel();
    private final JLabel firstNameDynamicPanel = new JLabel();
    private final JLabel creditDynamicPanel = new JLabel();
    private final JLabel milesDynamicPanel = new JLabel();
    private final JLabel statusDynamicPanel = new JLabel();
    private final JLabel lastActionDynamicPanel = new JLabel();

    /**
     * constructeur
     *
     * @param client le client dont on désire voir les informations
     */
    public ClientDetails(Client client) {
        super();
        this.client = client;

        //Frame creation
        JFrame clientDetailsFrame = new JFrame(title + client.getId());
        clientDetailsFrame.setSize(300, 250);
        clientDetailsFrame.setLocationRelativeTo(null);
        clientDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel clientDetailPanel = new JPanel(new GridLayout(0, 2));
        //last name panels
        clientDetailPanel.add(lastNameStaticPanel);
        clientDetailPanel.add(lastNameDynamicPanel);
        //first name panels
        clientDetailPanel.add(firstNameStaticPanel);
        clientDetailPanel.add(firstNameDynamicPanel);
        //credit panels
        clientDetailPanel.add(creditStaticPanel);
        clientDetailPanel.add(creditDynamicPanel);
        //miles panels
        clientDetailPanel.add(milesStaticPanel);
        clientDetailPanel.add(milesDynamicPanel);
        //status panels
        clientDetailPanel.add(statusStaticPanel);
        clientDetailPanel.add(statusDynamicPanel);
        //last action panels
        clientDetailPanel.add(lastActionStaticPanel);
        clientDetailPanel.add(lastActionDynamicPanel);

        //attach to frame
        clientDetailsFrame.add(clientDetailPanel);

        //attach client (after Jlabel creation for legacy update)
        client.attach(this);

        clientDetailsFrame.setVisible(true);
    }

    /**
     * update les informations
     *
     * @param subject le client dont les informations vont être modifier
     */
    public void update(Subject subject) {
        this.client = (Client) subject;

        //change panels information
        lastNameDynamicPanel.setText(client.getLastName());
        firstNameDynamicPanel.setText(client.getFirstName());
        creditDynamicPanel.setText(String.valueOf(client.getCredit()));
        milesDynamicPanel.setText(String.valueOf(client.getMiles()));
        statusDynamicPanel.setText(client.getState().toString());
        lastActionDynamicPanel.setText(client.getLastAction());

    }
}