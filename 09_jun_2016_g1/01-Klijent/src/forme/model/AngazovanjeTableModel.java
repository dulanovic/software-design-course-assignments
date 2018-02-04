package forme.model;

import domen.Angazovanje;
import domen.Inzenjer;
import domen.Projekat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AngazovanjeTableModel extends AbstractTableModel {

    private Projekat p;
    private String[] kolone = {"Projekat", "Inzenjer", "Datum angazovanja"};
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public AngazovanjeTableModel(Projekat p) {
        this.p = p;
    }

    public Projekat getP() {
        return p;
    }

    public void setP(Projekat p) {
        this.p = p;
    }

    @Override
    public int getRowCount() {
        if (p == null || p.getLista() == null) {
            return 0;
        } else {
            return p.getLista().size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Angazovanje a = p.getLista().get(i);
        switch (i1) {
            case 0:
                return a.getProjekat().getNaziv();
            case 1:
                return a.getInzenjer();
            case 2:
                return a.getDatumAngazovanja();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        Angazovanje a = p.getLista().get(i);
        switch (i1) {
            case 0:
                a.getProjekat().setNaziv(String.valueOf(o));
                break;
            case 1:
                a.setInzenjer((Inzenjer) o);
                break;
            case 2: {
                try {
                    a.setDatumAngazovanja(df.parse(String.valueOf(o)));
                } catch (ParseException pex) {
                    pex.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 1 || i1 == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajAngazovanje(Angazovanje a) {
        p.getLista().add(a);
        fireTableDataChanged();
    }

    public void obrisiAngazovanje(int red) {
        p.getLista().remove(red);
        fireTableDataChanged();
    }

    public List<Angazovanje> vratiListu() {
        return p.getLista();
    }
}
