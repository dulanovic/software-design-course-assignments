package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kurs implements Serializable {

    private int kursID;
    private String naziv;
    private int maxBrojPolaznika;
    private Date datumOd;
    private Date datumDo;
    private VrstaKursa vrsta;
    private List<PolaznikKurs> listaPolaznikKurs;

    private int brojPrijavljenihPolaznika;

    public Kurs() {
        this.vrsta = new VrstaKursa();
        this.listaPolaznikKurs = new ArrayList<>();
    }

    public Kurs(String naziv, int maxBrojPolaznika, int brojPrijavljenihPolaznika) {
        this.naziv = naziv;
        this.maxBrojPolaznika = maxBrojPolaznika;
        this.brojPrijavljenihPolaznika = brojPrijavljenihPolaznika;
    }

    public Kurs(String naziv, int maxBrojPolaznika, Date datumOd, Date datumDo, VrstaKursa vrsta) {
        this.naziv = naziv;
        this.maxBrojPolaznika = maxBrojPolaznika;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.vrsta = vrsta;
        this.listaPolaznikKurs = new ArrayList<>();
    }

    public int getKursID() {
        return kursID;
    }

    public void setKursID(int kursID) {
        this.kursID = kursID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMaxBrojPolaznika() {
        return maxBrojPolaznika;
    }

    public void setMaxBrojPolaznika(int maxBrojPolaznika) {
        this.maxBrojPolaznika = maxBrojPolaznika;
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

    public VrstaKursa getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaKursa vrsta) {
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public List<PolaznikKurs> getListaPolaznikKurs() {
        return listaPolaznikKurs;
    }

    public void setListaPolaznikKurs(List<PolaznikKurs> listaPolaznikKurs) {
        this.listaPolaznikKurs = listaPolaznikKurs;
    }

    public void dodajUListu(PolaznikKurs pk) {
        listaPolaznikKurs.add(pk);
    }

    public void obrisiIzListe(int red) {
        listaPolaznikKurs.remove(red);
    }

    public int getBrojPrijavljenihPolaznika() {
        return brojPrijavljenihPolaznika;
    }

    public void setBrojPrijavljenihPolaznika(int brojPrijavljenihPolaznika) {
        this.brojPrijavljenihPolaznika = brojPrijavljenihPolaznika;
    }

}
