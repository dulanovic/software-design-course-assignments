package forme.model;

import domen.Knjiga;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StavkeTableModel extends AbstractTableModel {

    private Racun r;
    private String[] kolone = {"RB", "Naziv knjige", "Autor", "Cena", "Kolicina", "Iznos stavke"};

    public StavkeTableModel(Racun r) {
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
                return sr.getRb();
            case 1:
                return sr.getK();
            case 2:
                return sr.getK().getAutor();
            case 3:
                return sr.getK().getCena();
            case 4:
                return sr.getKolicina();
            case 5:
                return sr.getIznosStavke();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        StavkaRacuna sr = r.getLista().get(i);
        switch (i1) {
            case 0:
                sr.setRb(Integer.parseInt(String.valueOf(o)));
                break;
            case 1:
                sr.setK((Knjiga) o);
//                if (sr.getKolicina() != 0) {
                    sr.setIznosStavke(sr.getKolicina() * sr.getK().getCena());
//                }
                fireTableCellUpdated(i, i1 + 1);
                fireTableCellUpdated(i, i1 + 2);
                fireTableCellUpdated(i, i1 + 4);
                break;
//            case 2:
//                sr.getK().setAutor(String.valueOf(o));
//                break;
//            case 3:
//                sr.getK().setCena(Double.parseDouble(String.valueOf(o)));
//                break;
            case 4:
                sr.setKolicina(Integer.parseInt(String.valueOf(o)));
                sr.setIznosStavke(sr.getKolicina() * sr.getK().getCena());
                fireTableCellUpdated(i, i1 + 1);
                break;
//            case 5:
//                sr.setIznosStavke(Double.parseDouble(String.valueOf(o)));
//                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 1 || i1 == 4) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajStavku() {
        r.getLista().add(new StavkaRacuna());
        osveziTabelu();
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        this.r.getLista().remove(red);
        osveziTabelu();
        fireTableDataChanged();
    }

    public List<StavkaRacuna> vratiListu() {
        osveziTabelu();
        return r.getLista();
    }

    public void osveziTabelu() {
        int rb = 1;
        for (StavkaRacuna sr : r.getLista()) {
            sr.setRb(rb);
            rb++;
        }
    }
}
