package domen;

import java.io.Serializable;

public class Dizajner implements Serializable {

    private int dizajnerID;
    private String ime;
    private String prezime;

    public Dizajner() {
    }

    public Dizajner(int dizajnerID, String ime, String prezime) {
        this.dizajnerID = dizajnerID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getDizajnerID() {
        return dizajnerID;
    }

    public void setDizajnerID(int dizajnerID) {
        this.dizajnerID = dizajnerID;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
