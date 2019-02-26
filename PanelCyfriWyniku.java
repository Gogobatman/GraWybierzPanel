package wybierzpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 *  Klasa dziedziczaca po JPanel, zawierajaca funkcjonalnosc calej gry poza obsługa menu
 * @author Mateusz Figon
 */
public class PanelCyfriWyniku extends JPanel {
    /** Utworzony obiekt PolaGraficznego  */
    PoleGraficzne poleGraficzne = new PoleGraficzne();
    /** Utworzony obiekty PolaTekstowego */
    PoleTekstowe poleTekstowe = new PoleTekstowe();
    /** Utworzony obiekt licznika*/
    Licznik licznik;
    /** Utworzony obiekt klasy Random  */
    Random rand = new Random();
    /** Zestaw 1 kolejnosci cyfr do poziomu najtrudniejszego */
    int[] zestaw1 = {3, 1, 0, 2, 6, 4, 5, 8, 7, 10, 11, 12, 15, 14, 13, 9};
    /** Zestaw 2 kolejnosci cyfr do poziomu najtrudniejszego */
    int[] zestaw2 = {13, 14, 12, 6, 4, 3, 7, 1, 0, 11, 9, 10, 15, 8, 5, 2};
    /** Zestaw 3 kolejnosci cyfr do poziomu najtrudniejszego */
    int[] zestaw3 = {7, 2, 5, 8, 12, 11, 13, 0, 3, 1, 10, 9, 15, 14, 6, 4};
    /** tablica stringow nazw plikow obrazkow przyciskow  */
    String srcObrazka[]= {"button1.png","button2.png","button3.png","button4.png","button5.png","button6.png","button7.png","button8.png",
            "button9.png","button10.png","button11.png","button12.png","button13.png","button14.png","button15.png",
            "button16.png","button2.png","button3.png","button4.png","button5.png","button6.png","button7.png","button8.png",
            "button9.png","button10.png","button11.png","button12.png","button13.png","button14.png","button15.png"};
    /** tablica stringow src obrazkow bedacym dymkiem tekstowym do podpowiedzi */
    String napisPolaTekstowego[] = {"ObrazekZaMalaLiczba.png","ObrazekOK.png","Obrazek1.png","ObrazekZaDuzaLiczba.png"};
    /** liczba reprezentujaca ktoras z kolei szanse na poprawe po pomylce  */
    int szansaNaPoprawe = 0;
    /** src paska 10 segmentowego na poziomie latwiejszym */
    String srcPaska10[]={"pasek0na10.png","pasek1na10.png","pasek2na10.png","pasek3na10.png","pasek4na10.png","pasek5na10.png",
            "pasek6na10.png","pasek7na10.png","pasek8na10.png","pasek9na10.png","pasek10na10.png"};
    /** src paska 8 segmentowego na poziomie trudniejszym */
    String srcPaska8[]={"pasek0na8.png","pasek1na8.png","pasek2na8.png","pasek3na8.png","pasek4na8.png","pasek5na8.png",
            "pasek6na8.png","pasek7na8.png","pasek8na8.png"};
    /**
     *  cyfra losowana podczas rozgrywki : a
     */
    int a = rand.nextInt(11) + 1;
    /**
     *  cyfra losowana podczas rozgrywki : c
     */
    int c = rand.nextInt(11) + a + 2;
    /**
     *  string przekonwertowany z intow cyfry a
     */
    String aString = Integer.toString(a);
    /**
     *  string przekonwertowany z intow cyfry  c
     */
    String cString = Integer.toString(c);
    /**
     *  zadeklarowanie  zmiennej mnoznika
     */
    int mnoznik = 1;
    /**
     *  zadeklarowanie  numeru opracji
     */
    int numerOperacji = 0;
    /**
     *  zadeklarowanie aktualnej punktacja zdobyta przez uzytkownika
     */
    int punktacja = 0;
    /**
     *  znak obowiazujacej w danej turze operacji
     */
    String[] operacja = {"+", "-", "*", "/"};
    /**
     * tablica obiektow klasy PrzyciskRownania z dolnej czesci okna gry
     */
    PrzyciskRownania[] przyciskRownania = new PrzyciskRownania[6];
    /**
     * zmienna typu boolean mowiaca czy wystartowano pierwszy raz
     */
    boolean start = false;
    /**
     * zmienna typu boolean mowiaca czy rozgrywka sie rozpoczela
     */
    boolean rozpoczecieRozgrywki = false;
    /**
     * zmienna typu boolean mowiaca czy uzytkowik przegral
     */
    boolean przegrana = false;
    /**
     * obiekt klasy PanelCyfr
     */
    PanelCyfr panelCyfr;
    /**
     * obiekt klasy PanelWyniku
     */
    PanelWyniku panelWyniku;
    /**
     * zmienna typu boolean okreslajaca czy poziom latwy juz byl , ciagle obowiazuje czy tez wcale nie wystepowal
     */
    boolean poziomLatwy = true;
    /**
     * zmienna typu boolean okreslajaca czy poziom sredni juz byl , ciagle obowiazuje czy tez wcale nie wystepowal
     */
    boolean poziomSredni = false;
    /**
     * zmienna typu boolean okreslajaca czy poziom trudny juz byl , ciagle obowiazuje czy tez wcale nie wystepowal
     */
    boolean poziomTrudny = false;
    /**
     * czas rozgrywki odliczany przez zegar
     */
    int czas = 0;

