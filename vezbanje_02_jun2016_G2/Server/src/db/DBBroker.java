package db;

import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBroker {

    private Connection connection;

    public void ucitajDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera.", ex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/prosoftjun16g2", "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno otvaranje konekcije.", sqlex);
        }
    }

    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno zatvaranje konekcije.", sqlex);
        }
    }

    public void commitTransakcije() throws Exception {
        try {
            connection.commit();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesan commit transakcije.", sqlex);
        }
    }

    public void rollbackTransakcije() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje drivera.", sqlex);
        }
    }

    public List<IspitniRok> vratiIspitneRokove() throws Exception {
        try {
            List<IspitniRok> lista = new ArrayList<>();
            String upit = "SELECT * FROM ispitnirok";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int ispitniRokID = rs.getInt(1);
                String naziv = rs.getString(2);
                Date datumOd = new Date(rs.getDate(3).getTime());
                Date datumDo = new Date(rs.getDate(4).getTime());

                IspitniRok ir = new IspitniRok(ispitniRokID, naziv, datumOd, datumDo);
                lista.add(ir);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste ispitnih rokova.", sqlex);
        }
    }

    public List<Nastavnik> vratiNastavnike() throws Exception {
        try {
            List<Nastavnik> lista = new ArrayList<>();
            String upit = "SELECT * FROM nastavnik";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int nastavnikID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String zvanje = rs.getString(4);

                Nastavnik n = new Nastavnik(nastavnikID, ime, prezime, zvanje);
                lista.add(n);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste nastavnika.", sqlex);
        }
    }

    public List<Predmet> vratiPredmete() throws Exception {
        try {
            List<Predmet> lista = new ArrayList<>();
            String upit = "SELECT * FROM predmet";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int predmetID = rs.getInt(1);
                String naziv = rs.getString(2);
                int trajanjeIspita = rs.getInt(3);

                Predmet p = new Predmet(predmetID, naziv, trajanjeIspita);
                lista.add(p);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste predmeta.", sqlex);
        }
    }

    public void sacuvajDezurstvo(Dezurstvo d) throws Exception {
        try {
            String upit = "INSERT INTO dezurstvo (datum,ispitnirokid,nastavnikid,predmetid) VALUES(?,?,?,?)";
            PreparedStatement sql = connection.prepareStatement(upit);
            sql.setDate(1, new java.sql.Date(d.getDatum().getTime()));
            sql.setInt(2, d.getIspitniRok().getIspitniRokID());
            sql.setInt(3, d.getNastavnik().getNastavnikID());
            sql.setInt(4, d.getPredmet().getPredmetID());
            sql.executeUpdate();
            sql.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje dezurstva.", sqlex);
        }

    }
    
    public List<Nastavnik> vratiListuZaPrikaz(String upit) throws Exception {
        try {
            List<Nastavnik> lista = new ArrayList<>();
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while(rs.next()) {
                int nastavnikID = rs.getInt(1);
                int ukupanBrojDezurstava = rs.getInt(2);
                double ukupanBrojSati = rs.getDouble(3);
                double ukupnoZaIsplatu = rs.getDouble(4);
                
                Nastavnik n = new Nastavnik(nastavnikID, ukupanBrojDezurstava, ukupanBrojSati, ukupnoZaIsplatu);
                lista.add(n);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz.", sqlex);
        }
    }
}
