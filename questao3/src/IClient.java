import java.rmi.Remote;
import java.rmi.RemoteException;


/* A interface IClient é implementada pelo cliente local e 
 * é replicada no servidor para que ele tenha acesso aos métodos
 * do objeto local. Por conveniência, a interface não foi replicada
 * neste projeto.
 * 
 * Além disto, a interface foi implementada como um possível objeto
 * Remoto para permitir que o servidor faça o download de uma nova
 * versão do objeto local sempre que uma nova implementação surja.
 * Caso isto não seja feito, as alterações no método show não serão
 * percebidas pelo servidor.
 * 
 * A interface é IClient é aquela na qual o servidor faz a chamada
 * para o objeto remoto Client(skeleton). Por este fator, ela também é considerada
 * como um stub, atendendo aos mesmos critérios utilizados para a classificação
 * da interface IServer.
 */
public interface IClient extends Remote {
	void show(String message) throws RemoteException;
}
