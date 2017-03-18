package model_classes;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Type in a number:");
		int x = input.nextInt();
		Player p1 = new Player(x);
		
		p1.setInt(3);
		x = p1.getInt();
		System.out.println("Back in main and in class - number is now: " + x);
	}

}
