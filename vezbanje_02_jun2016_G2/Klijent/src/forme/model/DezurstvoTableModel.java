package forme.model;

import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DezurstvoTableModel extends AbstractTableModel {

    private List<Dezurstvo> lista;
    private String[] kolone = new String[]{"Rok", "Nastavnik", "Predmet", "Datum", "Trajanje (min)"};
    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

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
    public void setValueAt(Object o, int i, int i1) {
        Dezurstvo d = lista.get(i);
        switch (i1) {
            case 0:
                d.setIspitniRok((IspitniRok) o);
                break;
            case 1:
                d.setNastavnik((Nastavnik) o);
                break;
            case 2:
                d.setPredmet((Predmet) o);
                break;
            case 3:
                try {
                    d.setDatum(df.parse((String) o));
                } catch (ParseException pex) {
                    pex.printStackTrace();
                }
                break;
            case 4:
                d.getPredmet().setTrajanjeIspita(Integer.parseInt((String) o));
                break;
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

    public Dezurstvo vratiRed(int red) {
        return lista.get(red);
    }

    public List<Dezurstvo> vratiListu() {
        return lista;
    }

    public void dodajRed(Dezurstvo d) {
        lista.add(d);
        fireTableDataChanged();
    }

    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
