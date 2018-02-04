package domen;

import java.io.Serializable;
import java.util.List;

public class Drzava implements Serializable {

    private int drzavaID;
    private String naziv;

    public Drzava() {
    }

    public Drzava(int drzavaID, String naziv) {
        this.drzavaID = drzavaID;
        this.naziv = naziv;
    }

    public int getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(int drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    private int brojPoena;
    private int rang;

    public Drzava(String naziv, int brojPoena) {
        this.naziv = naziv;
        this.brojPoena = brojPoena;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public static List<Drzava> izvrsiRangiranje(List<Drzava> lista) {
        int rang = 1;
        int rang2 = 1;
        int trenutniBrojPoena = lista.get(0).getBrojPoena();

        for (Drzava d : lista) {
            if (d.brojPoena == trenutniBrojPoena) {
                d.setRang(rang);
                trenutniBrojPoena = d.brojPoena;
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
