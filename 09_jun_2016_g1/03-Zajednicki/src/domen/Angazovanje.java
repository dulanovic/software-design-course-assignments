package domen;

import java.io.Serializable;
import java.util.Date;

public class Angazovanje implements Serializable {

    private int angazovanjeID;
    private Inzenjer inzenjer;
    private Projekat projekat;
    private Date datumAngazovanja;

    public Angazovanje() {
        this.inzenjer = new Inzenjer();
        this.projekat = new Projekat();
    }

    public Angazovanje(int angazovanjeID, Inzenjer inzenjer, Projekat projekat, Date datumAngazovanja) {
        this.angazovanjeID = angazovanjeID;
        this.inzenjer = inzenjer;
        this.projekat = projekat;
        this.datumAngazovanja = datumAngazovanja;
    }

    public int getAngazovanjeID() {
        return angazovanjeID;
    }

    public void setAngazovanjeID(int angazovanjeID) {
        this.angazovanjeID = angazovanjeID;
    }

    public Inzenjer getInzenjer() {
        return inzenjer;
    }

    public void setInzenjer(Inzenjer inzenjer) {
        this.inzenjer = inzenjer;
    }

    public Projekat getProjekat() {
        return projekat;
    }

    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
    }

    public Date getDatumAngazovanja() {
        return datumAngazovanja;
    }

    public void setDatumAngazovanja(Date datumAngazovanja) {
        this.datumAngazovanja = datumAngazovanja;
    }

}
