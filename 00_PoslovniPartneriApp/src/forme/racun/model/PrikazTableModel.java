package forme.racun.model;

import domen.Racun;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Racun> lista;
    private String[] kolone = new String[]{"RB", "Ukupan iznos", "Ukupna kolicina", "Ukupno stavki"};

    public PrikazTableModel(List<Racun> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista == null) {
            return 0;
        }
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getRacunID();
            case 1:
                return r.getUkupanIznos();
            case 2:
                return r.getUkupnaKolicina();
            case 3:
                return r.getUkupanBrojStavki();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Racun r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                r.setRacunID(Integer.parseInt((String) o));
                break;
            case 1:
                r.setUkupanIznos(Double.parseDouble((String) o));
                break;
            case 2:
                r.setUkupnaKolicina(Integer.parseInt((String) o));
                break;
            case 3:
                r.setUkupanBrojStavki(Integer.parseInt((String) o));
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }

}
