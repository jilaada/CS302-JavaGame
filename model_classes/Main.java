package model_classes;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Player p1 = new Player("Joe");
		
		p1.setPlayerScore(3);
		System.out.println(p1.getPlayerName());
		System.out.println(p1.getPlayerScore());
		
		//Paddle myPaddle = new Paddle(1.0, 2);
		Wall myWall = new Wall(50);
		Ball myBall = new Ball(3, 4);
		
		//p1.addPlayerPaddle(myPaddle);
		p1.addPlayerWall(myWall);
		
		/*int start = 0;
		for(int i = 0; i<5; i++) {
			Brick newBrick = new Brick(2,4);
			this.add(newBrick, 0, start);
			start = start + 5;
		}*/
		
		

		System.out.println(p1.getPlayerPaddle());
		
	}

}
