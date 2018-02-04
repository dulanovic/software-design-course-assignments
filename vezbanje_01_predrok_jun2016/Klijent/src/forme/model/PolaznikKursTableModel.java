package forme.model;

import domen.Kurs;
import domen.Polaznik;
import domen.PolaznikKurs;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PolaznikKursTableModel extends AbstractTableModel {

    private Kurs kurs;
    private final String[] kolone = new String[]{"Kurs", "Polaznik", "Datum prijave"};
    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public PolaznikKursTableModel(Kurs kurs) {
        this.kurs = kurs;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    @Override
    public int getRowCount() {
        if (kurs.getListaPolaznikKurs() == null) {
            return 0;
        } else {
            return kurs.getListaPolaznikKurs().size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PolaznikKurs pk = kurs.getListaPolaznikKurs().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pk.getKurs();
            case 1:
                return pk.getPolaznik();
            case 2:
                return pk.getDatumPrijave();
            default:
                return "asb";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        PolaznikKurs pk = kurs.getListaPolaznikKurs().get(i);
        switch (i1) {
            case 0:
                pk.setKurs((Kurs) o);
                break;
            case 1:
                pk.setPolaznik((Polaznik) o);
                break;
            case 2: {
                try {
                    pk.setDatumPrijave(df.parse((String) o));
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

    public PolaznikKurs vratiRed(int red) {
        return kurs.getListaPolaznikKurs().get(red);
    }

    public void dodajRed(PolaznikKurs pk) {
        kurs.dodajUListu(pk);
        fireTableDataChanged();
    }

    public void obrisiRed(int red) {
        kurs.obrisiIzListe(red);
        fireTableDataChanged();
    }

    public List<PolaznikKurs> vratiListu() {
        return kurs.getListaPolaznikKurs();
    }
}
