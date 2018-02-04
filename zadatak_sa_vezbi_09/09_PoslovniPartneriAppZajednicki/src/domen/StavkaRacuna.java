/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author student1
 */
public class StavkaRacuna implements Serializable {
    private int rb;
    private int kolicina;
    private double iznos;
    private Proizvod proizvod;

    public StavkaRacuna() {
    }

    public StavkaRacuna(int rb, int kolicina, double iznos, Proizvod proizvod) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.proizvod = proizvod;
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

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
}
