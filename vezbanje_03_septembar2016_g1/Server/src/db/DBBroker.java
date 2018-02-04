package db;

import domen.Brend;
import domen.Komponenta;
import domen.Konfiguracija;
import domen.StavkaKonfiguracije;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBroker {

    private Connection connection;

    public void ucitajDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfex) {
            throw new Exception("Neuspesno ucitavanje drivera!", cnfex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/septembar2015g1", "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno otvaranje konekcije!", sqlex);
        }
    }

    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", sqlex);
        }
    }

    public void commitTransakcije() throws Exception {
        try {
            connection.commit();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesan commit transakcije!", sqlex);
        }
    }

    public void rollbackTransakcije() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesan rollback transakcije!", sqlex);
        }
    }

    public List<Brend> vratiBrendove() throws Exception {
        try {
            List<Brend> lista = new ArrayList<>();
            String upit = "SELECT * FROM brend";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int brendID = rs.getInt(1);
                String naziv = rs.getString(2);
                Brend b = new Brend(brendID, naziv);
                lista.add(b);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste brendova!", sqlex);
        }
    }

    public List<Komponenta> vratiKomponente() throws Exception {
        try {
            List<Komponenta> lista = new ArrayList<>();
            String upit = "SELECT * FROM komponenta";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int komponentaID = rs.getInt(1);
                String naziv = rs.getString(2);
                double cena = rs.getDouble(3);
                String tip = rs.getString(4);

                Komponenta k = new Komponenta(komponentaID, naziv, cena, tip);
                lista.add(k);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste komponenti!", sqlex);
        }
    }

    public int vratiID() throws Exception {
        try {
            String upit = "SELECT konfiguracijaid FROM konfiguracija ORDER BY konfiguracijaid DESC LIMIT 1";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            id++;
            return id;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste komponenti!", sqlex);
        }
    }

    public void sacuvajKonfiguraciju(Konfiguracija k) throws Exception {
        try {
            String upit1 = "INSERT INTO konfiguracija VALUES (?,?,?,?)";
            PreparedStatement sql1 = connection.prepareStatement(upit1);
            sql1.setInt(1, k.getKonfiguracijaID());
            sql1.setString(2, k.getNaziv());
            sql1.setDate(3, new java.sql.Date(k.getDatumIzrade().getTime()));
            sql1.setInt(4, k.getBrend().getBrendID());
            sql1.executeUpdate();

            String upit2 = "INSERT INTO stavkakonfiguracije VALUES (?,?,?,?,?)";
            PreparedStatement sql2 = connection.prepareStatement(upit2);
            for (StavkaKonfiguracije sk : k.getListaStavki()) {
                sql2.setInt(1, k.getKonfiguracijaID());
                sql2.setInt(2, sk.getRb());
                sql2.setInt(3, sk.getKolicina());
                sql2.setDouble(4, sk.getIznos());
                sql2.setInt(5, sk.getKomponenta().getKomponentaID());
                sql2.executeUpdate();
            }
            sql2.close();
            sql1.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje konfiguracije!", sqlex);
        }
    }
    
    public List<Komponenta> vratiListuZaPrikaz() throws Exception {
        try {
            List<Komponenta> lista = new ArrayList<>();
            String upit = "SELECT k.naziv,k.tip,SUM(sk.kolicina),SUM(sk.iznos) FROM komponenta k INNER JOIN stavkakonfiguracije sk ON (k.komponentaid=sk.komponentaid) GROUP BY k.komponentaid";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                String tip = rs.getString(2);
                int brojUgradjenihKomada = rs.getInt(3);
                double ukupanIznos = rs.getDouble(4);
                
                Komponenta k = new Komponenta(naziv, tip, brojUgradjenihKomada, ukupanIznos);
                lista.add(k);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz!", sqlex);
        }
    }
}
