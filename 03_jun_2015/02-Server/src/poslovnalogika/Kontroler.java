package poslovnalogika;

import db.DBKomunikacija;
import domen.Dizajner;
import domen.Model;
import domen.ModnaRevija;
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

    public List<Dizajner> vratiDizajnere() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Dizajner> lista = db.vratiDizajnere();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Model> vratiModele() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Model> lista = db.vratiModele();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void sacuvajModnuReviju(ModnaRevija modnaRevija) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajModnuReviju(modnaRevija);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
    
    public List<Model> vratiListuZaPrikaz() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Model> lista = db.vratiListuZaPrikaz();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
