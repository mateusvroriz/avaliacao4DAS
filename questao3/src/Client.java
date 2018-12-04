import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Client extends UnicastRemoteObject implements IClient{

	private String id = "";
	
	protected Client(String id) throws RemoteException {
		super();
		
		this.id = id;
	}

	@Override
	public void show(String message) {
		System.out.println(
				"Mensagem recebida. ID Recebedor: " 
				+ id + " mensage: " + message);
	}
	
	public static void main(String[] args) {
		try {
			
			String id = "";
			String message = "";
			
			if(args.length == 2) {
				id = args[0];
				message = args[1];
			} else {
				System.out.println("Informe o ID do cliente que está enviando a mesange e a mensage a ser enviada "
						+ "como argumento da linha de comando!");
				System.exit(0);
			}
			
			IServer s = (IServer) Naming.lookup("server");
			
			IClient c = new Client(id);
			
			s.connect(c, id);

			s.sendBroadcast(id, message);
			
			s.sendDirect("ID3", message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
