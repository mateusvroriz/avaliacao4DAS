import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	void sendBroadcast(String message) throws RemoteException;
	void sendDirect(String id, String message) throws RemoteException;
	void connect(IClient o, String id) throws RemoteException;
}
