package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;

public class Komunikacija {
    
    private Socket socket;
    private static Komunikacija instance;

    private Komunikacija() throws IOException {
        this.socket = new Socket("127.0.0.1", 9002);
    }
    
    public static Komunikacija getInstance() throws IOException {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void posaljiZahtev(TransferObjekatZahtev toz) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(toz);
    }
    
    public TransferObjekatOdgovor primiOdgovor() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return (TransferObjekatOdgovor) in.readObject();
    }
}
