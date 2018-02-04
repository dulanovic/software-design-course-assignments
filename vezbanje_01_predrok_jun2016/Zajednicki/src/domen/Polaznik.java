package domen;

import java.io.Serializable;

public class Polaznik implements Serializable {

    private int polaznikID;
    private String ime;
    private String prezime;
    private String jmbg;

    public Polaznik() {
    }

    public Polaznik(int polaznikID, String ime, String prezime, String jmbg) {
        this.polaznikID = polaznikID;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
    }

    public int getPolaznikID() {
        return polaznikID;
    }

    public void setPolaznikID(int polaznikID) {
        this.polaznikID = polaznikID;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
