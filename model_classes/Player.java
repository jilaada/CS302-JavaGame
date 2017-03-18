package model_classes;

public class Player {

	private int playerScore;
	private String playerName;	
	private Wall playerWall;
	private Paddle playerPaddle;
	
	public Player(String name) {
		this.playerScore = 0;
		this.playerName = name;
		
		//Object constructors
		this.playerWall = 0;
		this.playerPaddle = 0;
	}
	
	public void setPlayerScore(int score) {
		this.playerScore = score;	
	}
	
	public int getPlayerScore() {
		return this.playerScore;
	}
	
	protected void setPlayerName(String name) {
		this.playerName = name;	
	}
	
	protected String setPlayerName() {
		return this.playerName;
	}
	
	//Object implementation
	protected void setPlayerWall(Wall barrier) {
		this.playerWall = barrier;	
	}
	
	protected Wall getPlayerWall() {
		return this.playerWall;
	}
	
	protected void setPlayerPaddle(Paddle blocker) {
		this.playerPaddle = blocker;	
	}
	
	protected Paddle getPlayerPaddle() {
		return this.playerPaddle;
	}
}
