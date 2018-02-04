package domen;

import java.io.Serializable;
import java.util.Date;

public class Model implements Serializable {

    private int modelID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;

    public Model() {
    }

    public Model(int modelID, String ime, String prezime, Date datumRodjenja) {
        this.modelID = modelID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    private String modelImePrezime;
    private int brojRevija;
    private int ukupnaZarada;
    private int ukupanBrojSati;

    public Model(String modelImePrezime, int brojRevija, int ukupnaZarada, int ukupanBrojSati) {
        this.modelImePrezime = modelImePrezime;
        this.brojRevija = brojRevija;
        this.ukupnaZarada = ukupnaZarada;
        this.ukupanBrojSati = ukupanBrojSati;
    }

    public String getModelImePrezime() {
        return modelImePrezime;
    }

    public void setModelImePrezime(String modelImePrezime) {
        this.modelImePrezime = modelImePrezime;
    }

    public int getBrojRevija() {
        return brojRevija;
    }

    public void setBrojRevija(int brojRevija) {
        this.brojRevija = brojRevija;
    }

    public int getUkupnaZarada() {
        return ukupnaZarada;
    }

    public void setUkupnaZarada(int ukupnaZarada) {
        this.ukupnaZarada = ukupnaZarada;
    }

    public int getUkupanBrojSati() {
        return ukupanBrojSati;
    }

    public void setUkupanBrojSati(int ukupanBrojSati) {
        this.ukupanBrojSati = ukupanBrojSati;
    }

}
