package domen;

import java.io.Serializable;

public class Komponenta implements Serializable {

    private int komponentaID;
    private String naziv;
    private double cena;
    private String tip;

    private int brojUgradjenihKomada;
    private double ukupanIznos;

    public Komponenta() {
    }

    public Komponenta(String naziv, String tip, int brojUgradjenihKomada, double ukupanIznos) {
        this.naziv = naziv;
        this.tip = tip;
        this.brojUgradjenihKomada = brojUgradjenihKomada;
        this.ukupanIznos = ukupanIznos;
    }

    public Komponenta(int komponentaID, String naziv, double cena, String tip) {
        this.komponentaID = komponentaID;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
    }

    public int getKomponentaID() {
        return komponentaID;
    }

    public void setKomponentaID(int komponentaID) {
        this.komponentaID = komponentaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public int getBrojUgradjenihKomada() {
        return brojUgradjenihKomada;
    }

    public void setBrojUgradjenihKomada(int brojUgradjenihKomada) {
        this.brojUgradjenihKomada = brojUgradjenihKomada;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

}
