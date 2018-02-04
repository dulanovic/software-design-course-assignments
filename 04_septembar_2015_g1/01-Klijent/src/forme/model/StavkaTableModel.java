package forme.model;

import domen.Komponenta;
import domen.Konfiguracija;
import domen.StavkaKonfiguracije;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StavkaTableModel extends AbstractTableModel {

    private Konfiguracija konfiguracija;
    private String[] kolone = {"Komponenta", "Kolicina", "Iznos"};

    public StavkaTableModel(Konfiguracija konfiguracija) {
        this.konfiguracija = konfiguracija;
    }

    @Override
    public int getRowCount() {
        if (konfiguracija.getListaStavki() == null) {
            return 0;
        } else {
            return konfiguracija.getListaStavki().size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        StavkaKonfiguracije sk = konfiguracija.getListaStavki().get(i);
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
        StavkaKonfiguracije sk = konfiguracija.getListaStavki().get(i);
        switch (i1) {
            case 0:
                sk.setKomponenta((Komponenta) o);
                sk.setIznos(sk.getKolicina() * sk.getKomponenta().getCena());
                fireTableCellUpdated(i, i1 + 2);
                break;
            case 1:
                sk.setKolicina(Integer.parseInt((String) o));
                sk.setIznos(sk.getKolicina() * sk.getKomponenta().getCena());
                fireTableCellUpdated(i, i1 + 1);
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
        konfiguracija.getListaStavki().add(new StavkaKonfiguracije());
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        konfiguracija.getListaStavki().remove(red);
        fireTableDataChanged();
    }

    public List<StavkaKonfiguracije> vratiListu() {
        return konfiguracija.getListaStavki();
    }
}