    /**
     * Konstruktor klasy PanelCyfriWyniku
     * @param wybierzPanel
     */
    public PanelCyfriWyniku(WybierzPanel wybierzPanel) {

        licznik = new Licznik(new TimerTask() {
            @Override
            public void run() {
                if(czas>1 && przegrana==false)
                {
                    poleTekstowe.odswiezPoleTekstowePuste();  //ustawienie pustego dymku podpowiedzi
                }
                else if(czas>1 && przegrana==false)
                {
                    poleTekstowe.odswiezPoleTekstowePrzegrales();
                }
                if(rozpoczecieRozgrywki==true && przegrana==false)
                {
                    czas++;
                    poleGraficzne.rysujCzas(licznik);
                }
                if (poziomLatwy == true && poziomSredni == false) {
                    jesliCzas(10);
                }
                if (poziomLatwy == true && poziomSredni == true) {
                    jesliCzas(7);
                }

            }
        });
        panelCyfr = new PanelCyfr(wybierzPanel);
        panelWyniku = new PanelWyniku();
    }

    /**
     * funkcja ktora wykonuje sie jesli uzytkownik przkroczy czas
     * @param czasWarunkowy
     */
    public void jesliCzas(int czasWarunkowy) {
        if (czas > czasWarunkowy) {
            przegrana = true;   //ustawienie przegranej gdy warunek jest spelniony
            poleTekstowe.odswiezPoleTekstowePrzegrales();
        }
    }

    /**
     *  Klasa dotyczaca funkcjonalnosci srodkowej czesci planszy
     *    @author Mateusz Figon
     */
    public class PanelCyfr extends JPanel {

        Przycisk[] przycisk = new Przycisk[20];

        /**
         * Konstruktor klasy PanelCyfr
         * @param wybierzPanel
         */
        public PanelCyfr(WybierzPanel wybierzPanel) {
            for (int i = 0; i < 20; i++) {
                przycisk[i] = new Przycisk(i, wybierzPanel);
            }
            dodajPanelCyfr();

        }

