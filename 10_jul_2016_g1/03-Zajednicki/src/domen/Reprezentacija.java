package domen;

import java.io.Serializable;

public class Reprezentacija implements Serializable {

    private int reprezentacijaID;
    private String naziv;

    public Reprezentacija() {
    }

    public Reprezentacija(int reprezentacijaID, String naziv) {
        this.reprezentacijaID = reprezentacijaID;
        this.naziv = naziv;
    }

    public int getReprezentacijaID() {
        return reprezentacijaID;
    }

    public void setReprezentacijaID(int reprezentacijaID) {
        this.reprezentacijaID = reprezentacijaID;
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
