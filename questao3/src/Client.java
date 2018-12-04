import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * A classe é Cliente corresponde a possíveis clientes que
 * farão uso do servidor de troca de mensagens. Ela foi
 * implementada como objeto remoto pelos motivos explicados nos comentários
 * da interface IClient(stub). 
 * 
 * Para o servidor, a classe Client funciona
 * como o skeleton, já que faz os mesmos processos que a classe Server, que são:
 * 
 * 1. Realizar o processo de unmarshalling dos parâmetros enviados pelo servidor
 * (neste caso, não existem parâmetros).
 * 2. Evocar a implementação real do método remoto show.
 * 3. Realizar o processo de marshalling do resultado da evocação do método show 
 * (neste caso, não há retorno).
 * 4. Retornar o valor para o servidor.
 * 
 */
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
			IServer s = (IServer) Naming.lookup("server"); //o IServer, considerado como stub, é utilizado para fazer a chamada ao objeto remoto.
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
