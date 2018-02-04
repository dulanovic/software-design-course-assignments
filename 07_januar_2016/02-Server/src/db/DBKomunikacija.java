package db;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Tip;
import domen.Vozilo;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2016_01_januar", "root", "");
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

    public List<Vozilo> vratiVozila() throws Exception {
        try {
            List<Vozilo> lista = new ArrayList<>();
            String upit = "SELECT * FROM vozilo v INNER JOIN tip t ON (v.tipid=t.tipid)";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int voziloID = rs.getInt(1);
                String registracija = rs.getString(2);
                double cenaPoDanu = rs.getDouble(3);

                int tipID = rs.getInt(5);
                String naziv = rs.getString(6);

                Tip t = new Tip(tipID, naziv);
                Vozilo v = new Vozilo(voziloID, registracija, cenaPoDanu, t);

                lista.add(v);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste vozila!", sqlex);
        }

    }

    public void sacuvajRezervaciju(Rezervacija r) throws Exception {
        try {
            String upit = "INSERT INTO rezervacija (klijent,ukupaniznos) VALUES (?,?)";
            PreparedStatement pr_stmt = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            pr_stmt.setString(1, r.getKlijent());
            pr_stmt.setDouble(2, r.getUkupanIznos());
            pr_stmt.executeUpdate();

            ResultSet rs = pr_stmt.getGeneratedKeys();
            rs.next();
            int rezervacijaID = rs.getInt(1);

            String upit2 = "INSERT INTO stavkarezervacije VALUES (?,?,?,?,?,?)";
            PreparedStatement pr_stmt2 = connection.prepareStatement(upit2);
            for (StavkaRezervacije sr : r.getLista()) {
                pr_stmt2.setInt(1, rezervacijaID);
                pr_stmt2.setInt(2, sr.getRb());
                pr_stmt2.setInt(3, sr.getVozilo().getVoziloID());
                pr_stmt2.setDate(4, new java.sql.Date(sr.getDatumOd().getTime()));
                pr_stmt2.setDate(5, new java.sql.Date(sr.getDatumDo().getTime()));
                pr_stmt2.setDouble(6, sr.getIznos());
                pr_stmt2.executeUpdate();
            }
            pr_stmt2.close();
            pr_stmt.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje rezervacije i njenih stavki!", sqlex);
        }

    }
}
