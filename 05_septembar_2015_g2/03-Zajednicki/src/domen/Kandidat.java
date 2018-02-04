package domen;

import java.io.Serializable;
import java.util.List;

public class Kandidat implements Serializable {

    private int kandidatID;
    private String ime;
    private String prezime;
    private double brojPoenaSkola;

    public Kandidat() {
    }

    public Kandidat(int kandidatID, String ime, String prezime, double brojPoenaSkola) {
        this.kandidatID = kandidatID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojPoenaSkola = brojPoenaSkola;
    }

    public int getKandidatID() {
        return kandidatID;
    }

    public void setKandidatID(int kandidatID) {
        this.kandidatID = kandidatID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public double getBrojPoenaSkola() {
        return brojPoenaSkola;
    }

    public void setBrojPoenaSkola(double brojPoenaSkola) {
        this.brojPoenaSkola = brojPoenaSkola;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    private int rang;
    private String kandidatImePrezime;
    private double brojPoenaPrijemni;
    private double brojPoenaUkupno;

    public Kandidat(String kandidatImePrezime, double brojPoenaSkola, double brojPoenaPrijemni, double brojPoenaUkupno) {
        this.brojPoenaSkola = brojPoenaSkola;
        this.kandidatImePrezime = kandidatImePrezime;
        this.brojPoenaPrijemni = brojPoenaPrijemni;
        this.brojPoenaUkupno = brojPoenaUkupno;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getKandidatImePrezime() {
        return kandidatImePrezime;
    }

    public void setKandidatImePrezime(String kandidatImePrezime) {
        this.kandidatImePrezime = kandidatImePrezime;
    }

    public double getBrojPoenaPrijemni() {
        return brojPoenaPrijemni;
    }

    public void setBrojPoenaPrijemni(double brojPoenaPrijemni) {
        this.brojPoenaPrijemni = brojPoenaPrijemni;
    }

    public double getBrojPoenaUkupno() {
        return brojPoenaUkupno;
    }

    public void setBrojPoenaUkupno(double brojPoenaUkupno) {
        this.brojPoenaUkupno = brojPoenaUkupno;
    }

    public static List<Kandidat> rangiraj(List<Kandidat> lista) {
        int rang = 1;
        int rang2 = 1;
        double trenutniBrojPoena = lista.get(0).getBrojPoenaUkupno();

        for (Kandidat d : lista) {
            if (d.brojPoenaUkupno == trenutniBrojPoena) {
                d.setRang(rang);
                trenutniBrojPoena = d.brojPoenaUkupno;
                rang2++;
            } else {
                rang = rang2;
                d.setRang(rang);
                rang2++;
            }
        }
        return lista;
    }
}
