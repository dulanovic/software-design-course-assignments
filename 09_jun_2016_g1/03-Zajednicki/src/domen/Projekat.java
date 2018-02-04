package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projekat implements Serializable {

    private int projekatID;
    private String naziv;
    private Date datumOd;
    private Date datumDo;
    private VrstaProjekta vrsta;
    private List<Angazovanje> lista;

    public Projekat() {
        this.vrsta = new VrstaProjekta();
        this.lista = new ArrayList<>();
    }

    public Projekat(int projekatID, String naziv, Date datumOd, Date datumDo, VrstaProjekta vrsta) {
        this.projekatID = projekatID;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.vrsta = vrsta;
        this.lista = new ArrayList<>();
    }

    public int getProjekatID() {
        return projekatID;
    }

    public void setProjekatID(int projekatID) {
        this.projekatID = projekatID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public VrstaProjekta getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaProjekta vrsta) {
        this.vrsta = vrsta;
    }

    public List<Angazovanje> getLista() {
        return lista;
    }

    public void setLista(List<Angazovanje> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return naziv;
    }

    private String vrstaP;
    private int brojAngaovanih;

    public Projekat(String naziv, String vrstaP, int brojAngaovanih) {
        this.naziv = naziv;
        this.vrstaP = vrstaP;
        this.brojAngaovanih = brojAngaovanih;
    }

    public String getVrstaP() {
        return vrstaP;
    }

    public void setVrstaP(String vrstaP) {
        this.vrstaP = vrstaP;
    }

    public int getBrojAngaovanih() {
        return brojAngaovanih;
    }

    public void setBrojAngaovanih(int brojAngaovanih) {
        this.brojAngaovanih = brojAngaovanih;
    }

}
