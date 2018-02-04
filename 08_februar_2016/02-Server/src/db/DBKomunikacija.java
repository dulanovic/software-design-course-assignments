package db;

import domen.Klijent;
import domen.Proizvod;
import domen.Racun;
import domen.StavkaRacuna;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2016_02_februar", "root", "");
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

    public Racun nadjiRacun(int id) throws Exception {
        try {
            Racun r = new Racun();
            String upit = "SELECT * FROM racun r INNER JOIN klijent k ON (r.klijentid=k.klijentid) WHERE racunid=" + id;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int racunID = rs.getInt(1);
                Date datum = new Date(rs.getDate(2).getTime());
                double ukupanIznos = rs.getDouble(4);

                int klijentID = rs.getInt(5);
                String naziv = rs.getString(6);
                String adresa = rs.getString(7);

                Klijent k = new Klijent(klijentID, naziv, adresa);
                r.setRacunID(racunID);
                r.setDatum(datum);
                r.setKlijent(k);
                r.setUkupanIznos(ukupanIznos);
            }
            stmt.close();

            List<StavkaRacuna> lista = new ArrayList<>();
            String upit2 = "SELECT * FROM stavkaracuna sr INNER JOIN proizvod p ON (sr.proizvodid=p.proizvodid) WHERE sr.racunid=" + id;
            Statement stmt2 = connection.createStatement();
            rs = stmt2.executeQuery(upit2);
            while (rs.next()) {
                int rb = rs.getInt(2);
                int kolicina = rs.getInt(3);
                double iznos = rs.getDouble(5);

                int proizvodID = rs.getInt(6);
                String nazivP = rs.getString(7);
                double cena = rs.getDouble(8);

                Proizvod p = new Proizvod(proizvodID, nazivP, cena);
                StavkaRacuna sr = new StavkaRacuna(rb, kolicina, p, iznos);
                lista.add(sr);
            }
            stmt2.close();

            System.out.println(r.getKlijent());

            r.setLista(lista);
            return r;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesna pretraga racuna po unetom primarnom kljucu!", sqlex);
        }

    }

    public List<Klijent> vratiKlijente() throws Exception {
        try {
            List<Klijent> lista = new ArrayList<>();
            String upit = "SELECT * FROM klijent";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int klijentID = rs.getInt(1);
                String naziv = rs.getString(2);
                String adresa = rs.getString(3);

                Klijent k = new Klijent(klijentID, naziv, adresa);
                lista.add(k);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste klijenata!", sqlex);
        }

    }

    public List<Proizvod> vratiProizvode() throws Exception {
        try {
            List<Proizvod> lista = new ArrayList<>();
            String upit = "SELECT * FROM proizvod";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int proizvodID = rs.getInt(1);
                String naziv = rs.getString(2);
                double cena = rs.getDouble(3);

                Proizvod p = new Proizvod(proizvodID, naziv, cena);
                lista.add(p);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste proivoda!", sqlex);
        }
    }

    public void sacuvajRacun(Racun r) throws Exception {
        try {
            String upit = "UPDATE racun SET racunid=" + r.getRacunID() + ", datum='" + new java.sql.Date(r.getDatum().getTime()) + "', klijentid=" + r.getKlijent().getKlijentID() + ", ukupaniznos=" + r.getUkupanIznos() + " WHERE racunid=" + r.getRacunID();
            Statement stmt = connection.createStatement();
            stmt.execute(upit);

            for (StavkaRacuna sr : r.getLista()) {
                String upitS = "UPDATE stavkaracuna SET racunid=" + r.getRacunID() + ", rb=" + sr.getRb() + ", kolicina=" + sr.getKolicina() + ", proizvodid=" + sr.getProizvod().getProizvodID() + ", iznos=" + sr.getIznos() + " WHERE racunid=" + r.getRacunID() + " AND rb=" + sr.getRb();
                Statement stmtS = connection.createStatement();
                stmtS.execute(upitS);
                stmtS.close();
            }
            for (StavkaRacuna sr : r.getListaZaBrisanje()) {
                String upitD = "DELETE FROM stavkaracuna WHERE racunid=" + r.getRacunID() + " AND rb=" + sr.getRb();
                Statement stmtD = connection.createStatement();
                stmtD.execute(upitD);
                stmtD.close();
            }
            for (StavkaRacuna sr : r.getListaZaCuvanje()) {
                String upitI = "INSERT INTO stavkaracuna VALUES (" + r.getRacunID() + "," + sr.getRb() + "," + sr.getKolicina() + "," + sr.getProizvod().getProizvodID() + "," + sr.getIznos() + ")";
                Statement stmtI = connection.createStatement();
                stmtI.execute(upitI);
                stmtI.close();
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesna izmena racuna i njegovih stavki!", sqlex);
        }
    }

    public List<Klijent> vratiListuZaPrikaz() throws Exception {
        try {
            List<Klijent> lista = new ArrayList<>();
            String upit = "SELECT k.naziv,SUM(r.ukupaniznos) AS ukupaniznosracuna FROM klijent k INNER JOIN racun r ON (k.klijentid=r.klijentid) GROUP BY k.klijentid";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                double ukupanIznosRacuna = rs.getDouble(2);

                Klijent k = new Klijent(naziv, ukupanIznosRacuna);
                lista.add(k);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste klijenata za prikaz na serveru!", sqlex);
        }
    }
}
