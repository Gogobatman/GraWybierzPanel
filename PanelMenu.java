package wybierzpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Klasa dziedziczaca po JPanel, zawierajaca funkcjonalnosc menu
 * @author Mateusz Figon
 */
public class PanelMenu extends JPanel {
    /**
     *  obiekty klasy przycisków trudności
     */
    PrzyciskTrudnosci[] przyciskTrudnosci = new PrzyciskTrudnosci[5];
    /**
     *  obiekt klasy przyciskStartu
     */
    PrzyciskStartu przyciskStartu;
    /**
     *  obiekt klasy przyciskResetu
     */
    PrzyciskResetu przyciskResetu;
    /**
     *  tablica stringow nazw przyciskow
     */
    String[] nazwaPrzyciskuTrudnosci = {"Poziom Latwy", "Poziom Sredni", "Poziom Trudny"};
    /**
     *  tablica nazw plikow graficznych przyciskow menu
     */
    String[] src = {"buttonPoziomLatwy.png", "buttonPoziomSredni.png", "buttonPoziomTrudny.png", "buttonPoziomLatwyPom.png",
            "buttonPoziomSredniPom.png", "buttonPoziomTrudnyPom.png"};


    /**
     * Konstruktor klasy PanelMenu
     * @param wybierzPanel
     */
    public PanelMenu(WybierzPanel wybierzPanel) {
        przyciskStartu = new PrzyciskStartu("Start", wybierzPanel);
        przyciskResetu = new PrzyciskResetu("Reset", wybierzPanel);
        przyciskStartu.setPreferredSize(new Dimension(118, 45));
        przyciskResetu.setPreferredSize(new Dimension(118, 45));
        ImageIcon obrazek;
        obrazek = new ImageIcon(getClass().getResource("buttonStart.png"));
        przyciskStartu.setIcon(obrazek);
        obrazek = new ImageIcon(getClass().getResource("buttonReset.png"));
        przyciskResetu.setIcon(obrazek);
        add(przyciskStartu);
        add(przyciskResetu);
        for (int i = 0; i < 3; i++) {
            przyciskTrudnosci[i] = new PrzyciskTrudnosci(nazwaPrzyciskuTrudnosci[i], wybierzPanel);
            przyciskTrudnosci[i].setPreferredSize(new Dimension(150, 45));
            if (i == 0) {
                obrazek = new ImageIcon(getClass().getResource(src[3]));
            } else {
                obrazek = new ImageIcon(getClass().getResource(src[i]));
            }
            przyciskTrudnosci[i].setIcon(obrazek);
            add(przyciskTrudnosci[i]);
        }

    }
    /**
     *  Klasa przycisku trudnosci
     *  @author Mateusz Figoń
     */
    public class PrzyciskTrudnosci extends JButton {
        String nazwa;

        /**
         * konstuktor klasy PrzyciskTrudnosci
         * @param nazwa
         * @param wybierzPanel
         */
        public PrzyciskTrudnosci(String nazwa, WybierzPanel wybierzPanel) {
            this.nazwa = nazwa;
        }

    }

    /**
     *  Klasa dotyczaca funkcjonalnosci przycisku startujacego rozgrywke
     *  @author Mateusz Figoń
     */
    public class PrzyciskStartu extends JButton {
        String nazwa;

        /**
         * Konstruktor klasy PrzyciskStartu
         * @param nazwa
         * @param wybierzPanel
         */
        public PrzyciskStartu(String nazwa, WybierzPanel wybierzPanel) {
            this.nazwa = nazwa;
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (wybierzPanel.panelCyfriWyniku.rozpoczecieRozgrywki == false) {
                        if (wybierzPanel.panelCyfriWyniku.start == false) {
                            wybierzPanel.panelCyfriWyniku.licznik.start();
                            wybierzPanel.panelCyfriWyniku.start = true;  //odnzaczenie pierwszego wystartowania gry
                        }
                        wybierzPanel.panelCyfriWyniku.rozpoczecieRozgrywki = true;
                        wybierzPanel.panelCyfriWyniku.czas = 0;
                        wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstowePuste();
                        wybierzPanel.panelCyfriWyniku.panelWyniku.odswiezPanelWyniku();
                    } else if (wybierzPanel.panelCyfriWyniku.przegrana == true) {
                        wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstowePrzegrales();
                        wybierzPanel.panelCyfriWyniku.panelWyniku.odswiezPanelWyniku();
                    } else {
                        wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweWcisnietoStart();
                        wybierzPanel.panelCyfriWyniku.panelWyniku.odswiezPanelWyniku();
                    }
                }
            });
        }

    }
    /**
     *  Klasa dotyczaca funkcjonalnosci przycisku resetujacego rozgrywke
     *  @author Mateusz Figoń
     */
    public class PrzyciskResetu extends JButton {
        /**
         * Konsruktor klasy PrzyciskResetu
         * @param nazwa
         * @param wybierzPanel
         */
        public PrzyciskResetu(String nazwa, WybierzPanel wybierzPanel) {
            super(nazwa);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    wybierzPanel.panelCyfriWyniku.przegrana = false;
                    wybierzPanel.panelCyfriWyniku.poziomLatwy = true;
                    wybierzPanel.panelCyfriWyniku.poziomSredni = false;
                    wybierzPanel.panelCyfriWyniku.poziomTrudny = false;
                    wybierzPanel.panelCyfriWyniku.panelCyfr.wyczyscPanelCyfr();
                    wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweWcisnijStart();
                    wybierzPanel.panelCyfriWyniku.panelCyfr.dodajPanelCyfr();
                    wybierzPanel.panelCyfriWyniku.panelCyfr.repaint();
                    wybierzPanel.panelCyfriWyniku.szansaNaPoprawe = 0;
                    ImageIcon obrazek;
                    obrazek = new ImageIcon(getClass().getResource(src[3]));
                    przyciskTrudnosci[0].setIcon(obrazek);
                    obrazek = new ImageIcon(getClass().getResource(src[1]));
                    przyciskTrudnosci[1].setIcon(obrazek);
                    obrazek = new ImageIcon(getClass().getResource(src[2]));
                    przyciskTrudnosci[2].setIcon(obrazek);
                    wybierzPanel.panelCyfriWyniku.czas = 0;
                    wybierzPanel.panelCyfriWyniku.poleGraficzne.rysujCzas(wybierzPanel.panelCyfriWyniku.licznik);
                    wybierzPanel.panelCyfriWyniku.punktacja = 0;
                    wybierzPanel.panelCyfriWyniku.panelWyniku.poKliknieciu();
                    wybierzPanel.panelCyfriWyniku.rozpoczecieRozgrywki = false;

                }
            });
        }

    }
}
