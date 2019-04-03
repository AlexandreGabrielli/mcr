package Observer.concreteObserver;

import model.subject.*;
import Observer.Observer;
import model.subject.concreteSubject.Client;
import model.subject.concreteSubject.ClientList;
import model.subject.concreteSubject.Flight;
import model.subject.concreteSubject.FlightList;
import model.ticketsType.TicketsType;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * cette class répresente une frame depuis laquel on peu manager des clients
 */
public class ClientManager implements Observer {
    private final static String title = "Clients manager";

    /**
     * cette class peremt de lier un type de tickets a un prix et de l'afficher correctement dans un combobox
     */
    public class ComboBoxPair {
        private TicketsType ticketsType;
        private double ticketsPrice;

        /**
         * constructeur
         *
         * @param ticketsType  type de ticket
         * @param ticketsPrice prix du ticket
         */
        ComboBoxPair(TicketsType ticketsType, double ticketsPrice) {
            this.ticketsType = ticketsType;
            this.ticketsPrice = ticketsPrice;
        }

        /**
         * @return le type de ticket
         */
        public TicketsType getTicketsType() {
            return ticketsType;
        }

        /**
         * @return le prix du ticket par rapport au type
         */
        double getTicketsPrice() {
            return ticketsType.getPriceCoef() * ticketsPrice;
        }

        /**
         * @return le prix du ticket en miles par rapport au prix
         */
        double getTicketPriceMiles() {
            return ticketsType.getMilesCoef() * ticketsPrice;
        }

        @Override
        public String toString() {
            return ticketsType + " " + ticketsType.getPriceCoef() * ticketsPrice + " $";
        }
    }

    //swing component have to be used in different method
    private JComboBox<Comparable<Client>> comboBoxClients = new JComboBox<>();
    private JComboBox<Comparable<Flight>> comboBoxFlihts = new JComboBox<>();
    private JComboBox<ComboBoxPair> comboBoxClass = new JComboBox<>();

    /**
     * Default constructor
     */
    public ClientManager() {
        //le clientmanager s'attache au deux sigletons clientlist et flightlist
        ClientList.getInstance().attach(this);
        FlightList.getInstance().attach(this);

        //dimension max pour certain component
        Dimension maxSize = new Dimension(150, 40);
        comboBoxClients.setMaximumSize(maxSize);
        comboBoxFlihts.setMaximumSize(maxSize);
        comboBoxClass.setMaximumSize(maxSize);
        JTextField jTextFieldCredit = new JTextField();
        jTextFieldCredit.setMaximumSize(maxSize);

        //creation de la frame
        JFrame frame = new JFrame(title);
        frame.setSize(500, 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //client line
        JPanel b1 = new JPanel();
        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        b1.add(new JLabel("Clients :"));
        b1.add(comboBoxClients);
        JButton detailButton = new JButton("Details");
        b1.add(detailButton);
        detailButton.addActionListener(e -> new ClientDetails((Client)
                Objects.requireNonNull(comboBoxClients.getSelectedItem())));


        //credit line
        JPanel b2 = new JPanel();
        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        b2.add(new JLabel("Credits :"));
        b2.add(jTextFieldCredit);
        JButton addCredit = new JButton("Add");
        b2.add(addCredit);
        addCredit.addActionListener(e ->
                ((Client) Objects.requireNonNull(comboBoxClients.getSelectedItem())).addCredit(
                        Double.parseDouble(jTextFieldCredit.getText())));

        //Flight line
        JPanel b3 = new JPanel();
        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        b3.add((new JLabel("Flight :")));
        b3.add(comboBoxFlihts);
        b3.add(comboBoxClass);
        JButton bookCashButton = new JButton("Book (cash)");
        b3.add(bookCashButton);
        JButton bookMilesButton = new JButton("Book (miles)");
        b3.add(bookMilesButton);
        comboBoxFlihts.addItemListener(e -> updateComboboxClass());
        bookCashButton.addActionListener(e -> ((Client) Objects.requireNonNull(
                comboBoxClients.getSelectedItem())).buyTicketsWithCredit(
                ((ComboBoxPair) Objects.requireNonNull(comboBoxClass.getSelectedItem())).getTicketsPrice(),
                (Flight) comboBoxFlihts.getSelectedItem()));
        bookMilesButton.addActionListener(e ->
                ((Client) Objects.requireNonNull(
                        comboBoxClients.getSelectedItem())).buyTicketsWithMiles(
                        ((ComboBoxPair) Objects.requireNonNull(comboBoxClass.getSelectedItem())).getTicketPriceMiles(),
                        (Flight) comboBoxFlihts.getSelectedItem()));


        //bouton line
        JPanel b4 = new JPanel();
        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JButton statuseButton = new JButton("Statuses");
        statuseButton.addActionListener(e -> new StatusesFrame());
        b4.add(statuseButton);
        JButton quitButton = new JButton("quit");
        quitButton.addActionListener(e -> System.exit(0));
        b4.add(quitButton);


        JPanel b5 = new JPanel();
        //On positionne maintenant ces lignes en colonne
        b5.setLayout(new BoxLayout(b5, BoxLayout.PAGE_AXIS));
        b5.add(b1);
        b5.add(b2);
        b5.add(b3);
        b5.add(b4);

        frame.getContentPane().add(b5);
        frame.setVisible(true);
    }

    //what to do when clientList update
    private void update(ClientList clients) {
        comboBoxClients.removeAllItems();
        for (Client client : clients.getState()) {
            comboBoxClients.addItem(client);
        }
    }

    //what to do when FlightList update
    private void update(FlightList flights) {
        comboBoxFlihts.removeAllItems();
        for (Flight flight : flights.getState()) {
            comboBoxFlihts.addItem(flight);
        }
        updateComboboxClass();
    }

    //call specific update method
    @Override
    public void update(Subject subject) {
        if (subject instanceof ClientList) {
            update((ClientList) subject);
        } else if (subject instanceof FlightList) {
            update((FlightList) subject);
        }
    }

    //update comboboxClass when flight change
    private void updateComboboxClass() {
        comboBoxClass.removeAllItems();
        for (TicketsType type : TicketsType.values()) {
            Flight currentFlight = (Flight) comboBoxFlihts.getSelectedItem();
            double currentFlightPrice =
                    (Objects.requireNonNull(currentFlight)).getPrice();
            ComboBoxPair comboBoxPair = new ComboBoxPair(type, currentFlightPrice);
            comboBoxClass.addItem(comboBoxPair);
        }
    }
}