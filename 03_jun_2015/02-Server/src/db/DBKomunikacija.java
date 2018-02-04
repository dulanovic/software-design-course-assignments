package db;

import domen.Angazovanje;
import domen.Dizajner;
import domen.Model;
import domen.ModnaRevija;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2015_06_jun", "root", "");
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

    public List<Dizajner> vratiDizajnere() throws Exception {
        try {
            List<Dizajner> lista = new ArrayList<>();
            String upit = "SELECT * FROM dizajner";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int dizajnerID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);

                Dizajner d = new Dizajner(dizajnerID, ime, prezime);
                lista.add(d);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste dizajnera!", sqlex);
        }

    }

    public List<Model> vratiModele() throws Exception {
        try {
            List<Model> lista = new ArrayList<>();
            String upit = "SELECT * FROM model";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int modelID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                Date datumRodjenja = new Date(rs.getDate(4).getTime());

                Model m = new Model(modelID, ime, prezime, datumRodjenja);
                lista.add(m);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste modela!", sqlex);
        }
    }

    public void sacuvajModnuReviju(ModnaRevija modnaRevija) throws Exception {
        try {
            String upit = "INSERT INTO modnarevija (naziv,datumodrzavanja,dizajnerid) VALUES (?,?,?)";
            PreparedStatement iskaz = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            iskaz.setString(1, modnaRevija.getNaziv());
            iskaz.setDate(2, new java.sql.Date(modnaRevija.getDatumOdrzavanja().getTime()));
            iskaz.setInt(3, modnaRevija.getDizajner().getDizajnerID());
            iskaz.executeUpdate();
            System.out.println("posle execute_query1");

            ResultSet rs = iskaz.getGeneratedKeys();
            rs.next();
            int modnaRevijaID = rs.getInt(1);
            System.out.println(modnaRevijaID);

            String upit_ = "INSERT INTO angazovanje (brojsati,zarada,komentar,modnarevijaid,modelid) VALUES (?,?,?,?,?)";
            PreparedStatement iskaz_ = connection.prepareStatement(upit_);
            for (Angazovanje a : modnaRevija.getListaAngazovanja()) {
                iskaz_.setInt(1, a.getBrojSati());
                iskaz_.setDouble(2, a.getZarada());
                iskaz_.setString(3, a.getKomentar());
                iskaz_.setInt(4, modnaRevijaID);
                iskaz_.setInt(5, a.getModel().getModelID());
                iskaz_.executeUpdate();
            }
            iskaz_.close();
            iskaz.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje modne revije i angazovanja!", sqlex);
        }

    }

    public List<Model> vratiListuZaPrikaz() throws Exception {
        try {
            List<Model> lista = new ArrayList<>();
            String upit = "SELECT CONCAT(m.ime,' ',m.prezime) AS Model, COUNT(*) AS Brojrevija, SUM(a.zarada) AS Ukupnazarada, SUM(a.brojsati) AS Ukupanbrojsati FROM model m INNER JOIN angazovanje a ON (m.modelid=a.modelid) GROUP BY m.modelid ORDER BY Brojrevija ASC, Ukupnazarada DESC";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                String modelImePrezime = rs.getString(1);
                int brojRevija = rs.getInt(2);
                int ukupnaZarada = rs.getInt(3);
                int ukupanBrojSati = rs.getInt(4);

                Model m = new Model(modelImePrezime, brojRevija, ukupnaZarada, ukupanBrojSati);
                lista.add(m);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz!", sqlex);
        }
    }
}
