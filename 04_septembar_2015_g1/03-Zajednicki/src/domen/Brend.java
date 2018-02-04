package domen;

import java.io.Serializable;

public class Brend implements Serializable {

    private int brendID;
    private String naziv;

    public Brend() {
    }

    public Brend(int brendID, String naziv) {
        this.brendID = brendID;
        this.naziv = naziv;
    }

    public int getBrendID() {
        return brendID;
    }

    public void setBrendID(int brendID) {
        this.brendID = brendID;
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
