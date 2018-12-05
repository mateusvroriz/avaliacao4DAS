import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	void sendBroadcast(String idSender, String message) throws RemoteException;
	void sendDirect(String receiver, String message) throws RemoteException;
	boolean connect(IClient o, String id) throws RemoteException;
}
