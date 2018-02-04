package domen;

import java.io.Serializable;

public class Klijent implements Serializable {

    private int klijentID;
    private String naziv;
    private String adresa;

    public Klijent() {
    }

    public Klijent(int klijentID, String naziv, String adresa) {
        this.klijentID = klijentID;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
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

    @Override
    public boolean equals(Object o) {
        Klijent k = (Klijent) o;
        if (this.getKlijentID() == k.getKlijentID() && this.getNaziv().equals(k.getNaziv()) && this.getAdresa().equals(k.getAdresa())) {
            return true;
        } else {
            return false;
        }
    }

    private double ukupanIznosRacuna;

    public Klijent(String naziv, double ukupanIznosRacuna) {
        this.naziv = naziv;
        this.ukupanIznosRacuna = ukupanIznosRacuna;
    }

    public double getUkupanIznosRacuna() {
        return ukupanIznosRacuna;
    }

    public void setUkupanIznosRacuna(double ukupanIznosRacuna) {
        this.ukupanIznosRacuna = ukupanIznosRacuna;
    }

}
