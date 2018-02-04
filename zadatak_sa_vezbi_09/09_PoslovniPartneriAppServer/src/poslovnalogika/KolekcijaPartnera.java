/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika;

import domen.PoslovniPartner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student1
 */
public class KolekcijaPartnera {
    private List<PoslovniPartner> lp;

    public KolekcijaPartnera() {
        lp = new ArrayList<>();
    }
    
    public void dodajPartnera(PoslovniPartner pp) {
        lp.add(pp);
    }
    
    public List<PoslovniPartner> vratiPartnere() {
        return lp;
    }
    
    
    
}
