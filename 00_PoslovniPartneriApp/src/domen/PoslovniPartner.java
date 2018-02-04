/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.Date;

/**
 *
 * @author student1
 */
public class PoslovniPartner {

    private int partnerID;
    private String naziv;
    private int pib;
    private int maticniBroj;
    private Date datumOsnivanja;
    private String ziroRacun;
    private String ulica;
    private String broj;
    private Mesto mesto;

    public PoslovniPartner() {
    }

    public PoslovniPartner(int partnerID) {
        this.partnerID = partnerID;
    }

    public PoslovniPartner(int partnerID, String naziv, int pib, int maticniBroj, Date datumOsnivanja, String ziroRacun, String ulica, String broj, Mesto mesto) {
        this.partnerID = partnerID;
        this.naziv = naziv;
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.datumOsnivanja = datumOsnivanja;
        this.ziroRacun = ziroRacun;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
    }

    public int getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(int partnerID) {
        this.partnerID = partnerID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public int getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(int maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public String getZiroRacun() {
        return ziroRacun;
    }

    public void setZiroRacun(String ziroRacun) {
        this.ziroRacun = ziroRacun;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
