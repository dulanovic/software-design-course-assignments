/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika;

import domen.Mesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student1
 */
public class KolekcijaMesta {
    private List<Mesto> lm;

    public KolekcijaMesta() {
        lm = new ArrayList<>();
    }
    
    public void dodajMesto(Mesto m) {
        lm.add(m);
    }
    
    public List<Mesto> vratiMesta() {
        return lm;
    }
    
    
}
