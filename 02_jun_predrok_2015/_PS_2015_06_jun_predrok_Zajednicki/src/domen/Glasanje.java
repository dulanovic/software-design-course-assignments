package domen;

import java.io.Serializable;

public class Glasanje implements Serializable {

    private int glasanjeID;
    private Drzava odDrzava;
    private Drzava zaDrzava;
    private int brojPoena;

    public Glasanje() {
        this.odDrzava = new Drzava();
        this.zaDrzava = new Drzava();
    }

    public Glasanje(int glasanjeID, Drzava odDrzava, Drzava zaDrzava, int brojPoena) {
        this.glasanjeID = glasanjeID;
        this.odDrzava = odDrzava;
        this.zaDrzava = zaDrzava;
        this.brojPoena = brojPoena;
    }

    public Glasanje(Drzava odDrzava, Drzava zaDrzava, int brojPoena) {
        this.odDrzava = odDrzava;
        this.zaDrzava = zaDrzava;
        this.brojPoena = brojPoena;
    }

    public int getGlasanjeID() {
        return glasanjeID;
    }

    public void setGlasanjeID(int glasanjeID) {
        this.glasanjeID = glasanjeID;
    }

    public Drzava getOdDrzava() {
        return odDrzava;
    }

    public void setOdDrzava(Drzava odDrzava) {
        this.odDrzava = odDrzava;
    }

    public Drzava getZaDrzava() {
        return zaDrzava;
    }

    public void setZaDrzava(Drzava zaDrzava) {
        this.zaDrzava = zaDrzava;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

}