        /**
         *  funkcja dodajaca przyciski do panelu cyfr w zaleznosci od poziomu obowiazujacego
         */
        public void dodajPanelCyfr() {

            if (poziomLatwy == true && poziomSredni == false && poziomTrudny == false) {
                for (int i = 0; i < 12; i++) {
                    przycisk[i].setPreferredSize(new Dimension(150, 80));  //ustaw wymiary przyciskow
                    add(przycisk[i]); //dodaj przyciski
                }

            } else if (poziomLatwy == true && poziomSredni == true && poziomTrudny == false) {
                for (int i = 0; i < 16; i++) {
                    przycisk[i].setPreferredSize(new Dimension(150, 80));
                    add(przycisk[i]);
                }
            } else if (poziomLatwy == true && poziomSredni == true && poziomTrudny == true) {

                int a = rand.nextInt(6);
                if (a % 3 == 0) {
                    for (int i = 0; i < 16; i++) {
                        przycisk[i].setPreferredSize(new Dimension(150, 80));
                        add(przycisk[zestaw1[i]]);
                    }
                } else if (a % 3 == 1) {
                    for (int i = 0; i < 16; i++) {
                        przycisk[i].setPreferredSize(new Dimension(150, 80));
                        add(przycisk[zestaw2[i]]);
                    }
                } else if (a % 3 == 2) {
                    for (int i = 0; i < 16; i++) {
                        przycisk[i].setPreferredSize(new Dimension(150, 80));
                        add(przycisk[zestaw3[i]]);
                    }
                }
            }
        }

        /**
         * funkcja usuwajaca przyciski z panelu cyfr
         */
        public void wyczyscPanelCyfr() {
            for (int i = 0; i < 16; i++) {
                remove(przycisk[i]); //usuwanie przyciskow
            }
        }

        /**
         * funkcja odswiezajaca panel cyfr
         */
        public void odswiezPanelCyfr() {
            wyczyscPanelCyfr();
            revalidate();
            repaint();
        }
    }

    /**
     *  Klasa dotyczaca funkcjonalnosci dolnej czesci okna gry dziedziczaca po JPanel
     *  @author Mateusz Figon
     */
    public class PanelWyniku extends JPanel {
        /**
         * konstruktor klasy PanelWyniku
         */
        public PanelWyniku() {
            zadeklarujPrzyciskiWyniku();
            wypelnijPanelWyniku();
        }

        /**
         * funkcja usuwajaca przyciski z dolnego panelu wyniku
         */
        public void wyczyscPanelWyniku() {
            for (int i = 0; i < 6; i++) {
                remove(przyciskRownania[i]);
            }
        }

        /**
         * funkcja opisujaca ciag ktory nastepuje po kliknieciu na przycisk z liczba
         */
        public void poKliknieciu() {
            if (przegrana == true) {

            } else if (rozpoczecieRozgrywki == true) {
                numerOperacji++; //zwieksz numer operacji
                czas=0;  //zeruj czas
                panelCyfr.wyczyscPanelCyfr();
                panelCyfr.dodajPanelCyfr();
                if (numerOperacji % 4 == 0) {
                    a = rand.nextInt(11) + 1;  //generuj losowa liczbe a
                    c = rand.nextInt(11) + a + 2; //generuj losowa liczbe c
                    konwertujIntNaString(a, c);
                } else if (numerOperacji % 4 == 1) {
                    a = rand.nextInt(11) + 1;
                    c = rand.nextInt(11) + a + 2;
                    konwertujIntNaString(c, a);
                } else if (numerOperacji % 4 == 2) {
                    a = rand.nextInt(12) + 1;
                    mnoznik = rand.nextInt(12) + 1;
                    c = a * mnoznik;
                    konwertujIntNaString(a, c);
                } else if (numerOperacji % 4 == 3) {
                    a = rand.nextInt(12) + 1;
                    mnoznik = rand.nextInt(12) + 1;
                    c = a * mnoznik;
                    konwertujIntNaString(c, a);
                }
                odswiezPanelWyniku();
                poleGraficzne.rysujCzas(licznik);  //narysuj na nowo pasek odliczajacy czas
            } else if (rozpoczecieRozgrywki == false) {
                odswiezPanelWyniku();
            }

        }
        /**
         * funkcja  odswiezenie panelu wyniku
         */
        public void odswiezPanelWyniku() {
            wyczyscPanelWyniku();
            zadeklarujPrzyciskiWyniku();
            wypelnijPanelWyniku();
            revalidate();
            repaint();
        }

