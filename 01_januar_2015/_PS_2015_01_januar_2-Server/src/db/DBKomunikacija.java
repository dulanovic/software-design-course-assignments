package db;

import domen.IstorijaStatusaRada;
import domen.Profesor;
import domen.Rad;
import domen.StatusRada;
import domen.Student;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost/_projektovanje_softvera_2015_01_januar", "root", "");
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

    public List<Student> vratiStudente() throws Exception {
        try {
            List<Student> lista = new ArrayList<>();
            String upit = "SELECT * FROM student";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int studentID = rs.getInt(1);
                String brojIndeksa = rs.getString(2);
                int godinaUpisa = rs.getInt(3);
                String ime = rs.getString(4);
                String prezime = rs.getString(5);

                Student s = new Student(studentID, brojIndeksa, godinaUpisa, ime, prezime);
                lista.add(s);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste studenata.");
        }
    }

    public List<Profesor> vratiProfesore() throws Exception {
        try {
            List<Profesor> lista = new ArrayList<>();
            String upit = "SELECT * FROM profesor";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int profesorID = rs.getInt(1);
                String ime = rs.getString(2);
                String prezime = rs.getString(3);
                String zvanje = rs.getString(4);

                Profesor p = new Profesor(profesorID, ime, prezime, zvanje);
                lista.add(p);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste profesora.");
        }
    }

    public List<StatusRada> vratiStatuse() throws Exception {
        try {
            List<StatusRada> lista = new ArrayList<>();
            String upit = "SELECT * FROM statusrada";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                int statusID = rs.getInt(1);
                String nazivStatusa = rs.getString(2);

                StatusRada sr = new StatusRada(statusID, nazivStatusa);
                lista.add(sr);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Neuspesno ucitavanje liste statusa.");
        }
    }

    public void sacuvajRad(Rad rad) throws Exception {
        try {
            String upit = "INSERT INTO rad VALUES (?,?,?,?)";
            PreparedStatement sql = connection.prepareStatement(upit);
            sql.setInt(1, rad.getRadID());
            sql.setString(2, rad.getTema());
            sql.setInt(3, rad.getStudent().getStudentID());
            sql.setInt(4, rad.getProfesor().getProfesorID());
            sql.executeUpdate();

            String upit_ = "INSERT INTO istorijastatusarada VALUES (?,?,?,?)";
            PreparedStatement sql_ = connection.prepareStatement(upit_);
            for (IstorijaStatusaRada isr : rad.getLista()) {

                sql_.setInt(1, rad.getRadID());
                sql_.setInt(2, isr.getRb());
                sql_.setDate(3, new java.sql.Date(isr.getDatum().getTime()));
                sql_.setInt(4, isr.getStatusRada().getStatusID());
                sql_.executeUpdate();
            }
            sql_.close();
            sql.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje rada.");
        }

    }

    public int vratiKljuc() throws Exception {
        try {
            int key = 0;
            String upit = "SELECT MAX(radid) FROM rad";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                key = rs.getInt(1);
            }
            rs.close();
            sql.close();
            key++;
            return key;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje primarnog kljuca za rad.");
        }
    }

    public List<IstorijaStatusaRada> vratiListuZaPrikaz() throws Exception {
        try {
            List<IstorijaStatusaRada> lista = new ArrayList<>();
            String upit = "SELECT CONCAT(p.ime,' ',p.prezime) AS Mentor,CONCAT(s.ime,' ',s.prezime) AS Student,s.brojindeksa 'Broj indeksa',s.godinaupisa AS 'Godina upisa',tabela.nazivstatusa AS 'Status rada' FROM rad r INNER JOIN (SELECT * FROM istorijastatusarada isr INNER JOIN statusrada sr ON (isr.statusradaid=sr.statusid)) AS tabela ON (r.radid=tabela.radid) INNER JOIN student s ON (r.studentid=s.studentid) INNER JOIN profesor p ON (r.profesorid=p.profesorid) ORDER BY s.godinaupisa,tabela.datum ASC";
            Statement sql = connection.createStatement();
            ResultSet rs = sql.executeQuery(upit);
            while (rs.next()) {
                String profesor = rs.getString(1);
                String student = rs.getString(2);
                String brojIndeksa = rs.getString(3);
                String godinaUpisa = rs.getString(4);
                String statusRada = rs.getString(5);

                IstorijaStatusaRada isr = new IstorijaStatusaRada(profesor, student, brojIndeksa, godinaUpisa, statusRada);
                lista.add(isr);
            }
            rs.close();
            sql.close();
            return lista;
        } catch (SQLException sqlex) {
            throw new Exception("Neuspesno ucitavanje liste za prikaz.");
        }
    }
}
