package forme.model;

import domen.Angazovanje;
import domen.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AngazovanjeTableModel extends AbstractTableModel {

    private List<Angazovanje> lista;
    private String[] kolone = {"Ime i prezime modela", "Broj sati", "Zarada", "Komentar"};

    public AngazovanjeTableModel(List<Angazovanje> lista) {
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
        Angazovanje a = lista.get(i);
        switch (i1) {
            case 0:
                return a.getModel();
            case 1:
                return a.getBrojSati();
            case 2:
                return a.getZarada();
            case 3:
                return a.getKomentar();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        Angazovanje a = lista.get(i);
        switch (i1) {
            case 0:
                a.setModel((Model) o);
                break;
            case 1:
                a.setBrojSati(Integer.parseInt((String) o));
                a.setZarada(a.getBrojSati() * 100);
                fireTableCellUpdated(i, i1 + 1);
                break;
            case 3:
                a.setKomentar((String) o);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 2) {
            return false;
        }
        return true;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajAngazovanje() {
        lista.add(new Angazovanje());
        fireTableDataChanged();
    }

    public void obrisiAngazovanje(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public List<Angazovanje> vratiListu() {
        return lista;
    }
}
