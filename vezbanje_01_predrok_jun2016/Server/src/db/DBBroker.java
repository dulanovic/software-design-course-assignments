package db;

import domen.Kurs;
import domen.Polaznik;
import domen.PolaznikKurs;
import domen.VrstaKursa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            throw new Exception("Neuspesno ucitavanje drivera!", cnfex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/prosoftpredrok16", "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException cnfex) {
            throw new Exception("Neuspesno otvaranje konekcije!", cnfex);
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

    public List<VrstaKursa> vratiVrste() throws Exception {
        try {
            List<VrstaKursa> lista = new ArrayList<>();
            String upit = "SELECT * FROM vrstakursa";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int vrstaID = rs.getInt(1);
                String naziv = rs.getString(2);

                VrstaKursa vk = new VrstaKursa(vrstaID, naziv);
                lista.add(vk);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste vrsta kurseva!", sqlex);
        }
    }

    public List<Polaznik> vratiPolaznike() throws Exception {
        try {
            List<Polaznik> lista = new ArrayList<>();
            String upit = "SELECT * FROM polaznik";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int polaznikID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String jmbg = rs.getString(4);

                Polaznik p = new Polaznik(polaznikID, ime, prezime, jmbg);
                lista.add(p);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste polaznika!", sqlex);
        }
    }

    public void sacuvajKurs(Kurs k) throws Exception {
        try {
            String upit = "INSERT INTO kurs (naziv,maxbrojpolaznika,datumod,datumdo,vrstaid) VALUES (?,?,?,?,?)";
            PreparedStatement sql = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            sql.setString(1, k.getNaziv());
            sql.setInt(2, k.getMaxBrojPolaznika());
            sql.setDate(3, new java.sql.Date(k.getDatumOd().getTime()));
            sql.setDate(4, new java.sql.Date(k.getDatumDo().getTime()));
            sql.setInt(5, k.getVrsta().getVrstaID());
            sql.executeUpdate();

            ResultSet rs = sql.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            String upit2 = "INSERT INTO polaznikkurs (polaznikid,kursid,datumprijave) VALUES (?,?,?)";
            PreparedStatement sql2 = connection.prepareStatement(upit2);
            for (PolaznikKurs pk : k.getListaPolaznikKurs()) {
                sql2.setInt(1, pk.getPolaznik().getPolaznikID());
                sql2.setInt(2, id);
                sql2.setDate(3, new java.sql.Date(pk.getDatumPrijave().getTime()));
                sql2.executeUpdate();
            }
            sql2.close();
            sql.close();
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            throw new Exception("Neuspesno cuvanje kursa!", sqlex);
        }

    }

    public List<Kurs> vratiListuZaPrikaz(String upit) throws Exception {
        try {
            List<Kurs> lista = new ArrayList<>();
//            String upit = "SELECT k.naziv,k.maxbrojpolaznika,COUNT(*) FROM kurs k INNER JOIN polaznikkurs pk ON (k.kursid=pk.kursid) GROUP BY pk.polaznikid";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                int maxBrojPolaznika = rs.getInt(2);
                int brojPrijavljenihPolaznika = rs.getInt(3);

                Kurs k = new Kurs(naziv, maxBrojPolaznika, brojPrijavljenihPolaznika);
                lista.add(k);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste kurseva za prikaz!", sqlex);
        }

    }
}
