package komunikacija;

import domen.Drzava;
import domen.Glasanje;
import forme.PnlServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import poslovnalogika.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

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
                    case Konstante.VRATI_DRZAVE:
                        List<Drzava> listaDrzava = new ArrayList<>();
                        listaDrzava = Kontroler.getInstance().vratiDrzave();
                        too.setRezultat(listaDrzava);
                        break;
                    case Konstante.VRATI_KLJUC:
                        int kljuc = 0;
                        kljuc = Kontroler.getInstance().vratiKljuc();
                        too.setRezultat(kljuc);
                        break;
                    case Konstante.SACUVAJ_GLASANJA:
                        List<Glasanje> listaGlasanja = new ArrayList<>();
                        listaGlasanja = (List<Glasanje>) toz.getParametar();
                        Kontroler.getInstance().sacuvajGlasanja(listaGlasanja);
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
        f.setVisible(true);
        new Komunikacija().pokreniServer();
    }
}
