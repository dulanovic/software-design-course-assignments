package domen;

import java.io.Serializable;

public class Fakultet implements Serializable {

    private int fakultetID;
    private String naziv;

    public Fakultet() {
    }

    public Fakultet(int fakultetID, String naziv) {
        this.fakultetID = fakultetID;
        this.naziv = naziv;
    }

    public int getFakultetID() {
        return fakultetID;
    }

    public void setFakultetID(int fakultetID) {
        this.fakultetID = fakultetID;
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
