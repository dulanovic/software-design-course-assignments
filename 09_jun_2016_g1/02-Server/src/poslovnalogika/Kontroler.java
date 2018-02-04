package poslovnalogika;

import db.DBKomunikacija;
import domen.Inzenjer;
import domen.Projekat;
import domen.VrstaProjekta;
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

    public List<VrstaProjekta> vratiVrsteProjekata() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<VrstaProjekta> lista = db.vratiVrsteProjekata();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Inzenjer> vratiInzenjere() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Inzenjer> lista = db.vratiInzenjere();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public int vratiPrimarniKljucProjekat() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        int pk = db.vratiPrimarniKljucProjekat();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return pk;
    }

    public int vratiPrimarniKljucAngazovanje() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        int pk = db.vratiPrimarniKljucAngazovanje();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return pk;
    }

    public void sacuvajProjekat(Projekat p) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajProjekat(p);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            ex.printStackTrace();
//            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }

    public List<Projekat> vratiListuZaServer() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Projekat> lista = db.vratiListuZaServer();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
