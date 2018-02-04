package domen;

import java.io.Serializable;

public class StavkaKonfiguracije implements Serializable {

    private int rb;
    private int kolicina;
    private double iznos;
    private Komponenta komponenta;

    public StavkaKonfiguracije() {
        this.komponenta = new Komponenta();
    }

    public StavkaKonfiguracije(int rb, int kolicina, double iznos, Komponenta komponenta) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.komponenta = komponenta;
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

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Komponenta getKomponenta() {
        return komponenta;
    }

    public void setKomponenta(Komponenta komponenta) {
        this.komponenta = komponenta;
    }

}
