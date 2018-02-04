package komunikacija;

import domen.Reprezentacija;
import domen.Utakmica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
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
                    case Konst.VRATI_REPREZENTACIJE:
                        List<Reprezentacija> listaR = Kontroler.getInstance().vratiReprezentacije();
                        too.setRezultat(listaR);
                        break;
                    case Konst.VRATI_UTAKMICE:
                        List<Utakmica> listaU = Kontroler.getInstance().vratiUtakmice();
                        too.setRezultat(listaU);
                        break;
                    case Konst.SACUVAJ_IZMENE:
                        List<Utakmica> listaI = (List<Utakmica>) toz.getParametar();
                        Kontroler.getInstance().sacuvajIzmene(listaI);
                        break;
                    case Konst.OBRISI_UTAKMICE:
                        List<Utakmica> listaB = (List<Utakmica>) toz.getParametar();
                        Kontroler.getInstance().obrisi(listaB);
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
        new Komunikacija().pokreniServer();
    }
}
