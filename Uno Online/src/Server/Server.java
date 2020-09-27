package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Game.Main;

public class Server {
	private static final int PORT = 9090;
	
	static boolean waitingForPlayers = true;
	
	public static int name = 0;
	
	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) throws IOException{
    	ServerSocket listener = new ServerSocket(PORT);
    	while(waitingForPlayers) {
	    	System.out.println("[SERVER] Waiting for client connection...");
	    	Socket client = listener.accept();
	    	System.out.println("[SERVER] Connected to client!");
	    	Main.numPlayers += 1;
	    	ClientHandler clientThread = new ClientHandler(client, clients);
	    	clients.add(clientThread);
	    	System.out.println("[CLIENT] Connected as Player " + clients.size() + ".");
	    	pool.execute(clientThread);
	    	if(clients.size() >= 4) waitingForPlayers = false;
    	}	
    }
}
