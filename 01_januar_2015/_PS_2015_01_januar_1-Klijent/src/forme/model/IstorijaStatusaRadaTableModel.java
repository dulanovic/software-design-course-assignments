package forme.model;

import domen.IstorijaStatusaRada;
import domen.Rad;
import domen.StatusRada;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;

public class IstorijaStatusaRadaTableModel extends AbstractTableModel {

    private Rad rad;
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private String[] kolone = {"RB", "Status rada", "Datum(dd.MM.yyyy)"};

    public IstorijaStatusaRadaTableModel(Rad rad) {
        this.rad = rad;
    }

    public Rad getRad() {
        return rad;
    }

    public void setRad(Rad rad) {
        this.rad = rad;
    }

    @Override
    public int getRowCount() {
        return rad.getLista().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        IstorijaStatusaRada isr = rad.getLista().get(i);
        switch (i1) {
            case 0:
                return isr.getRb();
            case 1:
                return isr.getStatusRada();
            case 2:
                return isr.getDatum();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        IstorijaStatusaRada isr = rad.getLista().get(i);
        switch(i1) {
            case 0:
                isr.setRb(Integer.parseInt((String) o)); break;
            case 1:
                isr.setStatusRada((StatusRada) o); break;
            case 2: {
            try {
                isr.setDatum(df.parse((String) o));
            } catch (ParseException pex) {
                pex.printStackTrace();
            }
            break;
        }
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }
    
    public void obrisiStatus(int red) {
        rad.obrisiStatus(red);
        fireTableDataChanged();
    }

    public void dodajStatus() {
        rad.dodajStatus();
        fireTableDataChanged();
    }
}
