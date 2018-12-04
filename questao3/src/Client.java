import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IClient{

	private String id = "";
	
	protected Client(String id) throws RemoteException {
		super();
		
		this.id = id;
	}

	//@Override
	//public void show(String message) {
		//System.out.println(
			//	"Mensagem recebida. ID Recebedor: " 
				//+ id + " mensage: " + message);
	//}
	
	public static void main(String[] args) {
		try {
			IServer s = (IServer) Naming.lookup("server");
			
			Client c1 = new Client("ID1");
			Client c2 = new Client("ID2");
			Client c3 = new Client("ID3");
			Client c4 = new Client("ID4");
			
			s.connect(c1, "ID1");
			s.connect(c2, "ID2");
			s.connect(c3, "ID3");
			s.connect(c4, "ID4");
			
			s.sendBroadcast("Mensagem Um");
			
			s.sendDirect("ID3", "Mensagem Dois");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
