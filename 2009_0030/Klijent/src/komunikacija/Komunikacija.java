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
        socket = new Socket("127.0.0.1", 9000);
    }
    
    public static Komunikacija getInstance() throws IOException {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void posaljiZahtev(TransferObjekatZahtev toZahtev) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(toZahtev);
    }
    
    public TransferObjekatOdgovor primiOdgovor() throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObjekatOdgovor) inSocket.readObject();
    }
}
