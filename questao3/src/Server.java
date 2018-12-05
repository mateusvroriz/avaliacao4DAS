import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server extends UnicastRemoteObject implements IServer{

	private HashMap<String, IClient> connectedClients = new HashMap<String, IClient>();
	
	protected Server() throws RemoteException {
		super();
	}

	public void sendBroadcast(String idSender, String message) {
		for(String key : connectedClients.keySet()) {
			try{
				if(!key.equals(idSender)) {
					IClient c = connectedClients.get(key);
					c.show(message);					
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void sendDirect(String idReceiver, String message) {
		for(String key : connectedClients.keySet()) {
			if(idReceiver.equals(key)) {
				IClient c = connectedClients.get(key);
				
				try{
					c.show(message);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			}
		}	
	}

	public boolean connect(IClient c, String id) {
		for(String key : connectedClients.keySet()) {
			if(id.equals(key)) {
				return false;
			}
		}
		
		connectedClients.put(id, c);
		return true;
	}
	
	public static void main(String[] args) {
		try {
			Server s = new Server();
			Naming.rebind("server", s);
			
			System.out.println("Servidor funcionando!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
