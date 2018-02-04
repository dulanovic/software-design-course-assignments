package domen;

import java.io.Serializable;
import java.util.Date;

public class IstorijaStatusaRada implements Serializable {

    private int rb;
    private Date datum;
    private StatusRada statusRada;

    public IstorijaStatusaRada() {
        this.statusRada = new StatusRada();
    }

    public IstorijaStatusaRada(int rb, Date datum, StatusRada statusRada) {
        this.rb = rb;
        this.datum = datum;
        this.statusRada = statusRada;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public StatusRada getStatusRada() {
        return statusRada;
    }

    public void setStatusRada(StatusRada statusRada) {
        this.statusRada = statusRada;
    }

    private String profesor;
    private String student;
    private String brojIndeksa;
    private String godinaUpisa;
    private String status;

    public IstorijaStatusaRada(String profesor, String student, String brojIndeksa, String godinaUpisa, String status) {
        this.profesor = profesor;
        this.student = student;
        this.brojIndeksa = brojIndeksa;
        this.godinaUpisa = godinaUpisa;
        this.status = status;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public String getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(String godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
