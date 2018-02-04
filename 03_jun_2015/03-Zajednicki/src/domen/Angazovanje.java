package domen;

import java.io.Serializable;

public class Angazovanje implements Serializable {

    private int angazovanjeID;
    private int brojSati;
    private double zarada;
    private String komentar;
    private ModnaRevija modnaRevija;
    private Model model;

    public Angazovanje() {
        this.modnaRevija = new ModnaRevija();
        this.model = new Model();
    }

    public Angazovanje(int angazovanjeID, int brojSati, double zarada, String komentar, ModnaRevija modnaRevija, Model model) {
        this.angazovanjeID = angazovanjeID;
        this.brojSati = brojSati;
        this.zarada = zarada;
        this.komentar = komentar;
        this.modnaRevija = modnaRevija;
        this.model = model;
    }

    public int getAngazovanjeID() {
        return angazovanjeID;
    }

    public void setAngazovanjeID(int angazovanjeID) {
        this.angazovanjeID = angazovanjeID;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public double getZarada() {
        return zarada;
    }

    public void setZarada(double zarada) {
        this.zarada = zarada;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public ModnaRevija getModnaRevija() {
        return modnaRevija;
    }

    public void setModnaRevija(ModnaRevija modnaRevija) {
        this.modnaRevija = modnaRevija;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
