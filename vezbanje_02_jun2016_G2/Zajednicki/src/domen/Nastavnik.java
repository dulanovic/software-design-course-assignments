package domen;

import java.io.Serializable;

public class Nastavnik implements Serializable {

    private int nastavnikID;
    private String ime;
    private String prezime;
    private String zvanje;

    private int ukupanBrojDezurstava;
    private double ukupanBrojSati;
    private double ukupnoZaIsplatu;

    public Nastavnik() {
    }

    public Nastavnik(int nastavnikID, int ukupanBrojDezurstava, double ukupanBrojSati, double ukupnoZaIsplatu) {
        this.nastavnikID = nastavnikID;
        this.ukupanBrojDezurstava = ukupanBrojDezurstava;
        this.ukupanBrojSati = ukupanBrojSati;
        this.ukupnoZaIsplatu = ukupnoZaIsplatu;
    }

    public Nastavnik(int nastavnikID, String ime, String prezime, String zvanje) {
        this.nastavnikID = nastavnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
    }

    public int getNastavnikID() {
        return nastavnikID;
    }

    public void setNastavnikID(int nastavnikID) {
        this.nastavnikID = nastavnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public int getUkupanBrojDezurstava() {
        return ukupanBrojDezurstava;
    }

    public void setUkupanBrojDezurstava(int ukupanBrojDezurstava) {
        this.ukupanBrojDezurstava = ukupanBrojDezurstava;
    }

    public double getUkupanBrojSati() {
        return ukupanBrojSati;
    }

    public void setUkupanBrojSati(double ukupanBrojSati) {
        this.ukupanBrojSati = ukupanBrojSati;
    }

    public double getUkupnoZaIsplatu() {
        return ukupnoZaIsplatu;
    }

    public void setUkupnoZaIsplatu(double ukupnoZaIsplatu) {
        this.ukupnoZaIsplatu = ukupnoZaIsplatu;
    }

}
