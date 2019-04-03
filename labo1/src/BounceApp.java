import Fabrique.FabriqueFormeVide;
import Formes.Bouncable;
import Fabrique.FabriqueForme;
import Fabrique.FabriqueFormePlein;
import Singleton.Rebond;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Classe de point d'entrée dans l'application
 */
public class BounceApp {

    private List<Bouncable> bouncers = new ArrayList<>();
    private FabriqueForme fabriquePlein = new FabriqueFormePlein();
    private FabriqueForme fabriqueVide = new FabriqueFormeVide();

    /**
     *
     */
    private BounceApp() {
        Rebond.getInstance().setTitle("Appli de rebonds");
        Rebond.getInstance().addKeyListener(new KeyAdapter() {
            @Override
            public  void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                switch (c) {
                    case 'e':
                        bouncers.clear();
                        break;
                    case 'b':
                        addforme(fabriqueVide);
                        break;
                    case 'f':
                        addforme(fabriquePlein);
                        break;
                    case 'q':
                        System.exit(0);
                }

            }
        });
    }

    private void addforme(FabriqueForme fabriqueForme) {
        int nombreFabriquerParPression = 10;
        for (int i = 0; i < nombreFabriquerParPression; i++) {
            bouncers.add(fabriqueForme.creerCercle());
            bouncers.add(fabriqueForme.creerCarre());
        }

    }


    private synchronized void loop() throws InterruptedException {

        while (true) {

            Rebond.getInstance().repaint();
            try { //n.b: ce try catch est inutile si on Utilise un Timer pour dessiner les forme mais la donner ne nous
                //le permet pas.
                for (Bouncable bouncable : bouncers) {
                    bouncable.draw();
                    bouncable.move();
                }
            } catch (ConcurrentModificationException e) {
                //ce try catch permet de regler des problèmes d'accès concurentiel provoquer par des appels succèsif
                //du for e
                e.printStackTrace();
            } //regle les probleme d'accès lors du clear de beacoup de forme


            Thread.sleep(30);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new BounceApp().loop();
    }
}
