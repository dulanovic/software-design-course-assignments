package domen;

import java.io.Serializable;

public class StavkaKonfiguracije implements Serializable {

    private Konfiguracija konfiguracija;
    private int rb;
    private int kolicina;
    private double iznos;
    private Komponenta komponenta;

    public StavkaKonfiguracije() {
        this.konfiguracija = new Konfiguracija();
        this.komponenta = new Komponenta();
    }

    public StavkaKonfiguracije(Konfiguracija konfiguracija, int rb, int kolicina, double iznos, Komponenta komponenta) {
        this.konfiguracija = konfiguracija;
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.komponenta = komponenta;
    }

    public Konfiguracija getKonfiguracija() {
        return konfiguracija;
    }

    public void setKonfiguracija(Konfiguracija konfiguracija) {
        this.konfiguracija = konfiguracija;
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
