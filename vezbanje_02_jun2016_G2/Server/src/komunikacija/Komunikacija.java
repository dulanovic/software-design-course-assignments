package komunikacija;

import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import poslovnalogika.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

public class Komunikacija {

    private Socket socket;
    private boolean kraj = false;

    public void pokreniServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9002);
        System.out.println("Server je podignut.");
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
                switch (toz.getOperacija()) {
                    case Konstante.VRATI_ISPITNE_ROKOVE:
                        List<IspitniRok> listaIspitnihRokova = Kontroler.getInstance().vratiIspitneRokove();
                        too.setRezultat(listaIspitnihRokova);
                        break;
                    case Konstante.VRATI_NASTAVNIKE:
                        List<Nastavnik> listaNastavnika = Kontroler.getInstance().vratiNastavnike();
                        too.setRezultat(listaNastavnika);
                        break;
                    case Konstante.VRATI_PREDMETE:
                        List<Predmet> listaPredmeta = Kontroler.getInstance().vratiPredmete();
                        too.setRezultat(listaPredmeta);
                        break;
                    case Konstante.SACUVAJ_DEZURSTVA:
                        List<Dezurstvo> listaDezurstava = (List<Dezurstvo>) toz.getParametar();
                        Kontroler.getInstance().sacuvajDezurstva(listaDezurstava);
                        break;
                    case Konstante.KRAJ_RADA:
                        kraj = true;
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
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
