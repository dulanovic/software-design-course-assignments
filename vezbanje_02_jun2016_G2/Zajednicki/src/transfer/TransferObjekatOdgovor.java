package transfer;

import java.io.Serializable;

public class TransferObjekatOdgovor implements Serializable {

    private String poruka;
    private Object rezultat;
    private Object izuzetak;

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Object getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Object izuzetak) {
        this.izuzetak = izuzetak;
    }

}
