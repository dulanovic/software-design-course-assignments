package db;

import domen.Angazovanje;
import domen.Inzenjer;
import domen.Projekat;
import domen.VrstaProjekta;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/prosoftjun16g1", "root", "");
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

    public List<VrstaProjekta> vratiVrsteProjekata() throws Exception {
        try {
            List<VrstaProjekta> lista = new ArrayList<>();
            String upit = "SELECT * FROM vrstaprojekta";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int vrstaID = rs.getInt(1);
                String naziv = rs.getString(2);

                VrstaProjekta vp = new VrstaProjekta(vrstaID, naziv);
                lista.add(vp);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste vrsta projekata!", sqlex);
        }

    }

    public List<Inzenjer> vratiInzenjere() throws Exception {
        try {
            List<Inzenjer> lista = new ArrayList<>();
            String upit = "SELECT * FROM inzenjer";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int inzenjerID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String jmbg = rs.getString(4);

                Inzenjer i = new Inzenjer(inzenjerID, ime, prezime, jmbg);
                lista.add(i);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje liste inzenjera!", sqlex);
        }
    }

    public int vratiPrimarniKljucProjekat() throws Exception {
        try {
            int pk = 0;
            String upit = "SELECT MAX(projekatid) FROM projekat";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                pk = rs.getInt(1);
                pk++;
            }
            rs.close();
            stmt.close();
            return pk;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje primarnog kljuca za projekat!", sqlex);
        }
    }

    public int vratiPrimarniKljucAngazovanje() throws Exception {
        try {
            int pk = 0;
            String upit = "SELECT MAX(angazovanjeid) FROM angazovanje";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                pk = rs.getInt(1);
                pk++;
            }
            rs.close();
            stmt.close();
            return pk;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno ucitavanje primarnog kljuca za angazovanje!", sqlex);
        }
    }

    public void sacuvajProjekat(Projekat p) throws Exception {
        try {
            String upit = "INSERT INTO projekat VALUES (?,?,?,?,?)";
            PreparedStatement pr_stmt = connection.prepareStatement(upit);
            pr_stmt.setInt(1, p.getProjekatID());
            pr_stmt.setString(2, p.getNaziv());
            pr_stmt.setDate(3, new java.sql.Date(p.getDatumOd().getTime()));
            pr_stmt.setDate(4, new java.sql.Date(p.getDatumDo().getTime()));
            pr_stmt.setInt(5, p.getVrsta().getVrstaID());
            pr_stmt.executeUpdate();

            String upitA = "INSERT INTO angazovanje VALUES (?,?,?,?)";
            PreparedStatement pr_stmtA = connection.prepareStatement(upitA);
            for (Angazovanje a : p.getLista()) {
                pr_stmtA.setInt(1, a.getAngazovanjeID());
                pr_stmtA.setInt(2, a.getInzenjer().getInzenjerID());
                pr_stmtA.setInt(3, a.getProjekat().getProjekatID());
                pr_stmtA.setDate(4, new java.sql.Date(a.getDatumAngazovanja().getTime()));
                pr_stmtA.executeUpdate();
            }
            pr_stmt.close();
            pr_stmtA.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw new Exception("Neuspesno cuvanje projekta sa angazovanjima!", sqlex);
        }

    }
    
    public List<Projekat> vratiListuZaServer() throws Exception {
        try {
            List<Projekat> lista = new ArrayList<>();
            String upit = "SELECT p.naziv,vp.naziv,COUNT(*) AS brojangazovanih FROM projekat p INNER JOIN vrstaprojekta vp ON (p.vrstaid=vp.vrstaprojekta) INNER JOIN angazovanje a ON (p.projekatid=a.projekatid) GROUP BY p.projekatid";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString(1);
                String nazivVP = rs.getString(2);
                int brojAngazovanih = rs.getInt(3);
                
                Projekat p = new Projekat(naziv, nazivVP, brojAngazovanih);
                lista.add(p);
            }
            rs.close();
            stmt.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz na serveru!", sqlex);
        }
    }
}
