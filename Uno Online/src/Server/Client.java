package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	  	private static final String SERVER_IP = "127.0.0.1";
		private static final int SERVER_PORT = 9090;

		private static String name;
		
		public static void main(String[] args) throws IOException {
		askForName();
		createClient();
	}

		private static void createClient() throws IOException{
			if(Server.waitingForPlayers == true) {
			    Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		
			    ServerConnection serverConn = new ServerConnection(socket);
			    
				BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
				new Thread(serverConn).start();
				System.out.println("Connection successful!");
				while(true) {
					System.out.println(" > ");
					String command = keyboard.readLine();
					
					if(command.equals("quit")) break;
					out.println(command);
		
					}
				socket.close();
				System.exit(0);
			}else {
				System.out.println("[SERVER] + Could not connect - player maximum reached");
			}			
		}

		private static void askForName() throws IOException {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose a name:");
			name = input.readLine();
		}
}