        /**
         * funkcja deklarujaca przyciski w dolnym panelu wyniku
         */
        public void zadeklarujPrzyciskiWyniku() {
            przyciskRownania[0] = new PrzyciskRownania(aString);
            przyciskRownania[1] = new PrzyciskRownania(operacja[numerOperacji % 4]);
            przyciskRownania[2] = new PrzyciskRownania("X");
            przyciskRownania[3] = new PrzyciskRownania("=");
            przyciskRownania[4] = new PrzyciskRownania(cString);
            przyciskRownania[5] = new PrzyciskRownania("Wynik to: " + punktacja);
        }

        /**
         * funkcja wypelniajaca panel wyniku przyciskami
         */
        public void wypelnijPanelWyniku() {
            for (int i = 0; i < 6; i++) {
                przyciskRownania[i].setPreferredSize(new Dimension(150, 80));
                if(i==5)
                {
                    przyciskRownania[i].setPreferredSize(new Dimension(180, 120));
                    przyciskRownania[i].setBackground(new Color(255, 132, 9)); //ustaw kolor tla przycisku
                }

                add(przyciskRownania[i]);
            }
        }

        /**
         * funkcja konwertujaca losowane zmienne typu int na String
         * @param a
         * @param c
         */
        public void konwertujIntNaString(int a, int c) {
            aString = Integer.toString(a);  //konwertuj int na String
            cString = Integer.toString(c);
        }
    }

    /**
     * Klasa przycisku z dana liczba na ktory klika sie aby rozwiazac rownanie, dziedziczaca po JButton
     *   @author Mateusz Figon
     */
    public class Przycisk extends JButton {

