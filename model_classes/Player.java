package model_classes;

public class Player extends modelSuperClass {

	private int playerScore;
	private String playerName;	
	private Wall playerWall;
	private Paddle playerPaddle;
	private Point playerPosition;
	private double height;
	private double length;
	private boolean isAlive;
	
	public Player(String name, Point position, double length, double height) {
		this.playerScore = 0;
		this.playerName = name;
		this.playerPosition = position;
		this.length = length;
		this.height = height;
		this.isAlive = true;
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
	
	public Wall getPlayerWall() {
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

	@Override
	public Point getCurrentPos() {
		return playerPosition;
	}


	@Override
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
