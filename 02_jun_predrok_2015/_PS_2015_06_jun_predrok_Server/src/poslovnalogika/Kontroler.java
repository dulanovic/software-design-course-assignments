package poslovnalogika;

import db.DBKomunikacija;
import domen.Drzava;
import domen.Glasanje;
import java.util.ArrayList;
import java.util.List;

public class Kontroler {

    private DBKomunikacija db;
    private static Kontroler instance;

    private Kontroler() {
        this.db = new DBKomunikacija();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<Drzava> vratiDrzave() throws Exception {
        List<Drzava> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiDrzave();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public int vratiKljuc() throws Exception {
        int kljuc = 0;
        db.ucitajDriver();
        db.otvoriKonekciju();
        kljuc = db.vratiKljuc();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return kljuc;
    }

    public void sacuvajGlasanja(List<Glasanje> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Glasanje g : lista) {
                System.out.println(g.getGlasanjeID() + ", " + g.getOdDrzava() + ", " + g.getZaDrzava() + ", " + g.getBrojPoena());
                db.sacuvajGlasanje(g);
                System.out.println(g.getGlasanjeID() + ", " + g.getOdDrzava() + ", " + g.getZaDrzava() + ", " + g.getBrojPoena());
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }

    public List<Drzava> vratiListuZaPrikaz() throws Exception {
        List<Drzava> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiListuZaPrikaz();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
