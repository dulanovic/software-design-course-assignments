package poslovnalogika;

import db.DBKomunikacija;
import domen.Reprezentacija;
import domen.Utakmica;
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

    public List<Reprezentacija> vratiReprezentacije() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Reprezentacija> lista = db.vratiReprezentacije();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Utakmica> vratiUtakmice() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Utakmica> lista = db.vratiUtakmice();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void sacuvajIzmene(List<Utakmica> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Utakmica u : lista) {
                db.sacuvajIzmene(u);
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            ex.printStackTrace();
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }

    public void obrisi(List<Utakmica> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Utakmica u : lista) {
                db.obrisi(u);
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            ex.printStackTrace();
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
}
