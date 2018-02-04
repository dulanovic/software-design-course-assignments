package domen;

import java.io.Serializable;

public class Nastavnik implements Serializable {

    private int nastavnikID;
    private String ime;
    private String prezime;
    private String zvanje;

    public Nastavnik() {
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

    private String imePrezime;
    private int ukupanBrojDezurstava;
    private int ukupanBrojSati;
    private int ukupnoZaIsplatu;

    public Nastavnik(String imePrezime, int ukupanBrojDezurstava, int ukupanBrojSati, int ukupnoZaIsplatu) {
        this.imePrezime = imePrezime;
        this.ukupanBrojDezurstava = ukupanBrojDezurstava;
        this.ukupanBrojSati = ukupanBrojSati;
        this.ukupnoZaIsplatu = ukupnoZaIsplatu;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public int getUkupanBrojDezurstava() {
        return ukupanBrojDezurstava;
    }

    public void setUkupanBrojDezurstava(int ukupanBrojDezurstava) {
        this.ukupanBrojDezurstava = ukupanBrojDezurstava;
    }

    public int getUkupanBrojSati() {
        return ukupanBrojSati;
    }

    public void setUkupanBrojSati(int ukupanBrojSati) {
        this.ukupanBrojSati = ukupanBrojSati;
    }

    public int getUkupnoZaIsplatu() {
        return ukupnoZaIsplatu;
    }

    public void setUkupnoZaIsplatu(int ukupnoZaIsplatu) {
        this.ukupnoZaIsplatu = ukupnoZaIsplatu;
    }

}
