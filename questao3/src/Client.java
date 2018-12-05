import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

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
		System.out.println();
		
		System.out.println("---------------------------------------");
		
		System.out.println(
				"Mensagem recebida. ID Recebedor: " 
				+ id + ". Mensagem: " + message);
		
		System.out.println("---------------------------------------");
	}
	
	public static void main(String[] args) {
		try {
			IServer s = (IServer) Naming.lookup("server");
			String message = "";
			String id = "";
			
			do {
				
				System.out.println("1 - Conectar novo cliente.");
				System.out.println("2 - Enviar mensagem broadcast.");
				System.out.println("3 - Enviar mensagem direta.");
				System.out.println("4 - Sair.");
				
				Scanner sc = new Scanner(System.in);
				String option = sc.next();
				
				IClient c;
				
				switch(option) {
					case "1":
						System.out.println("Digite o ID do cliente");
						id = sc.next();
						c = new Client(id);
						
						boolean connected = s.connect(c, id);
						
						if(!connected) {
							System.out.println("O cliete já está conectado!");
						}
						
						break;
					case "2":
						System.out.println("Digite sua mensagem: ");
						
						sc.nextLine();
						message = sc.nextLine();
						
						s.sendBroadcast(id, message);
						
						break;
					case "3":
						System.out.println("Digite a mensagem a ser enviada");
						sc.nextLine();
						message = sc.nextLine();
						
						System.out.println("Digite o ID do cliente destinatário");
						String idReceiver = sc.next();
						
						s.sendDirect(idReceiver, message);
						break;
					case "4":
						System.exit(0);
						break;
				}
			} while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
