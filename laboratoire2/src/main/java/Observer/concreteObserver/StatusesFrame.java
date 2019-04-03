package Observer.concreteObserver;

import model.subject.concreteSubject.Client;
import model.subject.concreteSubject.ClientList;
import model.state.State;
import model.subject.Subject;
import Observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * cette class représente une Frame qui affiche tout les status de nos clients
 */
public class StatusesFrame implements Observer {
    private final static String title = "Statuses";
    private ClientList clientList;
    private JFrame statusesFrame;

    /**
     * default constructor
     */
    StatusesFrame() {
        //recupere l'instance de clientList
        this.clientList = ClientList.getInstance();

        //Frame creation
        statusesFrame = new JFrame(title);
        statusesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statusesFrame.setSize(300, 250);
        statusesFrame.setLocationRelativeTo(null);

        //attach to all client from the list
        clientList.attach(this);
        for (Client client : clientList.getState()) {
            client.attach(this);
        }
        statusesFrame.setVisible(true);
    }


    @Override
    public void update(Subject subject) {
        //on ne connait pas a priori le nombre de Jpanel, on est obliger de les créer "a la volée"
        JPanel tmpPannel = new JPanel(new GridLayout(0, 1));

        //recreer les labels pour tout les clients
        for (Client client : clientList.getState()) {
            State state = client.getState();
            JLabel jLabel = new JLabel(client + " " + state);
            jLabel.setForeground(state.getColor());
            tmpPannel.add(jLabel);
        }
        //efface les anciennes info
        statusesFrame.getContentPane().removeAll();
        statusesFrame.getContentPane().invalidate();

        //update avec les nouvelles infos
        statusesFrame.getContentPane().add(tmpPannel);
        statusesFrame.getContentPane().revalidate();
    }
}