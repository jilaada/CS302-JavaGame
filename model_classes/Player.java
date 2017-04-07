package model_classes;

public class Player {

	private int playerScore;
	private String playerName;	
	private Wall playerWall;
	private Paddle playerPaddle;
	private Point playerPosition;
	
	public Player(String name, Point position) {
		this.playerScore = 0;
		this.playerName = name;
		this.playerPosition = position;
		
		//Object constructors
		//this.playerWall = 0;
		//this.playerPaddle = 0;
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
	
	protected String getPlayerName() {
		return this.playerName;
	}
	
	//Object implementation
	public void addPlayerWall(Wall barrier) {
		this.playerWall = barrier;
	}
	
	protected Wall getPlayerWall() {
		if(this.playerWall == null) {
			return null;
		} else {
			return this.playerWall;
		}
	}
	
	public void addPlayerPaddle(Paddle blocker) {
		this.playerPaddle = blocker;
	}
	
	public Paddle getPlayerPaddle() {
		if(this.playerPaddle == null) {
			return null;
		} else {
			return this.playerPaddle;
		}
	}

	public int getXPos() {
		return playerPosition.getX();
	}

	public int getYPos() {
		return playerPosition.getY();
	}

}
