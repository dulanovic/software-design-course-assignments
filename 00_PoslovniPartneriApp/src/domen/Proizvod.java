/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author student1
 */
public class Proizvod {

    private int proizvodID;
    private String naziv;
    private double cena;

    private int brojRacuna;
    private int ukupnaKolicina;
    private double ukupanIznos;

    public Proizvod() {
    }

    public Proizvod(int proizvodID, String naziv, double cena, int brojRacuna, int ukupnaKolicina, double ukupanIznos) {
        this.proizvodID = proizvodID;
        this.naziv = naziv;
        this.cena = cena;
        this.brojRacuna = brojRacuna;
        this.ukupnaKolicina = ukupnaKolicina;
        this.ukupanIznos = ukupanIznos;
    }

    public Proizvod(int proizvodID, String naziv, double cena) {
        this.proizvodID = proizvodID;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Proizvod) {
            Proizvod p = (Proizvod) obj;
            return p.getProizvodID() == this.proizvodID;
        }
        return false;
    }

    public int getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(int brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public int getUkupnaKolicina() {
        return ukupnaKolicina;
    }

    public void setUkupnaKolicina(int ukupnaKolicina) {
        this.ukupnaKolicina = ukupnaKolicina;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

}
