package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private static ArrayList<ClientHandler> clients;
	
	public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
		this.client = clientSocket;
		this.clients = clients;
		
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
		System.out.println(client);
	}
	
	public static String getClientPort(int i) {
		return clients.get(i).toString();
	}
	
	
	@Override
	public void run() {
		
		try {
			while(true) {
				String request = in.readLine();
				if(request.contains("name")) {

				}
			}	
		}catch(IOException e) {
			System.err.println("IO exception in clienth handler");
		}finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
