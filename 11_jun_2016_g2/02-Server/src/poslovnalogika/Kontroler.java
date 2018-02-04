package poslovnalogika;

import db.DBKomunikacija;
import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
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

    public List<IspitniRok> vratiIspitneRokove() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<IspitniRok> lista = db.vratiIspitneRokove();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Nastavnik> vratiNastavnike() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Nastavnik> lista = db.vratiNastavnike();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Predmet> vratiPredmete() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Predmet> lista = db.vratiPredmete();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public int vratiPrimarniKljucDezurstvo() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        int pkD = db.vratiPrimarniKljucDezurstvo();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return pkD;
    }

    public void sacuvajDezurstva(List<Dezurstvo> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Dezurstvo d : lista) {
                db.sacuvajDezurstvo(d);
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            ex.printStackTrace();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }

    }

    public List<Nastavnik> vratiListuZaPrikaz(String kriterijum) throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Nastavnik> lista = db.vratiListuZaPrikaz(kriterijum);
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
