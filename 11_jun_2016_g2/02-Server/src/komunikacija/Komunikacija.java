package komunikacija;

import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
import forme.PnlServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JFrame;
import poslovnalogika.Kontroler;
import sun.awt.im.InputMethodJFrame;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konst;

public class Komunikacija {

    private Socket socket;
    private boolean kraj = false;

    public void pokreniServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9000);
        System.out.println("Server je pokrenut.");
        socket = ss.accept();
        System.out.println("Klijent se povezao.");
        izvrsenjeOperacija();
    }

    private void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while (!kraj) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toz = (TransferObjekatZahtev) in.readObject();
            TransferObjekatOdgovor too = new TransferObjekatOdgovor();
            try {
                System.out.println("O: " + toz.getOperacija());
                switch (toz.getOperacija()) {
                    case Konst.VRATI_ISPITNE_ROKOVE:
                        List<IspitniRok> listaIR = Kontroler.getInstance().vratiIspitneRokove();
                        too.setRezultat(listaIR);
                        break;
                    case Konst.VRATI_NASTAVNIKE:
                        List<Nastavnik> listaN = Kontroler.getInstance().vratiNastavnike();
                        too.setRezultat(listaN);
                        break;
                    case Konst.VRATI_PREDMETE:
                        List<Predmet> listaP = Kontroler.getInstance().vratiPredmete();
                        too.setRezultat(listaP);
                        break;
                    case Konst.VRATI_PRIMARNI_KLJUC_DEZURSTVO:
                        int pkD = Kontroler.getInstance().vratiPrimarniKljucDezurstvo();
                        too.setRezultat(pkD);
                        break;
                    case Konst.SACUVAJ_DEZURSTVA:
                        List<Dezurstvo> listaD = (List<Dezurstvo>) toz.getParametar();
                        Kontroler.getInstance().sacuvajDezurstva(listaD);
                        break;
                    case Konst.KRAJ_RADA:
                        kraj = true;
                        break;
                }
            } catch (Exception ex) {
                too.setPoruka(ex.getMessage());
                too.setIzuzetak(ex);
            }
            posaljiOdgovor(too);
        }
    }

    private void posaljiOdgovor(TransferObjekatOdgovor too) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(too);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JFrame f = new JFrame();
        PnlServer p = new PnlServer();
        f.add(p);
        f.setTitle("[FON] FONDEZ - Server");
        f.setSize(p.getPreferredSize());
        f.setVisible(true);
        
        new Komunikacija().pokreniServer();
    }
}
