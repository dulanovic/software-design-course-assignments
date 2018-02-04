package poslovnalogika;

import db.DBKomunikacija;
import domen.Kandidat;
import domen.PrijemniIspit;
import domen.StudijskiProgram;
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
    
    public List<StudijskiProgram> vratiStudijskePrograme() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<StudijskiProgram> lista = db.vratiStudijskePrograme();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
    
    public List<Kandidat> vratiKandidate() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Kandidat> lista = db.vratiKandidate();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
    
    public void sacuvajPrijemneIspite(List<PrijemniIspit> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (PrijemniIspit pi : lista) {
                db.sacuvajPrijemniIspit(pi);
                System.out.println("Sacuvan rezultat: " + pi.getKandidat() + ", " + pi.getStudijskiProgram() + " -> " + pi.getBrojPoenaPrijemni());
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
    
    public List<Kandidat> vratiListuZaServer(String kriterijum) throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Kandidat> lista = db.vratiListuZaServer(kriterijum);
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
