package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rad implements Serializable {

    private int RadID;
    private String tema;
    private Student student;
    private Profesor profesor;
    private List<IstorijaStatusaRada> lista;

    public Rad() {
        this.student = new Student();
        this.profesor = new Profesor();
        this.lista = new ArrayList<>();
    }

    public Rad(int RadID, String tema, Student student, Profesor profesor) {
        this.RadID = RadID;
        this.tema = tema;
        this.student = student;
        this.profesor = profesor;
        this.lista = new ArrayList<>();
    }

    public int getRadID() {
        return RadID;
    }

    public void setRadID(int RadID) {
        this.RadID = RadID;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<IstorijaStatusaRada> getLista() {
        return lista;
    }

    public void setLista(List<IstorijaStatusaRada> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return this.tema;
    }

    public void obrisiStatus(int red) {
        lista.remove(red);
    }
    
    public void dodajStatus() {
        lista.add(new IstorijaStatusaRada());
    }
}
