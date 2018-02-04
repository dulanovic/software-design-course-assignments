package domen;

import java.io.Serializable;

public class Inzenjer implements Serializable {

    private int inzenjerID;
    private String ime;
    private String prezime;
    private String jmbg;

    public Inzenjer() {
    }

    public Inzenjer(int inzenjerID, String ime, String prezime, String jmbg) {
        this.inzenjerID = inzenjerID;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
    }

    public int getInzenjerID() {
        return inzenjerID;
    }

    public void setInzenjerID(int inzenjerID) {
        this.inzenjerID = inzenjerID;
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
