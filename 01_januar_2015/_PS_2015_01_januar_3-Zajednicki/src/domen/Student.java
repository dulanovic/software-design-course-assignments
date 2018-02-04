package domen;

import java.io.Serializable;

public class Student implements Serializable {

    private int studentID;
    private String brojIndeksa;
    private int godinaUpisa;
    private String ime;
    private String prezime;

    public Student() {
    }

    public Student(int studentID, String brojIndeksa, int godinaUpisa, String ime, String prezime) {
        this.studentID = studentID;
        this.brojIndeksa = brojIndeksa;
        this.godinaUpisa = godinaUpisa;
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime;
    }

}
