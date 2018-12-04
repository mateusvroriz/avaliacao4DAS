import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Server extends UnicastRemoteObject implements IServer{

	private HashMap<String, IClient> connectedClients = new HashMap<String, IClient>();
	
	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sendBroadcast(String message) {
		for(IClient c : connectedClients.values()) {
			try{
				System.out.println("Mensagem teste.");
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void sendDirect(String id, String message) {
		for(String key : connectedClients.keySet()) {
			if(id.equals(key)) {
				IClient c = connectedClients.get(key);
				try{
					System.out.println("Mensagem teste.");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
	}

	public void connect(IClient c, String id) {
		connectedClients.put(id, c);	
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
