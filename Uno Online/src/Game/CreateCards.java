package Game;
public class CreateCards extends Main{
	
	static String[] colors = {"Red", "Green", "Blue", "Yellow"};
	static String[] state = {"Nummber", "+2", "Suspend", "Return" , "Color change" , "+4 color change"};
	static String currentColor;
	
	public static void create() {

		for(int i = 1; i <= 4; i++) {
			if(i <= 1) currentColor = colors[0];
			if(i >= 2) currentColor = colors[1];
			if(i >= 3) currentColor = colors[2];
			if(i >= 4) currentColor = colors[3];
			for(int j = 0; j < 2; j++) {
				for(int k = 1; k <= 9; k++) {
					cards.add(new Card(state[0], String.valueOf(k), currentColor));
				}
			}
			cards.add(new Card(state[0],"0", currentColor));
			
			for (int l = 0; l < 2; l++) {
				cards.add(new Card(state[1], state[1], currentColor));
				cards.add(new Card(state[2], state[2], currentColor));
				cards.add(new Card(state[3], state[3], currentColor));
			}
		}
		
		for (int j = 0; j < 4; j++) {
			cards.add(new Card(state[4], state[4], null));
			cards.add(new Card(state[5], state[5], null));
		}
		
		
	}
}
