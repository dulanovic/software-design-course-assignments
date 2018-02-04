package db;

import domen.Reprezentacija;
import domen.Utakmica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBBroker {

    private Connection connection;

    public void ucitajDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfex) {
            throw new Exception("Neuspesno ucitavanje drajvera!", cnfex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/prosoftjul16g1", "root", "");
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

    public List<Utakmica> vratiUtakmice() throws Exception {
        try {
            List<Utakmica> lista = new ArrayList<>();
            String upit = "SELECT * FROM utakmica u INNER JOIN reprezentacija r ON (u.domacinid=r.reprezentacijaid) INNER JOIN reprezentacija e ON (u.gostid=e.reprezentacijaid) ORDER BY u.utakmicaid";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int utakmicaID = rs.getInt(1);
                String grupa = rs.getString(2);
                int golovaDomacin = rs.getInt(5);
                int golovaGost = rs.getInt(6);

                int domacinID = rs.getInt(3);
                String domacin = rs.getString(8);

                int gostID = rs.getInt(4);
                String gost = rs.getString(10);

                Reprezentacija repD = new Reprezentacija(domacinID, domacin);
                Reprezentacija repG = new Reprezentacija(gostID, gost);

                Utakmica u = new Utakmica(utakmicaID, grupa, repD, repG, golovaDomacin, golovaGost);
                lista.add(u);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste utakmica!", sqlex);
        }
    }

    public void sacuvajIzmene(Utakmica u) throws Exception {
        try {
            String upit = "UPDATE utakmica SET grupa='" + u.getGrupa() + "',domacinid=" + u.getDomacin().getReprezentacijaID() + ",gostid=" + u.getGost().getReprezentacijaID() + ",golovadomacin=" + u.getGolovaDomacin() + ",golovagost=" + u.getGolovaGost() + " WHERE utakmicaid=" + u.getUtakmicaID();
            Statement sql = connection.createStatement();
            sql.executeUpdate(upit);
            sql.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesna izmena podataka!", sqlex);
        }
    }

    public List<Reprezentacija> vratiReprezentacije() throws Exception {
        try {
            List<Reprezentacija> lista = new ArrayList<>();
            String upit = "SELECT * FROM reprezentacija";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int reprezentacijaID = rs.getInt(1);
                String naziv = rs.getString(2);

                Reprezentacija r = new Reprezentacija(reprezentacijaID, naziv);
                lista.add(r);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste reprezentacija!", sqlex);
        }
    }

    public void obrisiUtakmicu(Utakmica u) throws Exception {
        try {
            String upit = "DELETE FROM utakmica WHERE utakmicaid=" + u.getUtakmicaID();
            Statement sql = connection.createStatement();
            sql.executeUpdate(upit);
            sql.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno brisanje utakmica!", sqlex);
        }
    }
}
