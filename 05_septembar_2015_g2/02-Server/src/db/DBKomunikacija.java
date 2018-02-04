package db;

import domen.Fakultet;
import domen.Kandidat;
import domen.PrijemniIspit;
import domen.StudijskiProgram;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2015_09_septembar_g2", "root", "");
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

    public List<StudijskiProgram> vratiStudijskePrograme() throws Exception {
        try {
            List<StudijskiProgram> lista = new ArrayList<>();
            String upit = "SELECT * FROM studijskiprogram sp INNER JOIN fakultet f ON (sp.fakultetid=f.fakultetid)";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int studijskiProgramID = rs.getInt(1);
                String naziv = rs.getString(2);
                int maxBrojStudenata = rs.getInt(3);

                int fakultetID = rs.getInt(5);
                String nazivFakulteta = rs.getString(6);

                Fakultet f = new Fakultet(fakultetID, nazivFakulteta);
                StudijskiProgram sp = new StudijskiProgram(studijskiProgramID, naziv, maxBrojStudenata, f);

                lista.add(sp);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste studijskih programa!", sqlex);
        }
    }

    public List<Kandidat> vratiKandidate() throws Exception {
        try {
            List<Kandidat> lista = new ArrayList<>();
            String upit = "SELECT * FROM kandidat";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                int kandidatID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                double brojPoenaSkola = rs.getDouble(4);

                Kandidat k = new Kandidat(kandidatID, ime, prezime, brojPoenaSkola);
                lista.add(k);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste kandidata!", sqlex);
        }
    }

    public void sacuvajPrijemniIspit(PrijemniIspit pi) throws Exception {
        try {
            String upit = "INSERT INTO prijemniispit VALUES (?,?,?)";
            PreparedStatement iskaz = connection.prepareStatement(upit);
            iskaz.setInt(1, pi.getStudijskiProgram().getStudijskiProgramID());
            iskaz.setInt(2, pi.getKandidat().getKandidatID());
            iskaz.setDouble(3, pi.getBrojPoenaPrijemni());
            iskaz.executeUpdate();

            iskaz.close();
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno cuvanje prijemnog ispita!", sqlex);
        }

    }

    public List<Kandidat> vratiListuZaServer(String kriterijum) throws Exception {
        try {
            List<Kandidat> lista = new ArrayList<>();
            String upit = "SELECT CONCAT(k.ime,' ',k.prezime) AS kandidat, k.brojpoenaskola, p.brojpoenaprijemni, k.brojpoenaskola+p.brojpoenaprijemni AS poenaukupno FROM prijemniispit p INNER JOIN kandidat k ON (p.kandidatid=k.kandidatid) INNER JOIN studijskiprogram sp ON (p.studijskiprogramid=sp.studijskiprogramid) WHERE sp.naziv='" + kriterijum + "' GROUP BY k.kandidatid, p.studijskiprogramid ORDER BY poenaukupno DESC";
            Statement iskaz = connection.createStatement();
            ResultSet rs = iskaz.executeQuery(upit);
            while (rs.next()) {
                String imePrezime = rs.getString(1);
                double poeniSkola = rs.getDouble(2);
                double poeniPrijemni = rs.getDouble(3);
                double poeniUkupno = rs.getDouble(4);

                Kandidat k = new Kandidat(imePrezime, poeniSkola, poeniPrijemni, poeniUkupno);
                lista.add(k);
            }
            rs.close();
            iskaz.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz!", sqlex);
        }

    }
}
