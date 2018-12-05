import java.rmi.Remote;
import java.rmi.RemoteException;


/*
 * "Stubs são responsáveis por representarem, localmente, o objeto remoto. 
 * É no stub que o a máquina local faz a chamada para o objeto remoto, 
 * e é o stub o responsável por transferir essa chamada para o servidor. 
 * Além disso, o stub implementa as mesmas interfaces que o objeto remoto 
 * (neste projeto, foi utilizado uma único arquivo de interface, por questões de conveniência).

https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/spec/rmi-arch2.html

 * Antes da versão 1.5 do java, as classes stub eram geradas pelo compilador rmic. 
 * A partir desta versão, as classes stub passaram a ser automaticamente e dinamicamente, 
 * durante o tempo de execução. x''

https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/relnotes.html

 * Apesar disso, acreditamos que a interface IServer atende alguma das características do stub, 
 * como o fato de ser utilizada, na máquina local, para realizar as chamadas a um objeto remoto. 
 * Por isso, consideramos que IServer corresponde ao stub na arquitetura RMI".
*/
public interface IServer extends Remote {
	void sendBroadcast(String idSender, String message) throws RemoteException;
	void sendDirect(String receiver, String message) throws RemoteException;
	boolean connect(IClient o, String id) throws RemoteException;
}
