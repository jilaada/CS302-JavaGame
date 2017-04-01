package model_classes;

public class Player {

	// Declaring the player attributes
	private int playerScore;
	private String playerName;	
	private Wall playerWall;
	private Paddle playerPaddle;

	/**
	 * player constructor that sets the player parameters
	 * @param name - the name of the player; string
	 */
	public Player(String name) {
		this.playerScore = 0;
		this.playerName = name;
		
		//TODO: Object constructors implemented later
		//this.playerWall = 0;
		//this.playerPaddle = 0;
	}

	/**
	 * setPlayerScore will set the score of the player. This is the number of bricks they have destroyed
	 * @param score - a score related to the player; int
	 */
	public void setPlayerScore(int score) {
		this.playerScore = score;
	}

	/**
	 * getPlayerScore will get the score of the player
	 * @return the score of the player; int
	 */
	public int getPlayerScore() {
		return this.playerScore;
	}

	/**
	 * setPlayerName will set the name of the player
	 * @param name - name of the player; string
	 */
	protected void setPlayerName(String name) {
		this.playerName = name;
	}

	/**
	 * getPlayerName will get the name of player
	 * @return the name of the player; string
	 */
	protected String getPlayerName() {
		return this.playerName;
	}

	//TODO: add commenting
	//Object implementation
	protected void addPlayerWall(Wall barrier) {
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
	
	protected Paddle getPlayerPaddle() {
		if(this.playerPaddle == null) {
			return null;
		} else {
			return this.playerPaddle;
		}
	}

}
