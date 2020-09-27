package Game;
public class Card{
	private String num, color, state;

	public Card(String state, String num, String color){
		this.state = state;
		this.num = num;
		this.color = color;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getNum() {
		return this.num;
	}
	
	public String getColor() {
		return this.color;
	}
	
}
