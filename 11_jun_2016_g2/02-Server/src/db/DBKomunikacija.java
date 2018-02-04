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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/prosoftjun16g2", "root", "");
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

    public List<IspitniRok> vratiIspitneRokove() throws Exception {
        try {
            List<IspitniRok> lista = new ArrayList<>();
            String upit = "SELECT * FROM ispitnirok";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int ispitniRokID = rs.getInt(1);
                String naziv = rs.getString(2);
                Date datumOd = new Date(rs.getDate(3).getTime());
                Date datumDo = new Date(rs.getDate(4).getTime());

                IspitniRok iR = new IspitniRok(ispitniRokID, naziv, datumOd, datumDo);
                lista.add(iR);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste ispitnih rokova!", sqlex);
        }
    }

    public List<Nastavnik> vratiNastavnike() throws Exception {
        try {
            List<Nastavnik> lista = new ArrayList<>();
            String upit = "SELECT * FROM nastavnik";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int nastavnikID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String zvanje = rs.getString(4);

                Nastavnik n = new Nastavnik(nastavnikID, ime, prezime, zvanje);
                lista.add(n);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste nastavnika!", sqlex);
        }
    }

    public List<Predmet> vratiPredmete() throws Exception {
        try {
            List<Predmet> lista = new ArrayList<>();
            String upit = "SELECT * FROM predmet";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int predmetID = rs.getInt(1);
                String naziv = rs.getString(2);
                int trajanjeIspita = rs.getInt(3);

                Predmet p = new Predmet(predmetID, naziv, trajanjeIspita);
                lista.add(p);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste predmeta!", sqlex);
        }
    }

    public int vratiPrimarniKljucDezurstvo() throws Exception {
        try {
            int pkD = 0;
            String upit = "SELECT MAX(dezurstvoid) FROM dezurstvo";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                pkD = rs.getInt(1);
                pkD++;
            }
            rs.close();
            stmt.close();
            return pkD;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje primarnog kljuca za tabelu dezurstvo!", sqlex);
        }
    }

    public void sacuvajDezurstvo(Dezurstvo d) throws Exception {
        try {
            String upit = "INSERT INTO dezurstvo VALUES (?,?,?,?,?)";
            PreparedStatement pr_stmt = connection.prepareStatement(upit);
            pr_stmt.setInt(1, d.getDezurstvoID());
            pr_stmt.setDate(2, new java.sql.Date(d.getDatum().getTime()));
            pr_stmt.setInt(3, d.getIspitniRok().getIspitniRokID());
            pr_stmt.setInt(4, d.getNastavnik().getNastavnikID());
            pr_stmt.setInt(5, d.getPredmet().getPredmetID());
            pr_stmt.executeUpdate();

            pr_stmt.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno cuvanje dezurstva!", sqlex);
        }

    }

    public List<Nastavnik> vratiListuZaPrikaz(String kriterijum) throws Exception {
        try {
            List<Nastavnik> lista = new ArrayList<>();
            String upit = "SELECT CONCAT(n.ime,' ',n.prezime) AS Nastavnik,COUNT(*) AS UkupanBrDezurstava,SUM(p.trajanjeispita/60) AS UkupanBrSati,SUM(p.trajanjeispita/60)*10 AS UkupnoZaIsplatu FROM dezurstvo d INNER JOIN nastavnik n ON (d.nastavnikid=n.nastavnikid) INNER JOIN predmet p ON (d.predmetid=p.predmetid)" + kriterijum + " GROUP BY n.nastavnikid ORDER BY UkupnoZaIsplatu DESC";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String imePrezime = rs.getString(1);
                int ukupanBrojDezurstava = rs.getInt(2);
                int ukupanBrojSati = rs.getInt(3);
                int ukupnoZaIsplatu = rs.getInt(4);

                Nastavnik n = new Nastavnik(imePrezime, ukupanBrojDezurstava, ukupanBrojSati, ukupnoZaIsplatu);
                lista.add(n);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste za prikaz na serveru!", sqlex);
        }

    }
}
