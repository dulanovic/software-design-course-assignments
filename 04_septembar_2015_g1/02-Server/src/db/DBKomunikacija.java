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

public class DBKomunikacija {

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2015_09_septembar_g1", "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno otvaranje konekcije!", sqlex);
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

    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", sqlex);
        }
    }

    public List<Brend> vratiBrendove() throws Exception {
        try {
            List<Brend> lista = new ArrayList<>();
            String upit = "SELECT * FROM brend";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int brendID = rs.getInt(1);
                String naziv = rs.getString(2);

                Brend b = new Brend(brendID, naziv);
                lista.add(b);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste brendova!", sqlex);
        }
    }

    public List<Komponenta> vratiKomponente() throws Exception {
        try {
            List<Komponenta> lista = new ArrayList<>();
            String upit = "SELECT * FROM komponenta";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int komponentaID = rs.getInt(1);
                String naziv = rs.getString(2);
                double cena = rs.getDouble(3);
                String tip = rs.getString(4);

                Komponenta k = new Komponenta(komponentaID, naziv, cena, tip);
                lista.add(k);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste komponenti!", sqlex);
        }
    }

    public int vratiPrimarniKljuc() throws Exception {
        try {
            int kljuc = 0;
            String upit = "SELECT MAX(konfiguracijaid) FROM konfiguracija";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                kljuc = rs.getInt(1);
            }
            rs.close();
            iskaz.close();
            return kljuc;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje primarnog kljuca konfiguracije!", sqlex);
        }
    }

    public void sacuvajKonfiguraciju(Konfiguracija k) throws Exception {
        try {
            String upit = "INSERT INTO konfiguracija VALUES (?,?,?,?)";
            PreparedStatement iskaz = connection.prepareStatement(upit);
            iskaz.setInt(1, k.getKonfiguracijaID());
            iskaz.setString(2, k.getNaziv());
            iskaz.setDate(3, new java.sql.Date(k.getDatumIzrade().getTime()));
            iskaz.setInt(4, k.getBrend().getBrendID());
            iskaz.executeUpdate();

            String upit_ = "INSERT INTO stavkakonfiguracije VALUES (?,?,?,?,?)";
            PreparedStatement iskaz_ = connection.prepareStatement(upit_);
            for (StavkaKonfiguracije sk : k.getListaStavki()) {
                iskaz_.setInt(1, k.getKonfiguracijaID());
                iskaz_.setInt(2, sk.getRb());
                iskaz_.setInt(3, sk.getKolicina());
                iskaz_.setDouble(4, sk.getIznos());
                iskaz_.setInt(5, sk.getKomponenta().getKomponentaID());
                iskaz_.executeUpdate();
            }
            iskaz_.close();
            iskaz.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno cuvanje konfiguracije!", sqlex);
        }

    }

    public List<Komponenta> vratiListuZaServer() throws Exception {
        try {
            List<Komponenta> lista = new ArrayList<>();
            String upit = "SELECT k.naziv, k.tip, SUM(sk.kolicina) AS brojugradjenihkomada, SUM(sk.iznos) AS iznos FROM komponenta k INNER JOIN stavkakonfiguracije sk ON (sk.komponentaid=k.komponentaid) GROUP BY k.komponentaid ORDER BY brojugradjenihkomada DESC, iznos DESC";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                String tip = rs.getString(2);
                int brojUgradjenihKomada = rs.getInt(3);
                double iznos = rs.getDouble(4);

                Komponenta k = new Komponenta(naziv, tip, brojUgradjenihKomada, iznos);
                lista.add(k);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje konfiguracije!", sqlex);
        }
    }
}
