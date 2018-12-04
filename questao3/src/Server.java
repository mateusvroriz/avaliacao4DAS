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

	@Override
	public void sendBroadcast(String message) {
		for(IClient c : connectedClients.values()) {
			try{
				c.show(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendDirect(String id, String message) {
		for(String key : connectedClients.keySet()) {
			if(id.equals(key)) {
				IClient c = connectedClients.get(key);
				try{
					c.show(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
	}

	@Override
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
