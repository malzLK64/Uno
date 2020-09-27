package Game;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Server.Server;

public class Main {
	
	public static boolean gameRunning, direction;
	
	public static ArrayList<Card> cards = new ArrayList<Card>();
	public static ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<Card> cardStack = new ArrayList<Card>();

    Scanner userInput = new Scanner(System.in);
	
	static Random rand = new Random();

	public static int numPlayers = 0;
	
	
	private void start() {
		int playerTurn = 0;
		
		playerTurn = rand.nextInt(numPlayers);
		while(gameRunning) {
			playerMove(playerTurn);
			playerTurn = nextPlayerTurn(playerTurn);
			for (int j = 0; j < numPlayers; j++) {
				if(players.get(j).size() == 0) {
					System.out.println("Player " + j + " won the game!");
					gameRunning = false;
				}
			}
		}
	}
	
	void playerMove(int playerTurn) {
        
        System.out.println("Player " + (playerTurn +1) + "'s Turn \n Top card: " + Stack.topCard());
        
		for (int i = 0; i < players.get(playerTurn).size(); i++) {
			System.out.println("[" + (i+1) + "] " + players.get(playerTurn).getCardNum(i) + " " + players.get(playerTurn).getCardColor(i));
		}
		
		System.out.println("Choose a Card or x to pick up a card:");
        while(!userInput.hasNext());
        String input = userInput.next();

        //if (userInput.hasNext()) input = userInput.nextLine();
        
        if(input.equals("x")) {
    		int r = rand.nextInt(cards.size()) -1;
			players.get(playerTurn).takeCard(cards.get(r));
			cards.remove(r);
        }
        
        if (!input.equals("") && !input.equals("x")) {
        	try {
            	int num = Integer.parseInt(input);
            	if(num <= (players.get(playerTurn).size()+1)) {
            		Stack.layDownCard(players.get(playerTurn).layDownCard(num -1), playerTurn);
            		players.get(playerTurn).remove(num -1);
            	}else {
            		System.out.println("Enter valid value");
            		playerMove(playerTurn);
            	}
            } catch (NumberFormatException | NullPointerException nfe) {
            	System.out.println("Enter valid value");
        		playerMove(playerTurn);
            }
        } 
        userInput.reset();
	}
	
	public static int nextPlayerTurn(int currentPlayer) {
		if(direction) {
			 return (currentPlayer += 1) % numPlayers;
		}else {
			currentPlayer -= 1;
			if(currentPlayer < 0) return numPlayers -1;
		}
		return currentPlayer;
	}
	
	public static void penaltyCards(int player, int num) {
			for (int i = 0; i < num; i++) {
				int r = rand.nextInt(cards.size()) -1;
				players.get(player).takeCard(cards.get(r));
				cards.remove(r);
			}
	}
	
	private void handOut() {
		for(int i = 0; i < numPlayers; i++) {
			players.add(new Player(i));
			for (int j = 0; j < 7; j++) {
				int r = rand.nextInt(cards.size());
				players.get(i).takeCard(cards.get(r));
				cards.remove(r);
			}			
		}		
		firstCard();
	}
	
	private void firstCard() {
		int r = rand.nextInt(cards.size()) -1;
		if(cards.get(r).getState() == "Nummber") {
			Stack.firstCard(cards.get(r));
			cards.remove(r);
		}else firstCard();
	}
	
	public static void main(String [] args) {
		Main main = new Main();
		CreateCards.create();
		main.handOut();
		main.gameRunning = true;
		main.start();
	}
}
