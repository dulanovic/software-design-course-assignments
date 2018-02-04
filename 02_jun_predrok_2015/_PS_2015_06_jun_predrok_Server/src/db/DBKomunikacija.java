package db;

import domen.Drzava;
import domen.Glasanje;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2015_06_jun_predrok", "root", "");
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

    public List<Drzava> vratiDrzave() throws Exception {
        try {
            List<Drzava> lista = new ArrayList<>();
            String upit = "SELECT * FROM drzava";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int drzavaID = rs.getInt(1);
                String naziv = rs.getString(2);

                Drzava d = new Drzava(drzavaID, naziv);
                lista.add(d);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste drzava!", sqlex);
        }

    }

    public void sacuvajGlasanje(Glasanje glasanje) throws Exception {
        try {
            String upit = "INSERT INTO Glasanje VALUES (?,?,?,?)";
            PreparedStatement iskaz = connection.prepareStatement(upit);
            iskaz.setInt(1, glasanje.getGlasanjeID());
            iskaz.setInt(2, glasanje.getOdDrzava().getDrzavaID());
            iskaz.setInt(3, glasanje.getZaDrzava().getDrzavaID());
            iskaz.setInt(4, glasanje.getBrojPoena());
            iskaz.executeUpdate();
            System.out.println("+++");
            iskaz.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje glasanja!", sqlex);
        }
    }

    public int vratiKljuc() throws Exception {
        try {
            int kljuc = 0;
            String upit = "SELECT MAX(glasanjeid) FROM glasanje";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                kljuc = rs.getInt(1) + 1;
            }
            rs.close();
            iskaz.close();
            return kljuc;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje kljuca!", sqlex);
        }
    }

    public List<Drzava> vratiListuZaPrikaz() throws Exception {
        try {
            List<Drzava> lista = new ArrayList<>();
            String upit = "SELECT d.naziv, SUM(g.brojpoena) AS Brojpoena FROM drzava d INNER JOIN glasanje g ON (d.drzavaid=g.zadrzavaid) GROUP BY d.drzavaid ORDER BY Brojpoena DESC";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                int brojPoena = rs.getInt(2);

                Drzava d = new Drzava(naziv, brojPoena);
                lista.add(d);
            }
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz rezultata glasanja!", sqlex);
        }
    }
}