        public Przycisk(int numerPorzadkowy, WybierzPanel wybierzPanel) {
           // super(Integer.toString(numerPorzadkowy + 1));
            ImageIcon obrazek = new ImageIcon(getClass().getResource(srcObrazka[numerPorzadkowy]));
            setIcon(obrazek);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rozpoczecieRozgrywki == true && przegrana == false && szansaNaPoprawe < 3) { //sprawdzanie czy uzytkownik ma szanse na rozgrywke
                        if (numerOperacji % 4 == 0 || numerOperacji % 4 == 1) {
                            if (a + numerPorzadkowy + 1 == c) {
                                jesliWarunek('=', wybierzPanel);
                            } else if (a + numerPorzadkowy + 1 > c) {
                                jesliWarunek('>', wybierzPanel);
                            } else if (a + numerPorzadkowy + 1 < c) {
                                jesliWarunek('<', wybierzPanel);
                            }
                        } else if (numerOperacji % 4 == 2 || numerOperacji % 4 == 3) {
                            if (a * (numerPorzadkowy + 1) == c) {
                                jesliWarunek('=', wybierzPanel);
                            } else if (a * (numerPorzadkowy + 1) > c) {
                                jesliWarunek('>', wybierzPanel);
                            } else if (a * (numerPorzadkowy + 1) < c) {
                                jesliWarunek('<', wybierzPanel);
                            }
                        }

                    } else if (przegrana == true || szansaNaPoprawe>=3) {
                        przegrana = true;
                        wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstowePrzegrales();
                        panelWyniku.odswiezPanelWyniku();
                    } else {
                        wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweWcisnijStart();
                        panelWyniku.odswiezPanelWyniku();
                    }
                }
            });
            if (numerPorzadkowy % 2 == 1) {
                setBackground(new Color(0, 191, 255));
            } else {
                setBackground(new Color(204, 255, 255));
            }
        }

        /**
         * funkcja okreslajaca przyznanie punktow lub ich odjecie w przypadku danej odpowiedzi, wywolanie funkcji poKliknieciu i odswiezenie dymku podpowiedzi
         * @param znak
         * @param wybierzPanel
         */
        public void jesliWarunek(char znak, WybierzPanel wybierzPanel) {
            if (znak == '=') {
                punktacja += c;  //przyznanie punktacji za poprawna odpowiedz
                szansaNaPoprawe = 0;
                wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweJesliOk();
                wybierzPanel.panelCyfriWyniku.panelWyniku.poKliknieciu();
                przyznajNowyPoziomTrudnosci(wybierzPanel);
            } else if (znak == '>') {
                szansaNaPoprawe++;  //naliczanie pomylek
                punktacja -= (c * szansaNaPoprawe);  //odjecie punktacji za bledna odpowiedz
                wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweJesliZaDuzo();
                czas=0;
                panelWyniku.odswiezPanelWyniku();
            } else if (znak == '<') {
                szansaNaPoprawe++; //naliczanie pomylek
                punktacja -= (c * szansaNaPoprawe);  //odjecie punktacji za bledna odpowiedz
                wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweJesliZaMalo();
                czas=0;
                panelWyniku.odswiezPanelWyniku();
            }
        }
    }

    /**
     * funkcja przyznajaca uzytkownikowi nowy poziom trudnosci jesli ma odpowiednia ilosc punktow
     * @param wybierzPanel
     */
    public void przyznajNowyPoziomTrudnosci(WybierzPanel wybierzPanel) {
        if (wybierzPanel.panelCyfriWyniku.punktacja > 200 && poziomLatwy == true && poziomSredni == false) { //sprawdzanie czy uzytkownik moze przejsc na wyzszy poziom
            poziomSredni = true;
            przyznajNowyPoziomTrudnosciWnetrze(0, 1, 0,4, wybierzPanel);
            wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweWcisnijStart();
            wybierzPanel.panelCyfriWyniku.panelWyniku.odswiezPanelWyniku();
        } else if (wybierzPanel.panelCyfriWyniku.punktacja > 300 && poziomLatwy == true && poziomSredni == true && poziomTrudny == false) {
            poziomTrudny = true;
            wybierzPanel.panelCyfriWyniku.panelCyfr.odswiezPanelCyfr();
            przyznajNowyPoziomTrudnosciWnetrze(1, 2,1,5, wybierzPanel);
            wybierzPanel.panelCyfriWyniku.poleTekstowe.odswiezPoleTekstoweWcisnijStart();
            wybierzPanel.panelCyfriWyniku.panelWyniku.odswiezPanelWyniku();
        }
    }

    /**
     * funkcja znajdujaca sie wewnatrz przyznajNowyPoziomTrudnosci okreslajaca miedzy innymi kolor przycisku trudnosci
     * @param cyfraJeden
     * @param cyfraDwa
     * @param src1
     * @param src2
     * @param wybierzPanel
     */
    public void przyznajNowyPoziomTrudnosciWnetrze(int cyfraJeden, int cyfraDwa, int src1, int src2, WybierzPanel wybierzPanel) {
        wybierzPanel.panelCyfriWyniku.panelCyfr.wyczyscPanelCyfr();
        wybierzPanel.panelCyfriWyniku.panelCyfr.dodajPanelCyfr();
        wybierzPanel.panelCyfriWyniku.rozpoczecieRozgrywki = false;
        czas=0;
        wybierzPanel.panelCyfriWyniku.poleGraficzne.rysujCzas(licznik);
        ImageIcon obrazek;
        obrazek = new ImageIcon(getClass().getResource(wybierzPanel.panelMenu.src[src1]));
        wybierzPanel.panelMenu.przyciskTrudnosci[cyfraJeden].setIcon(obrazek);  //zmiana obrazka w przycisku trudnosci
        obrazek = new ImageIcon(getClass().getResource(wybierzPanel.panelMenu.src[src2]));
        wybierzPanel.panelMenu.przyciskTrudnosci[cyfraDwa].setIcon(obrazek);
        wybierzPanel.panelCyfriWyniku.punktacja = 0;
        wybierzPanel.panelCyfriWyniku.panelWyniku.poKliknieciu();
    }

    /**
     * Klasa przycisku Rownania z dolenj czesci panelu, dziedziczaca po JButton
     *  @author Mateusz Figon
     */
    public class PrzyciskRownania extends JButton {
        /**
         * Konstuktor przycisku rownania z dolnej czesci panelu
         * @param liczbaString
         */
        public PrzyciskRownania(String liczbaString) {
            super(liczbaString);
        }
    }

    /**
     * Klasa umozliwiajaca odliczanie czasu
     *  @author Mateusz Figon
     */
    public class Licznik {

        java.util.Timer timer = new Timer();
        TimerTask zadanie;

        /**
         * Konstuktor klasy Licznik
         * @param task
         */
        public Licznik(TimerTask task) {
            zadanie = task;
        }

        public void start() {
            timer.scheduleAtFixedRate(zadanie, 1000, 1000);
        }
    }

    /**
     * Klasa dziedziczaca po JLabel, ktora okresla funkcjonalnosc paska pozostalego czasu
     * @author Mateusz Figon
     */
    public class PoleGraficzne extends JLabel {
        /**
         * Konstruktor klasy PoleGraficzne
         */
        public PoleGraficzne() {
            ImageIcon obrazek;
            obrazek = new ImageIcon(getClass().getResource("pasek0na10.png"));
            setIcon(obrazek);

        }

        /**
         * funkcja rysujaca pasek odliczania czasu
         * @param licznik
         */
        public void rysujCzas(Licznik licznik){
            ImageIcon obrazek;

            if (poziomLatwy == true && poziomSredni == false) {
                obrazek = new ImageIcon(getClass().getResource(srcPaska10[czas%11]));
            } else   {
                obrazek = new ImageIcon(getClass().getResource(srcPaska8[czas%9]));
            }
            setIcon(obrazek);
        }
    }

    /**
     * Klasa dziedziczaca po JLabel, ktora opisuje dzialanie dymka tekstu ktory opisuje rozgrywke i daje wskazkowki
     * @author Mateusz Figon
     */
    public class PoleTekstowe extends JLabel {
        ImageIcon obrazek;

        /**
         * Konstruktor klasy PoleTekstowe
         */
        public PoleTekstowe() {
            odswiezPoleTekstoweWcisnijStart();

        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstoweJesliZaMalo(){
            obrazek = new ImageIcon(getClass().getResource(napisPolaTekstowego[0]));
            setIcon(obrazek);
        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstoweJesliOk(){
            obrazek = new ImageIcon(getClass().getResource("ObrazekOK.png"));
            setIcon(obrazek);
        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstowePuste(){
            obrazek = new ImageIcon(getClass().getResource("Obrazek1.png"));
            setIcon(obrazek);
        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstoweJesliZaDuzo(){
            obrazek = new ImageIcon(getClass().getResource("ObrazekZaDuzaLiczba.png"));
            setIcon(obrazek);
        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstowePrzegrales(){
            obrazek = new ImageIcon(getClass().getResource("ObrazekPrzegrana.png"));
            setIcon(obrazek);
        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstoweWcisnietoStart(){
            obrazek = new ImageIcon(getClass().getResource("ObrazekWcisnietoStart.png"));
            setIcon(obrazek);
        }

        /**
         * funkcja odswiezajaca dymek informujacy uzytkownika o rozgrywce
         */
        public void odswiezPoleTekstoweWcisnijStart(){
            obrazek = new ImageIcon(getClass().getResource("ObrazekWcisnijStart.png"));
            setIcon(obrazek);
        }
    }

}
