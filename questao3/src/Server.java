import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
/*
 * Na arquitetura RMI, o skeleton é o responsável por desserializar (unmarshall) os parâmetros enviados pelo 
 * stub, quando estes chegam no servidor, e realizar a chamada a implementação concreta método remoto. Além disso, o skeleton também é responsável por serializar (marshall) a resposta a ser enviada para o cliente e por enviar a resposta.

https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/spec/rmi-arch2.html

 * Antes da versão 1.2, o skeleton, era gerado pelo compilador rmic. A partir da versão 
 * 1.2 do java, a funcionalidade do skeleton é implementada por código genérico, e o skeleton teve seu uso subtituído pela simples implementação da classe do servidor que representa o objeto remoto.

https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/relnotes.html

https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/spec/rmi-arch2.html

https://paginas.fe.up.pt/~pfs/aulas/sd2009/at/78rmi_handout.pdf

  * Portanto, acreditamos que a classe Server, representa o skeleton, já que ela:
  * 1. Recebe os parâmetros enviados pelo stub da máquina local e realiza o processo
  * de desserialização (unmarshalling) destes parâmetros.
  * 2. Executa a implementação real dos método remotos. 
  * 3. Realiza o processo de serialização (marshalling) das respostas.
  * 4. Envia a resposta para o cliente.x
 */
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
