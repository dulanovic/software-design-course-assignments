package domen;

import java.io.Serializable;

public class Predmet implements Serializable {

    private int predmetID;
    private String naziv;
    private int trajanjeIspita;

    public Predmet() {
    }

    public Predmet(int predmetID, String naziv, int trajanjeIspita) {
        this.predmetID = predmetID;
        this.naziv = naziv;
        this.trajanjeIspita = trajanjeIspita;
    }

    public int getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(int predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanjeIspita() {
        return trajanjeIspita;
    }

    public void setTrajanjeIspita(int trajanjeIspita) {
        this.trajanjeIspita = trajanjeIspita;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
