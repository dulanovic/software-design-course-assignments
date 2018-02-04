package db;

import domen.Izdavac;
import domen.Knjiga;
import domen.Racun;
import domen.StavkaRacuna;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2015_11_novembar", "root", "");
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

    public List<Knjiga> vratiKnjige() throws Exception {
        try {
            List<Knjiga> lista = new ArrayList<>();
            String upit = "SELECT * FROM knjiga k INNER JOIN izdavac i ON (k.izdavacid=i.izdavacid)";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int knjigaID = rs.getInt(1);
                String naziv = rs.getString(2);
                String autor = rs.getString(3);
                double cena = rs.getDouble(4);
                int godinaIzdanja = rs.getInt(5);

                int izdavacID = rs.getInt(7);
                String nazivIzdavaca = rs.getString(8);
                String adresa = rs.getString(9);

                Izdavac i = new Izdavac(izdavacID, nazivIzdavaca, adresa);
                Knjiga k = new Knjiga(knjigaID, naziv, autor, cena, godinaIzdanja, i);

                lista.add(k);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste knjiga!", sqlex);
        }
    }

    public void sacuvajRacun(Racun r) throws Exception {
        try {
            String upit = "INSERT INTO racun (datum,ukupaniznos) VALUES (?,?)";
            PreparedStatement iskaz = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            iskaz.setDate(1, new java.sql.Date(r.getDatum().getTime()));
            iskaz.setDouble(2, r.getUkupanIznos());
            iskaz.executeUpdate();

            ResultSet rs = iskaz.getGeneratedKeys();
            rs.next();
            int racunID = rs.getInt(1);

            String upit_ = "INSERT INTO stavkaracuna VALUES (?,?,?,?,?)";
            PreparedStatement iskaz_ = connection.prepareStatement(upit_);
            for (StavkaRacuna sr : r.getLista()) {
                iskaz_.setInt(1, racunID);
                iskaz_.setInt(2, sr.getRb());
                iskaz_.setInt(3, sr.getK().getKnjigaID());
                iskaz_.setInt(4, sr.getKolicina());
                iskaz_.setDouble(5, sr.getIznosStavke());
                iskaz_.executeUpdate();
            }
            rs.close();
            iskaz_.close();
            iskaz.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje racuna i njegovih stavki!", sqlex);
        }

    }

    public List<Knjiga> vratiListuZaServer() throws Exception {
        try {
            List<Knjiga> lista = new ArrayList<>();
            String upit = "SELECT k.naziv,k.autor,i.naziv,SUM(sr.kolicina) AS brojprodatihprimeraka,SUM(sr.iznosstavke) AS prihod FROM knjiga k INNER JOIN izdavac i ON (k.izdavacid=i.izdavacid) INNER JOIN stavkaracuna sr ON (k.knjigaid=sr.knjigaid) GROUP BY k.knjigaid ORDER BY brojprodatihprimeraka DESC, prihod DESC";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                String autor = rs.getString(2);
                String izdavac = rs.getString(3);
                int brojProdatihPrimeraka = rs.getInt(4);
                double prihod = rs.getDouble(5);

                Knjiga k = new Knjiga(naziv, autor, izdavac, brojProdatihPrimeraka, prihod);

                lista.add(k);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za server!", sqlex);
        }
    }
}
