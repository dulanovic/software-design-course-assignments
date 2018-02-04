package domen;

import java.io.Serializable;

public class StavkaRacuna implements Serializable {

    private int rb;
    private Knjiga k;
    private int kolicina;
    private double iznosStavke;

    public StavkaRacuna() {
        this.k = new Knjiga();
    }

    public StavkaRacuna(int rb) {
        this.rb = rb;
    }

    public StavkaRacuna(int rb, Knjiga k, int kolicina, double iznosStavke) {
        this.rb = rb;
        this.k = k;
        this.kolicina = kolicina;
        this.iznosStavke = iznosStavke;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Knjiga getK() {
        return k;
    }

    public void setK(Knjiga k) {
        this.k = k;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

}
