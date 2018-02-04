package forme.model;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Vozilo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StavkeTableModel extends AbstractTableModel {

    private Rezervacija r;
    private String[] kolone = {"RB", "Vozilo", "Cena po danu", "Datum od", "Datum do", "Iznos"};
    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public StavkeTableModel(Rezervacija r) {
        this.r = r;
    }

    public Rezervacija getR() {
        return r;
    }

    public void setR(Rezervacija r) {
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
        StavkaRezervacije sr = r.getLista().get(i);
        switch (i1) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getVozilo();
            case 2:
                return sr.getVozilo().getCenaPoDanu();
            case 3:
                return sr.getDatumOd();
            case 4:
                return sr.getDatumDo();
            case 5:
                return sr.getIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        StavkaRezervacije sr = r.getLista().get(i);
        switch (i1) {
            case 0:
                sr.setRb(Integer.parseInt(String.valueOf(o)));
                break;
            case 1:
                sr.setVozilo((Vozilo) o);
                if (sr.getDatumOd() != null && sr.getDatumDo() != null) {
                    Calendar cal1 = new GregorianCalendar();
                    Calendar cal2 = new GregorianCalendar();

                    cal1.setTime(sr.getDatumOd());
                    cal2.setTime(sr.getDatumDo());
                    double iznos = ((cal2.getTime().getTime() - cal1.getTime().getTime()) / (1000 * 60 * 60 * 24)) * sr.getVozilo().getCenaPoDanu();
                    sr.setIznos(iznos);
                }
                fireTableCellUpdated(i, i1 + 4);
                break;
            case 2:
                sr.getVozilo().setCenaPoDanu(Double.parseDouble(String.valueOf(o)));
                break;
            case 3: {
                try {
                    sr.setDatumOd(df.parse(String.valueOf(o)));

                    if (sr.getDatumDo() != null) {
                        Calendar cal1 = new GregorianCalendar();
                        Calendar cal2 = new GregorianCalendar();

                        cal1.setTime(df.parse(String.valueOf(o)));
                        cal2.setTime(sr.getDatumDo());
                        double iznos = ((cal2.getTime().getTime() - cal1.getTime().getTime()) / (1000 * 60 * 60 * 24)) * sr.getVozilo().getCenaPoDanu();
                        sr.setIznos(iznos);
                    }
                    fireTableCellUpdated(i, i1 + 2);
                } catch (ParseException pex) {
                    pex.printStackTrace();
                }
                break;

            }
            case 4: {
                try {
                    sr.setDatumDo(df.parse(String.valueOf(o)));

                    if (sr.getDatumOd() != null) {
                        Calendar cal1 = new GregorianCalendar();
                        Calendar cal2 = new GregorianCalendar();

                        cal1.setTime(sr.getDatumOd());
                        cal2.setTime(df.parse(String.valueOf(o)));
                        double iznos = ((cal2.getTime().getTime() - cal1.getTime().getTime()) / (1000 * 60 * 60 * 24)) * sr.getVozilo().getCenaPoDanu();
                        sr.setIznos(iznos);
                    }
                    fireTableCellUpdated(i, i1 + 1);
                } catch (ParseException pex) {
                    pex.printStackTrace();
                }
                break;
            }
//            case 5:
//                sr.setIznos(Double.parseDouble(String.valueOf(o)));
//                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 1 || i1 == 3 || i1 == 4) {
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
        r.getLista().add(new StavkaRezervacije());
        osveziListu();
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        r.getLista().remove(red);
        osveziListu();
        fireTableDataChanged();
    }

    public List<StavkaRezervacije> vratiListu() {
        return r.getLista();
    }

    public void osveziListu() {
        int rb = 1;
        for (StavkaRezervacije sr : r.getLista()) {
            sr.setRb(rb);
            rb++;
        }
    }
}
