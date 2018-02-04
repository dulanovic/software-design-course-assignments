package forme.model;

import domen.Komponenta;
import domen.Konfiguracija;
import domen.StavkaKonfiguracije;
import javax.swing.table.AbstractTableModel;

public class KonfiguracijaTableModel extends AbstractTableModel {

    private Konfiguracija k;
    private final String[] kolone = new String[]{"Komponenta", "Kolicina", "Iznos"};

    public KonfiguracijaTableModel(Konfiguracija k) {
        this.k = k;
    }

    @Override
    public int getRowCount() {
        if (k.getListaStavki() == null) {
            return 0;
        }
        return k.getListaStavki().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        StavkaKonfiguracije sk = k.getListaStavki().get(i);
        switch (i1) {
            case 0:
                return sk.getKomponenta();
            case 1:
                return sk.getKolicina();
            case 2:
                return sk.getIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        StavkaKonfiguracije sk = k.getListaStavki().get(i);
        switch (i1) {
            case 0:
                sk.setKomponenta((Komponenta) o);
                sk.setIznos(sk.getKomponenta().getCena() * sk.getKolicina());
                fireTableCellUpdated(i, i1 + 2);
                break;
            case 1:
                sk.setKolicina(Integer.parseInt((String) o));
                sk.setIznos(sk.getKomponenta().getCena() * sk.getKolicina());
                fireTableCellUpdated(i, i1 + 1);
                break;
            case 2:
                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajStavku() {
        k.dodajStavku();
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        k.obrisiStavku(red);
        fireTableDataChanged();
    }

    public Konfiguracija getK() {
        return k;
    }

    public void setK(Konfiguracija k) {
        this.k = k;
    }

}
