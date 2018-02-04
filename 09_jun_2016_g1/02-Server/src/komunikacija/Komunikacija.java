package komunikacija;

import domen.Inzenjer;
import domen.Projekat;
import domen.VrstaProjekta;
import forme.PnlServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JFrame;
import poslovnalogika.Kontroler;
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
                    case Konst.VRATI_VRSTE_PROJEKATA:
                        List<VrstaProjekta> listaVP = Kontroler.getInstance().vratiVrsteProjekata();
                        too.setRezultat(listaVP);
                        break;
                    case Konst.VRATI_INZENJERE:
                        List<Inzenjer> listaI = Kontroler.getInstance().vratiInzenjere();
                        too.setRezultat(listaI);
                        break;
                    case Konst.VRATI_PRIMARNI_KLJUC_PROJEKAT:
                        int pkP = Kontroler.getInstance().vratiPrimarniKljucProjekat();
                        too.setRezultat(pkP);
                        break;
                    case Konst.VRATI_PRIMARNI_KLJUC_ANGAZOVANJE:
                        int pkA = Kontroler.getInstance().vratiPrimarniKljucAngazovanje();
                        too.setRezultat(pkA);
                        break;
                    case Konst.SACUVAJ_PROJEKAT:
                        Projekat p = (Projekat) toz.getParametar();
                        Kontroler.getInstance().sacuvajProjekat(p);
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
        f.setSize(p.getPreferredSize());
        f.setTitle("[FON] Izvestaj o projektima");
        f.setVisible(true);
        new Komunikacija().pokreniServer();
    }
}
