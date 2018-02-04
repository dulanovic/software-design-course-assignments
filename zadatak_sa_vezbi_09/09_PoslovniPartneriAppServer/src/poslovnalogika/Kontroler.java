/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika;

import db.DbKomunikacija;
import domen.Mesto;
import domen.PoslovniPartner;
import domen.Proizvod;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author student1
 */
public class Kontroler {

    private KolekcijaMesta km;
    private KolekcijaPartnera kp;
    private DbKomunikacija db;
    private static Kontroler instance;
    private Map<String, Object> map;

    private Kontroler() {
        km = new KolekcijaMesta();
        kp = new KolekcijaPartnera();
        db = new DbKomunikacija();
        map = new HashMap<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void dodajPartnera(PoslovniPartner pp) throws Exception {
        // kp.dodajPartnera(pp);
        db.ucitajDriver();
        db.otvoriKonekciju();
        db.sacuvajPartnera(pp);
        db.commitTransakcije();
        db.zatvoriKonekciju();
    }

    public List<PoslovniPartner> vratiPartnere() throws Exception {
        // return km.vratiMesta();
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<PoslovniPartner> lp = db.vratiPartnere();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lp;
    }

    public void dodajMesto(Mesto m) {
        km.dodajMesto(m);
    }

    public List<Mesto> vratiMesta() throws Exception {
        // return km.vratiMesta();
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Mesto> lm = db.vratiMesta();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lm;
    }

    public List<Proizvod> vratiProizvode() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Proizvod> lp = db.vratiProizvode();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lp;
    }
    
    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public void sacuvajPartnere(List<PoslovniPartner> lp) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (PoslovniPartner p : lp) {
                db.sacuvajPartnera(p);
                System.out.println("Sacuvao partnerID = " + p.getPartnerID());
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
    
    public void sacuvajRacun(Racun racun) throws Exception {
        try {
            racun.pripremiRacun();
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajRacun(racun);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
        
    }
}
