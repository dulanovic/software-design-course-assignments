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

    public List<Reprezentacija> vratiReprezentacije() throws Exception {
        try {
            List<Reprezentacija> lista = new ArrayList<>();
            String upit = "SELECT * FROM reprezentacija";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int reprezentacijaID = rs.getInt(1);
                String naziv = rs.getString(2);

                Reprezentacija r = new Reprezentacija(reprezentacijaID, naziv);
                lista.add(r);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno zatvaranje konekcije!", sqlex);
        }

    }

    public List<Utakmica> vratiUtakmice() throws Exception {
        try {
            List<Utakmica> lista = new ArrayList<>();
            String upit = "SELECT * FROM utakmica u INNER JOIN reprezentacija d ON (u.domacinid=d.reprezentacijaid) INNER JOIN reprezentacija g ON (u.gostid=g.reprezentacijaid)";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int utakmicaID = rs.getInt(1);
                String grupa = rs.getString(2);
                int golovaDomacin = rs.getInt(5);
                int golovaGost = rs.getInt(6);

                int domacinID = rs.getInt(7);
                String nazivDomacin = rs.getString(8);

                int gostID = rs.getInt(9);
                String nazivGost = rs.getString(10);

                Reprezentacija domacin = new Reprezentacija(domacinID, nazivDomacin);
                Reprezentacija gost = new Reprezentacija(gostID, nazivGost);
                Utakmica u = new Utakmica(utakmicaID, grupa, domacin, gost, golovaDomacin, golovaGost);
                lista.add(u);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno zatvaranje konekcije!", sqlex);
        }
    }

    public void sacuvajIzmene(Utakmica u) throws Exception {
        try {
            String upit = "UPDATE utakmica SET utakmicaid=" + u.getUtakmicaID() + ",grupa='" + u.getGrupa() + "',domacinid=" + u.getDomacin().getReprezentacijaID() + ",gostid=" + u.getGost().getReprezentacijaID() + ",golovadomacin=" + u.getGolovaDomacin() + ",golovagost=" + u.getGolovaGost() + " WHERE utakmicaid=" + u.getUtakmicaID();
            Statement stmt = connection.createStatement();
            stmt.execute(upit);
            stmt.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno zatvaranje konekcije!", sqlex);
        }
    }

    public void obrisi(Utakmica u) throws Exception {
        try {
            String upit = "DELETE FROM utakmica WHERE utakmicaid=" + u.getUtakmicaID();
            Statement stmt = connection.createStatement();
            stmt.execute(upit);
            stmt.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno zatvaranje konekcije!", sqlex);
        }
    }
}
