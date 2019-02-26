package wybierzpanel;

import javax.swing.*;
import java.awt.*;

/**
 *  Glowna klasa dziedziczaca po JFrame
 * @author Mateusz Figon
 */
public class WybierzPanel extends JFrame {

    PanelMenu panelMenu;
    PanelCyfriWyniku panelCyfriWyniku;

    /**
     * Konstruktor klasy WybierzPanel, w ktorym ustawiane sa pozostale panele
     * @param nazwaFrame
     * @param szerokoscFrame
     * @param wysokoscFrame
     */
    public WybierzPanel(String nazwaFrame, int szerokoscFrame, int wysokoscFrame) {
        super(nazwaFrame);
        panelMenu = new PanelMenu(this);
        setLayout(null);
        panelCyfriWyniku = new PanelCyfriWyniku(this);

        panelMenu.setBounds(0,0,1000,60);
        // ustawienie pol w odpowiednich regionach okna
        panelCyfriWyniku.panelCyfr.setBounds(0,100,750,400);
        panelCyfriWyniku.poleGraficzne.setBounds(200,450,1000,150);
        panelCyfriWyniku.panelWyniku.setBounds(0,600,1000,200);
        panelCyfriWyniku.poleTekstowe.setBounds(750,130,290,200);
        add(panelMenu);
        setVisible(true);
        setSize(szerokoscFrame, wysokoscFrame);
        panelMenu.przyciskTrudnosci[0].setBackground(Color.green);
        //dodawanie skladowych Frame'u
        add(panelMenu);
        add(panelCyfriWyniku.poleTekstowe);
        add(panelCyfriWyniku.panelCyfr);
        add(panelCyfriWyniku.poleGraficzne);
        add(panelCyfriWyniku.panelWyniku);
        revalidate();
        repaint();

    }

    public static void main(String[] args) {
        WybierzPanel wybierzPanel = new WybierzPanel("Wybierz Panel", 1050, 800);
    }
}
