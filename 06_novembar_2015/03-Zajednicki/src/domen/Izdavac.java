package domen;

import java.io.Serializable;

public class Izdavac implements Serializable {

    private int izdavacID;
    private String naziv;
    private String adresa;

    public Izdavac() {
    }

    public Izdavac(int izdavacID, String naziv, String adresa) {
        this.izdavacID = izdavacID;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public int getIzdavacID() {
        return izdavacID;
    }

    public void setIzdavacID(int izdavacID) {
        this.izdavacID = izdavacID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
