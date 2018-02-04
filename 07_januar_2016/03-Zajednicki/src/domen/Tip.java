package domen;

import java.io.Serializable;

public class Tip implements Serializable {

    private int tipID;
    private String naziv;

    public Tip() {
    }

    public Tip(int tipID, String naziv) {
        this.tipID = tipID;
        this.naziv = naziv;
    }

    public int getTipID() {
        return tipID;
    }

    public void setTipID(int tipID) {
        this.tipID = tipID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
