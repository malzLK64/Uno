package Game;
import java.util.ArrayList;

public class Player{
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int num;
	
	public Player(int num) {
		this.num = num;
	}
	
	public void takeCard(Card card) {
		cards.add(card);
	}
	
	public Card layDownCard(int num) {
		return cards.get(num);
	}
	
	public int getNum() {
		return num;
	}
	
	public String getCardNum(int i) {
		return cards.get(i).getNum();
	}
	
	public String getCardColor(int i) {
		return cards.get(i).getColor();
	}
	
	public String getCardState(int i) {
		return cards.get(i).getState();
	}

	public void remove(int i) {
		cards.remove(i);
	}
	
	public int size() {
		return cards.size();
	}
}
