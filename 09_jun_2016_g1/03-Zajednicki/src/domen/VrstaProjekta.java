package domen;

import java.io.Serializable;

public class VrstaProjekta implements Serializable {

    private int vrstaID;
    private String naziv;

    public VrstaProjekta() {
    }

    public VrstaProjekta(int vrstaID, String naziv) {
        this.vrstaID = vrstaID;
        this.naziv = naziv;
    }

    public int getVrstaID() {
        return vrstaID;
    }

    public void setVrstaID(int vrstaID) {
        this.vrstaID = vrstaID;
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
