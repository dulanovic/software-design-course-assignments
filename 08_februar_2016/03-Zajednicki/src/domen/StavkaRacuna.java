package domen;

import java.io.Serializable;

public class StavkaRacuna implements Serializable {

    private int rb;
    private int kolicina;
    private Proizvod proizvod;
    private double iznos;

    public StavkaRacuna() {
        this.proizvod = new Proizvod();
    }

    public StavkaRacuna(int rb, int kolicina, Proizvod proizvod, double iznos) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.proizvod = proizvod;
        this.iznos = iznos;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

}
