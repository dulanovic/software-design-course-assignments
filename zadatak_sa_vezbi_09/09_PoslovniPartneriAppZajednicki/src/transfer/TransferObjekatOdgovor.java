/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author student1
 */
public class TransferObjekatOdgovor implements Serializable {
    private Object rezultat;
    private String poruka;
    private Object izuzetak;

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Object izuzetak) {
        this.izuzetak = izuzetak;
    }
    
    
    
}
