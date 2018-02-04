/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Mesto;
import domen.PoslovniPartner;
import domen.Proizvod;
import domen.Racun;
import domen.StavkaRacuna;
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

/**
 *
 * @author student1
 */
public class DbKomunikacija {

    private Connection connection;

    public void ucitajDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera!", ex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/poslovnipartneri", "root", "");
            connection.setAutoCommit(false);
            // Zahteva eksplicitnu potvrdu transakcije
        } catch (SQLException ex) {
            throw new Exception("Neuspesno otvaranje konekcije!", ex);
        }
    }

    public void commitTransakcije() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan commit transakcije!", ex);
        }
    }

    public void rollbackTransakcije() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan rollback transakcije!", ex);
        }
    }

    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", ex);
        }
    }

    public List<Mesto> vratiMesta() throws Exception {
        try {
            List<Mesto> lm = new ArrayList<>();
            String sql = "SELECT * FROM Mesto";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                int ptt = rs.getInt(1);
                String naziv = rs.getString("Naziv");
                Mesto m = new Mesto(ptt, naziv);
                lm.add(m);
            }
            rs.close();
            sqlStatement.close();
            return lm;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje mesta!", ex);
        }
    }

    public void sacuvajPartnera(PoslovniPartner pp) throws Exception {
        try {
            String sql = "INSERT INTO PoslovniPartner VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setInt(1, pp.getPartnerID());
            sqlStatement.setString(2, pp.getNaziv());
            sqlStatement.setInt(3, pp.getPib());
            sqlStatement.setInt(4, pp.getMaticniBroj());
            sqlStatement.setDate(5, new java.sql.Date(pp.getDatumOsnivanja().getTime()));
            sqlStatement.setString(6, pp.getZiroRacun());
            sqlStatement.setString(7, pp.getUlica());
            sqlStatement.setString(8, pp.getBroj());
            sqlStatement.setInt(9, pp.getMesto().getPtt());
            sqlStatement.executeUpdate();
            sqlStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje partnera!", ex);
        }
    }

    public List<PoslovniPartner> vratiPartnere() throws Exception {
        try {
            List<PoslovniPartner> lp = new ArrayList<>();
            String sql = "SELECT p.*, m.Naziv FROM PoslovniPartner p INNER JOIN Mesto m ON (p.ptt = m.ptt)";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                int partnerID = rs.getInt("PartnerID");
                String naziv = rs.getString(2);
                int pib = rs.getInt("Pib");
                int maticniBroj = rs.getInt("MaticniBroj");
                Date datumOsnivanja = new Date(rs.getDate("DatumOsnivanja").getTime());
                String ziroRacun = rs.getString("ZiroRacun");
                String ulica = rs.getString("Ulica");
                String broj = rs.getString("Broj");
                int ptt = rs.getInt("Ptt");
                String nazivMesta = rs.getString(10);
                Mesto m = new Mesto(ptt, nazivMesta);
                PoslovniPartner p = new PoslovniPartner(partnerID, naziv, pib, maticniBroj, datumOsnivanja, ziroRacun, ulica, broj, m);
                lp.add(p);
            }
            return lp;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje partnera!", ex);
        }
    }

    public List<Proizvod> vratiProizvode() throws Exception {
        try {
            String sql = "SELECT * FROM Proizvod";
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            List<Proizvod> lp = new ArrayList<>();
            while (rs.next()) {
                int proizvodID = rs.getInt("ProizvodID");
                String naziv = rs.getString(2);
                double cena = rs.getDouble("Cena");
                Proizvod p = new Proizvod(proizvodID, naziv, cena);
                lp.add(p);
            }
            return lp;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje partnera", ex);
        }
    }

    public void sacuvajRacun(Racun racun) throws Exception {
        try {
            String sql = "INSERT INTO Racun VALUES (?,?,?,?)";
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setInt(1, racun.getRacunID());
            sqlStatement.setDate(2, new java.sql.Date(racun.getDatum().getTime()));
            sqlStatement.setDouble(3, racun.getUkupanIznos());
            sqlStatement.setInt(4, racun.getPoslovniPartner().getPartnerID());
            sqlStatement.executeUpdate();

            String sql_stavka = "INSERT INTO StavkaRacuna VALUES (?,?,?,?,?)";
            PreparedStatement sqlStavka = connection.prepareStatement(sql_stavka);
            for (StavkaRacuna stavka : racun.getStavkaList()) {
                sqlStavka.setInt(1, racun.getRacunID());
                sqlStavka.setInt(2, stavka.getRb());
                sqlStavka.setInt(3, stavka.getKolicina());
                sqlStavka.setDouble(4, stavka.getIznos());
                sqlStavka.setInt(5, stavka.getProizvod().getProizvodID());
                sqlStavka.executeUpdate();
            }
            sqlStavka.close();
            sqlStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje racuna!", ex);
        }
    }

    public int uzmiNajveciIDPartnera() throws Exception {
        try {
            String upit = "SELECT partnerid FROM poslovnipartner ORDER BY partnerid DESC LIMIT 1";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            int najveciID = 0;
            if (rs.next()) {
                najveciID = rs.getInt("partnerid");
            }
            rs.close();
            s.close();
            return najveciID;
        } catch (SQLException sqlex) {
            throw new Exception("Izgleda nema nijednog partnera u tabeli...", sqlex);
        }
    }

    public void obrisiPartnera(int partnerid) throws Exception {
        try {
            String upit = "DELETE FROM poslovnipartner WHERE partnerid = " + partnerid;
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
        } catch (SQLException sqlex) {
            throw new Exception("U tabeli trenutno ne postoji partner sa tim ID.", sqlex);
        }

    }

    public List<PoslovniPartner> nadjiPartnera(String kriterijumPretrage) throws Exception {
        try {
            String upit = "SELECT p.*, m.naziv FROM poslovnipartner p INNER JOIN mesto m ON (p.ptt = m.ptt) WHERE p.naziv = \"" + kriterijumPretrage + "\"";
            System.out.println(upit);
            System.out.println("3");
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            System.out.println("4");
            List<PoslovniPartner> lista = new ArrayList<>();
            if (rs.next()) {
                int partnerID = rs.getInt("PartnerID");
                String naziv = rs.getString(2);
                int pib = rs.getInt("Pib");
                int maticniBroj = rs.getInt("MaticniBroj");
                Date datumOsnivanja = new Date(rs.getDate("DatumOsnivanja").getTime());
                String ziroRacun = rs.getString("ZiroRacun");
                String ulica = rs.getString("Ulica");
                String broj = rs.getString("Broj");
                int ptt = rs.getInt("Ptt");
                String nazivMesta = rs.getString(10);
                Mesto m = new Mesto(ptt, nazivMesta);
                PoslovniPartner p = new PoslovniPartner(partnerID, naziv, pib, maticniBroj, datumOsnivanja, ziroRacun, ulica, broj, m);
                System.out.println("u petji " + p);
                lista.add(p);
                System.out.println("5");
            }
            System.out.println("6");
            s.close();
            rs.close();
            System.out.println("2");
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Nije pronadjen nijedan partner pod tim nazivom", sqlex);
        }
    }

    public List<Racun> vratiRacune() throws Exception {
        try {
            String sql = "SELECT r.racunid, SUM(sr.iznos) AS ukupan_iznos, SUM(sr.kolicina) AS ukupna_kolicina, COUNT(*) AS ukupno_stavki FROM racun r INNER JOIN stavkaracuna sr ON (r.racunid = sr.racunid) GROUP BY r.racunid ORDER BY r.racunid ASC";
            List<Racun> lista = new ArrayList<>();
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                int racunID = rs.getInt(1);
                double ukupanIznos = rs.getDouble(2);
                int ukupnaKolicina = rs.getInt(3);
                int ukupanBrojStavki = rs.getInt(4);
                Racun r = new Racun(racunID, ukupanIznos, ukupnaKolicina, ukupanBrojStavki);
                lista.add(r);
            }
            return lista;
        } catch (SQLException sqle) {
            throw new Exception("Neuspesno ucitavanje racuna.", sqle);
        }
    }

    public List<Proizvod> vratiProizvodePrikaz() throws Exception {
        try {
            String sql = "SELECT p.proizvodid, p.naziv, p.cena, COUNT(*) AS broj_racuna, SUM(sr.kolicina) AS ukupna_kolicina, SUM(sr.iznos) AS ukupan_iznos FROM proizvod p INNER JOIN stavkaracuna sr ON (p.proizvodid = sr.proizvodid) GROUP BY p.proizvodid ORDER BY p.proizvodid";
            List<Proizvod> lista = new ArrayList<>();
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while (rs.next()) {
                int proizvodID = rs.getInt(1);
                String naziv = rs.getString(2);
                double cena = rs.getDouble(3);
                int brojRacuna = rs.getInt(4);
                int ukupnaKolicina = rs.getInt(5);
                double ukupanIznos = rs.getDouble(6);
                Proizvod p = new Proizvod(proizvodID, naziv, cena, brojRacuna, ukupnaKolicina, ukupanIznos);
                lista.add(p);
            }
            return lista;
        } catch (SQLException sqle) {
            throw new Exception("Neuspesno ucitavanja proizvoda.", sqle);
        }
    }
    
    public List<Proizvod> nadjiProizvode(String kriterijumPretrage) throws Exception {
        try {
            String sql = "SELECT * FROM (SELECT p.proizvodid, p.naziv, p.cena, COUNT(*) AS broj_racuna, SUM(sr.kolicina) AS ukupna_kolicina, SUM(sr.iznos) AS ukupan_iznos FROM proizvod p INNER JOIN stavkaracuna sr ON (p.proizvodid = sr.proizvodid) GROUP BY p.proizvodid ORDER BY p.proizvodid) AS tabela WHERE ukupan_iznos > " + kriterijumPretrage;
            List<Proizvod> lista = new ArrayList<>();
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            while(rs.next()) {
                int proizvodID = rs.getInt(1);
                String naziv = rs.getString(2);
                double cena = rs.getDouble(3);
                int brojRacuna = rs.getInt(4);
                int ukupnaKolicina = rs.getInt(5);
                double ukupanIznos = rs.getDouble(6);
                Proizvod p = new Proizvod(proizvodID, naziv, cena, brojRacuna, ukupnaKolicina, ukupanIznos);
                lista.add(p);
            }
            return lista;
        } catch (SQLException sqle) {
            throw new Exception("Neuspesna pretraga po zadatom kriterijumu!", sqle);
        }
    }
}
