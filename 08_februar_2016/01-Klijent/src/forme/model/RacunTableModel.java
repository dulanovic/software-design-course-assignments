package forme.model;

import domen.Proizvod;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class RacunTableModel extends AbstractTableModel {

    private Racun r;
    private String[] kolone = {"Proizvod", "Cena", "Kolicina", "Iznos"};

    public RacunTableModel(Racun r) {
        this.r = r;
    }

    public Racun getR() {
        return r;
    }

    public void setR(Racun r) {
        this.r = r;
    }

    @Override
    public int getRowCount() {
        if (r == null || r.getLista() == null) {
            return 0;
        } else {
            return r.getLista().size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        StavkaRacuna sr = r.getLista().get(i);
        switch (i1) {
            case 0:
                return sr.getProizvod();
            case 1:
                return sr.getProizvod().getCena();
            case 2:
                return sr.getKolicina();
            case 3:
                return sr.getIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        StavkaRacuna sr = r.getLista().get(i);
        switch (i1) {
            case 0:
                sr.setProizvod((Proizvod) o);
                fireTableCellUpdated(i, i1 + 1);
                break;
//            case 1:
//                sr.getProizvod().setCena(Integer.parseInt(String.valueOf(o)));
//                break;
            case 2:
                sr.setKolicina(Integer.parseInt(String.valueOf(o)));
                sr.setIznos(sr.getKolicina() * sr.getProizvod().getCena());
                fireTableCellUpdated(i, i1 + 1);
                break;
//            case 3:
//                sr.setIznos(Double.parseDouble(String.valueOf(o)));
//                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 0 || i1 == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajStavku(StavkaRacuna sr) {
        r.getLista().add(sr);
        fireTableDataChanged();
    }

    public StavkaRacuna obrisiStavku(int red) {
        StavkaRacuna sr = r.getLista().get(red);
        r.getLista().remove(red);
        fireTableDataChanged();
        return sr;
    }

    public List<StavkaRacuna> vratiListu() {
        return r.getLista();
    }
}
