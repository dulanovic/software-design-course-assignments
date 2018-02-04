package forme.model;

import domen.Dezurstvo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DezurstvoTableModel extends AbstractTableModel {

    private List<Dezurstvo> lista;
    private String[] kolone = {"Rok", "Nastavnik", "Predmet", "Datum", "Trajanje (min)"};

    public DezurstvoTableModel(List<Dezurstvo> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista == null) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Dezurstvo d = lista.get(i);
        switch (i1) {
            case 0:
                return d.getIspitniRok();
            case 1:
                return d.getNastavnik();
            case 2:
                return d.getPredmet();
            case 3:
                return d.getDatum();
            case 4:
                return d.getPredmet().getTrajanjeIspita();
            default:
                return "N/A";
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajDezurstvo(Dezurstvo d) {
        lista.add(d);
        fireTableDataChanged();
    }

    public void obrisiDezurstvo(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public List<Dezurstvo> vratiListu() {
        return lista;
    }
}
