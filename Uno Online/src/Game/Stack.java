package Game;
import java.util.ArrayList;
import java.util.Scanner;

public class Stack {
	private static ArrayList<Card> cards = new ArrayList<Card>();
	
	public void takeCard(Card card) {
		this.cards.add(card);
	}
	
	public static void layDownCard(Card card, int currentPlayer) {
		Main main = new Main();
		if(card.getState() == "Nummber") {
			if(card.getColor() == cards.get(cards.size() -1).getColor() || card.getNum() == cards.get(cards.size()-1).getNum()) cards.add(card);
			else main.playerMove(currentPlayer);
		}else if(card.getState() == "Return" || card.getColor() == cards.get(cards.size() -1).getColor()) {
			if(Main.direction == true) Main.direction = false;
			if(Main.direction == false) Main.direction = true;
		}else if(card.getState() == "+2" || card.getColor() == cards.get(cards.size() -1).getColor()) {
			Main.penaltyCards(Main.nextPlayerTurn(currentPlayer), 2);
		}else if(card.getState() == "+4 color change") {
			Main.penaltyCards(Main.nextPlayerTurn(currentPlayer), 4);	        
			cards.add(new Card("Nummber", "", newColor()));
		}else if(card.getState() == "Color change") {
			cards.add(new Card("Nummber", "", newColor()));
		}else if(card.getState() == "Suspend") {
			main.playerMove(main.nextPlayerTurn(currentPlayer));
		}
	}
	
	private static String newColor() {
		System.out.println("Choose a color: ");
		
		Scanner userInput = new Scanner(System.in);
        String input = "";
        while(!userInput.hasNext());
        if (userInput.hasNext()) input = userInput.nextLine();
		
		if(input.equals("Blue") || input.equals("blue")) {
			return "Blue";
		}else if(input.equals("Red") || input.equals("red")) {
			return "Red";
		}else if(input.equals("Yellow") || input.equals("yellow")) {
			return "Yellow";
		}else if(input.equals("Green") || input.equals("green")){
      		return "Green";
		}else {
			System.out.println("Enter valid value [Blue, Green, Yellow, Red]");
		}
		return null;
	}

	public static void firstCard(Card card) {
		cards.add(card);
	}
	
	public static String topCard() {
		return cards.get(cards.size() -1).getNum() + " " + cards.get(cards.size()-1).getColor();
	}
}
