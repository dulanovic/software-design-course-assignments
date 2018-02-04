package domen;

import java.io.Serializable;

public class Knjiga implements Serializable {

    private int knjigaID;
    private String naziv;
    private String autor;
    private double cena;
    private int godinaIzdanja;
    private Izdavac i;

    public Knjiga() {
        this.i = new Izdavac();
    }

    public Knjiga(int knjigaID, String naziv, String autor, double cena, int godinaIzdanja, Izdavac i) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.autor = autor;
        this.cena = cena;
        this.godinaIzdanja = godinaIzdanja;
        this.i = i;
    }

    public int getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(int knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public Izdavac getI() {
        return i;
    }

    public void setI(Izdavac i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return naziv;
    }

    private String izdavac;
    private int brojProdatihPrimeraka;
    private double prihod;

    public Knjiga(String naziv, String autor, String izdavac, int brojProdatihPrimeraka, double prihod) {
        this.naziv = naziv;
        this.autor = autor;
        this.izdavac = izdavac;
        this.brojProdatihPrimeraka = brojProdatihPrimeraka;
        this.prihod = prihod;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public int getBrojProdatihPrimeraka() {
        return brojProdatihPrimeraka;
    }

    public void setBrojProdatihPrimeraka(int brojProdatihPrimeraka) {
        this.brojProdatihPrimeraka = brojProdatihPrimeraka;
    }

    public double getPrihod() {
        return prihod;
    }

    public void setPrihod(double prihod) {
        this.prihod = prihod;
    }

}
