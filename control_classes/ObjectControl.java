package control_classes;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model_classes.*;
import view_classes.RenderView;

import java.util.ArrayList;
import java.util.Random;

import static model_classes.PowerUps.Power.FREEZE;

public class ObjectControl {

	private boolean firstLoop;
	private Image imgP1VertDefault = new Image("/images/paddleV1.png");
	private Image imgP1HoriDefault = new Image("/images/paddleH1.png");
	private Image imgP2VertDefault = new Image("/images/paddleV2.png");
	private Image imgP2HoriDefault = new Image("/images/paddleH2.png");
	private Image imgP3VertDefault = new Image("/images/paddleV3.png");
	private Image imgP3HoriDefault = new Image("/images/paddleH3.png");
	private Image imgP4VertDefault = new Image("/images/paddleV4.png");
	private Image imgP4HoriDefault = new Image("/images/paddleH4.png");
	private Image imgShrinkP1Vert = new Image("/images/shrinkPaddleV1.png");
	private Image imgShrinkP1Hori = new Image("/images/shrinkPaddleH1.png");
	private Image imgShrinkP2Vert = new Image("/images/shrinkPaddleV2.png");
	private Image imgShrinkP2Hori = new Image("/images/shrinkPaddleH2.png");
	private Image imgShrinkP3Vert = new Image("/images/shrinkPaddleV3.png");
	private Image imgShrinkP3Hori = new Image("/images/shrinkPaddleH3.png");
	private Image imgShrinkP4Vert = new Image("/images/shrinkPaddleV4.png");
	private Image imgShrinkP4Hori = new Image("/images/shrinkPaddleH4.png");
	private ArrayList<gameObject> paddleArray;

	public ObjectControl(ArrayList<gameObject> paddleArray) {
		//Add some constructor information
		this.firstLoop = true;
		this.paddleArray = paddleArray;
	}

	public ObjectControl() {
		//Add some constructor information
		this.firstLoop = true;
	}
	
	//Need to add:
	/**
	 * movePaddle is a function that will move the paddle in a specific direction determined by keyboard inputs
	 * @param currentPaddle - paddle to be moved
	 * @param direction - direction of movement; determined via keyboard inputs (1 = right, 0 = left)
	 */
	private boolean movePaddle(Paddle currentPaddle, int direction) {
		//TODO: move paddle is a function that will move the paddle according to the inputs
		int newX, newY;
		int paddleSpeed = (int)currentPaddle.getPaddleSpeed();
		int paddleLength = (int)currentPaddle.getLength();
		int paddleHeight = (int)currentPaddle.getHeight();
		Point curPos = currentPaddle.getCurrentPos();
		Point startPos = currentPaddle.getPaddleStart();
		Point endPos = currentPaddle.getPaddleEnd();
		Point boundPos = currentPaddle.getPaddleBounds();
		int token = currentPaddle.getPaddleToken();

		// Determine if the paddle is in between;
		//	1. start and middle
		//	2. middle and end
		//	3. at middle

		// Specifying at least one sustained position
		newX = curPos.getX();
		newY = curPos.getY();

		if (token == 1) {
			//Move player 1 paddle
			if (direction == 1) {
				// Move to the right
				if (curPos.getY() == boundPos.getY()) {
					// Horizontal
					if (curPos.getX() + paddleSpeed + paddleLength <= boundPos.getX()) {
						// Get new x coordinates
						newX = curPos.getX() + paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = boundPos.getX();
						newY = boundPos.getY() - paddleLength + paddleHeight;
					}
				} else {
					// Vertical
					if ((curPos.getY() - paddleSpeed) > endPos.getY()) {
						newX = boundPos.getX();
						newY = curPos.getY() - paddleSpeed;
					} else {
						newX = boundPos.getX();
						newY = endPos.getY();
					}
				}
			} else {
				// Moving to the left
				if (curPos.getX() == boundPos.getX()) {
					//Vertical
					if ((curPos.getY() + paddleSpeed + paddleLength) <= boundPos.getY()) {
						// Paddle moving down
						newX = boundPos.getX();
						newY = curPos.getY() + paddleSpeed;
					} else {
						// Paddle is moving down and across
						newX = boundPos.getX() - paddleLength + paddleHeight;
						newY = boundPos.getY();
					}
				} else {
					// Horizontal
					if (curPos.getX() - paddleSpeed >= startPos.getX()) {
						newX = curPos.getX() - paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = startPos.getX();
						newY = boundPos.getY();
					}
				}
			}
		} else if (token == 2) {
			//Move player 2 paddle
			if (direction == 1) {
				// Move to the right
				if (curPos.getY() == boundPos.getY()) {
					// Horizontal
					if (curPos.getX() + paddleSpeed <= endPos.getX()) {
						newX = curPos.getX() + paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = endPos.getX();
						newY = boundPos.getY();
					}
				} else {
					// Vertical
					if ((curPos.getY() + paddleSpeed + paddleLength) >= endPos.getY()) {
						newX = boundPos.getX() + paddleSpeed - (boundPos.getY() - (curPos.getY() + paddleLength));
						newY = boundPos.getY();
					} else {
						newX = boundPos.getX();
						newY = curPos.getY() + paddleSpeed;
					}
				}
			} else {
				// Moving to the left
				if (curPos.getX() == boundPos.getX()) {
					//Vertical
					if ((curPos.getY() - paddleSpeed) <= startPos.getY()) {
						newX = boundPos.getX();
						newY = startPos.getY();
					} else {
						newX = boundPos.getX();
						newY = curPos.getY() - paddleSpeed;
					}
				} else {
					// Horizontal
					if ((curPos.getX() - paddleSpeed) <= startPos.getX()) {
						newX = boundPos.getX();
						newY = boundPos.getY() - paddleLength + paddleHeight;
					} else {
						newX = curPos.getX() - paddleSpeed;
						newY = boundPos.getY();
					}
				}
			}
		} else if (token == 3) {
			//Move player 2 paddle
			if (direction == 1) {
				// Move to the right
				if ((curPos.getY() == boundPos.getY()) && (curPos.getX() != boundPos.getX())) {
					// Horizontal
					if (curPos.getX() + paddleSpeed + paddleLength < boundPos.getX()) {
						newX = curPos.getX() + paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = endPos.getX();
						newY = boundPos.getY() + paddleSpeed - (boundPos.getX() - (curPos.getX() + paddleLength));
					}
				} else {
					// Vertical
					if ((curPos.getY() + paddleSpeed) >= endPos.getY()) {
						newX = boundPos.getX();
						newY = endPos.getY();
					} else {
						newX = boundPos.getX();
						newY = curPos.getY() + paddleSpeed;
					}
				}
			} else {
				// Moving to the left
				if (curPos.getX() == boundPos.getX()) {
					//Vertical
					if ((curPos.getY() - paddleSpeed) <= boundPos.getY()) {
						newX = boundPos.getX() - (curPos.getY() - boundPos.getY()) - paddleLength;
						newY = boundPos.getY();
					} else {
						newX = boundPos.getX();
						newY = curPos.getY() - paddleSpeed;
					}
				} else {
					// Horizontal
					if (curPos.getX() - paddleSpeed >= startPos.getX()) {
						newX = curPos.getX() - paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = startPos.getX();
						newY = boundPos.getY();
					}
				}
			}
		} else if (token == 4) {
			//Move player 2 paddle
			if (direction == 1) {
				// Move to the right
				if (curPos.getY() == boundPos.getY()) {
					// Horizontal
					if (curPos.getX() + paddleSpeed <= endPos.getX()) {
						newX = curPos.getX() + paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = endPos.getX();
						newY = boundPos.getY();
					}
				} else {
					// Vertical
					if ((curPos.getY() - paddleSpeed) >= boundPos.getY()) {
						newX = boundPos.getX();
						newY = curPos.getY() - paddleSpeed;
					} else {
						newX = boundPos.getX() + (curPos.getY() - boundPos.getY());
						newY = boundPos.getY();
					}
				}
			} else {
				// Moving to the left
				if (curPos.getX() == boundPos.getX()) {
					//Vertical
					if ((curPos.getY() + paddleSpeed) >= startPos.getY()) {
						newX = boundPos.getX();
						newY = startPos.getY();
					} else {
						newX = boundPos.getX();
						newY = curPos.getY() + paddleSpeed;
					}
				} else {
					// Horizontal
					if (curPos.getX() - paddleSpeed >= boundPos.getX()) {
						newX = curPos.getX() - paddleSpeed;
						newY = boundPos.getY();
					} else {
						newX = boundPos.getX();
						newY = boundPos.getY() + (curPos.getX() - boundPos.getX());
					}
				}
			}
		}

		// Set the newY and newX coordinates and switch to old coordinates
		currentPaddle.setPreviousPos(currentPaddle.getCurrentPos());
		currentPaddle.getCurrentPos().setX(newX);
		currentPaddle.getCurrentPos().setY(newY);

		if (newX == boundPos.getX() && newY == boundPos.getY()) {
			return false;
		} else if (newX == boundPos.getX()) {
			return false;
		} else {
			return true;
		}
	}
	

	/**
	 * moveBall is a function that will move the ball when called. Direction of ball movement is dependent on the previous direction
	 * and the angle as well as the speed of the ball
	 * @param currentBall - the ball to be moved; ball
	 */
	public void moveBall(Ball currentBall, CollisionStruct inp) {
		//TODO: change so angle can be changed randomly


		//Declare and get variables from checkCollision function
		double newX = inp.getNewValues()[0], newY = inp.getNewValues()[1];
		double updatePrevX = currentBall.getCurrentPos().getX(), updatePrevY = currentBall.getCurrentPos().getY();
		boolean[] wallArray = inp.getWallHit();
		boolean wallLeft = wallArray[0], wallRight = wallArray[1], wallTop = wallArray[2], wallBottom = wallArray[3];
		Point finalPoint = inp.getFinalPoint();
		double xDel = Math.abs(currentBall.getPreviousPos().getX() - updatePrevX), yDel = Math.abs(currentBall.getPreviousPos().getY() - updatePrevY);
		double ballRadius = currentBall.getBallRadius();

		// Check if there was a collision
		if (finalPoint != null) {//IF INTERSECTS WORKS BUT THEN BALL WONT COLLIDE (line is from centre)
			//Since collision has occured, account for it by updating current and previous ball coordinates

			if ((newX > finalPoint.getX()) && (wallLeft == true)) { //Left wall collision
				newX = finalPoint.getX();
				updatePrevX = newX + xDel;

			} else if ((newX < finalPoint.getX()) && (wallRight == true)) { //Right wall collision
				newX = finalPoint.getX();
				updatePrevX = newX - xDel;
			}

			if ((newY > finalPoint.getY()) && (wallTop == true)) { //Top wall collision
				newY = finalPoint.getY();
				updatePrevY = newY + yDel;
			} else if ((newY < finalPoint.getY()) && (wallBottom == true)) { //Bottom wall collision
				newY = finalPoint.getY();
				updatePrevY = newY - yDel;
			}

			//Update previous coordinates
			currentBall.getPreviousPos().setX((int) updatePrevX);
			currentBall.getPreviousPos().setY((int) updatePrevY);

			// Set the new point with the new x and y coordinate
			currentBall.getCurrentPos().setX((int) Math.round(newX));
			currentBall.getCurrentPos().setY((int) Math.round(newY));

			currentBall.setMoved(true);
		}

	}

	/**
	 * moveInBounds will move the bll in the boudns if it leave it
	 * @param currentBall - the current ball in the game; Ball
	 * @param col - the collision of the ball; Collision
	 */
	public void moveInBounds(Ball currentBall, Collision col) {
			//Object collision hasn't occurred so check boundary collision
			// Then account for it by updating current and previous ball coordinates
		Point check = col.getDels((Ball) currentBall.getObj());

		//Get new and current positions
		double newX = check.getX();
		double newY = check.getY();
		double updatePrevX = ((Ball) currentBall.getObj()).getCurrentPos().getX();
		double updatePrevY = ((Ball) currentBall.getObj()).getCurrentPos().getY();

		double xDel = Math.abs(currentBall.getCurrentPos().getX() - newX);
		double yDel = Math.abs(currentBall.getCurrentPos().getY() - newY);

			if (newX > 1024) { //Right boundary
				newX = 1024 - (newX - 1024);
				updatePrevX = newX + xDel;
			} else if (newX < 0) { //Left boundary
				newX = Math.abs(newX);
				updatePrevX = newX - xDel;
			}

			if (newY > 768) { //Bottom boundary
				newY = 768 - (newY - 768);
				updatePrevY = newY + yDel;
			} else if (newY < 0) { //Top boundary
				newY = Math.abs(newY);
				updatePrevY = newY - yDel;
			}

		if(yDel == 0) {
			updatePrevY += 2;
		}

		if(xDel == 0) {
			updatePrevX += 2;
		}

			//Update previous coordinates
			currentBall.getPreviousPos().setX((int)updatePrevX);
			currentBall.getPreviousPos().setY((int)updatePrevY);

			// Set the new point with the new x and y coordinate
			currentBall.getCurrentPos().setX((int)Math.round(newX));
			currentBall.getCurrentPos().setY((int)Math.round(newY));


	}

	/**
	 * moveAllPaddles will render and control when the paddles are moved
	 * @param render - the render view of the game; RenderView
	 * @param HandleIO - the IOHandle of the game; IOHandle
	 * @param SetUpGame - the GameSetUp of the game; GameSetUp
	 */
	public void moveAllPaddles(RenderView render, IOHandle HandleIO, GameSetUp SetUpGame) {
		// Ghosts
		Image imgP1Vert = imgP1VertDefault;
		Image imgP1Hori = imgP1HoriDefault;
		Image imgP2Vert = imgP2VertDefault;
		Image imgP2Hori = imgP2HoriDefault;
		Image imgP3Vert = imgP3VertDefault;
		Image imgP3Hori = imgP3HoriDefault;
		Image imgP4Vert = imgP4VertDefault;
		Image imgP4Hori = imgP4HoriDefault;
		Image imgGhostRight = new Image("/images/GhostPaddleRight.png");
		Image imgGhostLeft = new Image("/images/GhostPaddleLeft.png");
		Image imgGhostUp = new Image("/images/GhostPaddleUp.png");
		Image imgGhostDown = new Image("/images/GhostPaddleDown.png");
		Image imgFreezeRight = new Image("/images/freezePaddleRight.png");
		Image imgFreezeLeft = new Image("/images/freezePaddleLeft.png");
		Image imgFreezeUp = new Image("/images/freezePaddleUp.png");
		Image imgFreezeDown = new Image("/images/freezePaddleDown.png");
		boolean isInvis = false;

		// Determine if the object is alive
		if (!SetUpGame.getPlayer1().isAlive()) {
			imgP1Hori = imgGhostDown;
			imgP1Vert = imgGhostRight;
		} else if (SetUpGame.getPlayer1().getPlayerPaddle().hasPowerUp()){
			// Determine the power up and associated picture
			switch (SetUpGame.getPlayer1().getPlayerPaddle().getPower().getPower()) {
				case FREEZE: 	imgP1Hori = imgFreezeDown;
								imgP1Vert = imgFreezeRight;
								break;
				case SHRINK:	imgP1Vert = imgShrinkP1Vert;
								imgP1Hori = imgShrinkP1Hori;
								break;
				case INVIS:		isInvis = true;
								break;
			}
		}

		if (!SetUpGame.getPlayer2().isAlive()) {
			imgP2Hori = imgGhostDown;
			imgP2Vert = imgGhostLeft;
		} else if (SetUpGame.getPlayer2().getPlayerPaddle().hasPowerUp()){
			// Determine the power up and associated picture
			switch (SetUpGame.getPlayer2().getPlayerPaddle().getPower().getPower()) {
				case FREEZE: 	imgP2Hori = imgFreezeDown;
								imgP2Vert = imgFreezeLeft;
								break;
				case SHRINK:	imgP2Vert = imgShrinkP2Vert;
								imgP2Hori = imgShrinkP2Hori;
								break;
				case INVIS:		isInvis = true;
								break;
			}
		}
		if (!SetUpGame.getPlayer3().isAlive()) {
			imgP3Hori = imgGhostUp;
			imgP3Vert = imgGhostRight;
		} else if (SetUpGame.getPlayer3().getPlayerPaddle().hasPowerUp()){
			// Determine the power up and associated picture
			switch (SetUpGame.getPlayer3().getPlayerPaddle().getPower().getPower()) {
				case FREEZE: 	imgP3Hori = imgFreezeUp;
								imgP3Vert = imgFreezeRight;
								break;
				case SHRINK:	imgP3Vert = imgShrinkP3Vert;
								imgP3Hori = imgShrinkP3Hori;
								break;
				case INVIS:		isInvis = true;
								break;
			}
		}
		if (!SetUpGame.getPlayer4().isAlive()) {
			imgP4Hori = imgGhostUp;
			imgP4Vert = imgGhostLeft;
		} else if (SetUpGame.getPlayer4().getPlayerPaddle().hasPowerUp()){
			// Determine the power up and associated picture
			switch (SetUpGame.getPlayer4().getPlayerPaddle().getPower().getPower()) {
				case FREEZE: 	imgP4Hori = imgFreezeUp;
								imgP4Vert = imgFreezeLeft;
								break;
				case SHRINK:	imgP4Vert = imgShrinkP4Vert;
								imgP4Hori = imgShrinkP4Hori;
								break;
				case INVIS:		isInvis = true;
								break;
			}
		}

		if (HandleIO.hasMovedLeftP1()) {
			if (movePaddle(SetUpGame.getPlayer1().getPlayerPaddle(), 0)) {
				// Is not horizontal
				SetUpGame.getPlayer1().getPlayerPaddle().setRotated(false);
				render.getP1Render().setHeight(SetUpGame.getPlayer1().getPlayerPaddle().getHeight());
				render.getP1Render().setWidth(SetUpGame.getPlayer1().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP1Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP1Render().setFill(new ImagePattern(imgP1Hori));
				}
			} else {
				SetUpGame.getPlayer1().getPlayerPaddle().setRotated(true);
				render.getP1Render().setHeight(SetUpGame.getPlayer1().getPlayerPaddle().getLength());
				render.getP1Render().setWidth(SetUpGame.getPlayer1().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP1Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP1Render().setFill(new ImagePattern(imgP1Vert));
				}
			}
		}
		if (HandleIO.hasMovedRightP1()) {
			if (movePaddle(SetUpGame.getPlayer1().getPlayerPaddle(), 1)) {
				// Is not horizontal
				SetUpGame.getPlayer1().getPlayerPaddle().setRotated(false);
				render.getP1Render().setHeight(SetUpGame.getPlayer1().getPlayerPaddle().getHeight());
				render.getP1Render().setWidth(SetUpGame.getPlayer1().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP1Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP1Render().setFill(new ImagePattern(imgP1Hori));
				}
			} else {
				SetUpGame.getPlayer1().getPlayerPaddle().setRotated(true);
				render.getP1Render().setHeight(SetUpGame.getPlayer1().getPlayerPaddle().getLength());
				render.getP1Render().setWidth(SetUpGame.getPlayer1().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP1Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP1Render().setFill(new ImagePattern(imgP1Vert));
				}
			}
		}


		if (HandleIO.hasMovedLeftP2()) {
			if (movePaddle(SetUpGame.getPlayer2().getPlayerPaddle(), 0)) {
				// Is not horizontal
				SetUpGame.getPlayer2().getPlayerPaddle().setRotated(false);
				render.getP2Render().setHeight(SetUpGame.getPlayer2().getPlayerPaddle().getHeight());
				render.getP2Render().setWidth(SetUpGame.getPlayer2().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP2Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP2Render().setFill(new ImagePattern(imgP2Hori));
				}
			} else {
				SetUpGame.getPlayer2().getPlayerPaddle().setRotated(true);
				render.getP2Render().setHeight(SetUpGame.getPlayer2().getPlayerPaddle().getLength());
				render.getP2Render().setWidth(SetUpGame.getPlayer2().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP2Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP2Render().setFill(new ImagePattern(imgP2Vert));
				}
			}
		}
		if (HandleIO.hasMovedRightP2()) {
			if (movePaddle(SetUpGame.getPlayer2().getPlayerPaddle(), 1)) {
				// Is not horizontal
				SetUpGame.getPlayer2().getPlayerPaddle().setRotated(false);
				render.getP2Render().setHeight(SetUpGame.getPlayer2().getPlayerPaddle().getHeight());
				render.getP2Render().setWidth(SetUpGame.getPlayer2().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP2Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP2Render().setFill(new ImagePattern(imgP2Hori));
				}
			} else {
				SetUpGame.getPlayer2().getPlayerPaddle().setRotated(true);
				render.getP2Render().setHeight(SetUpGame.getPlayer2().getPlayerPaddle().getLength());
				render.getP2Render().setWidth(SetUpGame.getPlayer2().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP2Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP2Render().setFill(new ImagePattern(imgP2Vert));
				}
			}
		}


		if (HandleIO.hasMovedLeftP3()) {
			if (movePaddle(SetUpGame.getPlayer3().getPlayerPaddle(), 0)) {
				// Is not horizontal
				SetUpGame.getPlayer3().getPlayerPaddle().setRotated(false);
				render.getP3Render().setHeight(SetUpGame.getPlayer3().getPlayerPaddle().getHeight());
				render.getP3Render().setWidth(SetUpGame.getPlayer3().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP3Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP3Render().setFill(new ImagePattern(imgP3Hori));
				}
			} else {
				SetUpGame.getPlayer3().getPlayerPaddle().setRotated(true);
				render.getP3Render().setHeight(SetUpGame.getPlayer3().getPlayerPaddle().getLength());
				render.getP3Render().setWidth(SetUpGame.getPlayer3().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP3Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP3Render().setFill(new ImagePattern(imgP3Vert));
				}
			}
		}
		if (HandleIO.hasMovedRightP3()) {
			if (movePaddle(SetUpGame.getPlayer3().getPlayerPaddle(), 1)) {
				// Is not horizontal
				SetUpGame.getPlayer3().getPlayerPaddle().setRotated(false);
				render.getP3Render().setHeight(SetUpGame.getPlayer3().getPlayerPaddle().getHeight());
				render.getP3Render().setWidth(SetUpGame.getPlayer3().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP3Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP3Render().setFill(new ImagePattern(imgP3Hori));
				}
			} else {
				SetUpGame.getPlayer3().getPlayerPaddle().setRotated(true);
				render.getP3Render().setHeight(SetUpGame.getPlayer3().getPlayerPaddle().getLength());
				render.getP3Render().setWidth(SetUpGame.getPlayer3().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP3Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP3Render().setFill(new ImagePattern(imgP3Vert));
				}
			}
		}


		if (HandleIO.hasMovedLeftP4()) {
			if (movePaddle(SetUpGame.getPlayer4().getPlayerPaddle(), 0)) {
				// Is not horizontal
				SetUpGame.getPlayer4().getPlayerPaddle().setRotated(false);
				render.getP4Render().setHeight(SetUpGame.getPlayer4().getPlayerPaddle().getHeight());
				render.getP4Render().setWidth(SetUpGame.getPlayer4().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP4Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP4Render().setFill(new ImagePattern(imgP4Hori));
				}
			} else {
				SetUpGame.getPlayer4().getPlayerPaddle().setRotated(true);
				render.getP4Render().setHeight(SetUpGame.getPlayer4().getPlayerPaddle().getLength());
				render.getP4Render().setWidth(SetUpGame.getPlayer4().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP4Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP4Render().setFill(new ImagePattern(imgP4Vert));
				}
			}
		}
		if (HandleIO.hasMovedRightP4()) {
			if (movePaddle(SetUpGame.getPlayer4().getPlayerPaddle(), 1)) {
				// Is not horizontal
				SetUpGame.getPlayer4().getPlayerPaddle().setRotated(false);
				render.getP4Render().setHeight(SetUpGame.getPlayer4().getPlayerPaddle().getHeight());
				render.getP4Render().setWidth(SetUpGame.getPlayer4().getPlayerPaddle().getLength());
				if (isInvis) {
					render.getP4Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP4Render().setFill(new ImagePattern(imgP4Hori));
				}
			} else {
				SetUpGame.getPlayer4().getPlayerPaddle().setRotated(true);
				render.getP4Render().setHeight(SetUpGame.getPlayer4().getPlayerPaddle().getLength());
				render.getP4Render().setWidth(SetUpGame.getPlayer4().getPlayerPaddle().getHeight());
				if (isInvis) {
					render.getP4Render().setFill(Color.TRANSPARENT);
				} else {
					render.getP4Render().setFill(new ImagePattern(imgP4Vert));
				}
			}
		}
	}

	/**
	 * playerDeaths will remove all the remaining bricks once the player warlord is destroyed
	 * @param obj - the obejcts in the game; gameobject
	 * @param root - the group of the game; Group
	 * @param gameArray - the gameArray containing all objects; Arraylist
	 * @param pos - the postion in the game; pos
	 */
	public void playerDeaths(gameObject obj, Group root, ArrayList<gameObject> gameArray, int pos) {
		if(obj.getObj() instanceof Player) {
			if(!((Player) obj.getObj()).isAlive()) {

				//Get player walls and bricks
				Wall wall = ((Player) obj.getObj()).getPlayerWall();
				int token = ((Player) obj.getObj()).getPlayerPaddle().getPaddleToken();
				ArrayList<Brick> brickList = wall.getBricks();

				//Loop through Brick array and then remove each brick
				for(Brick temp:brickList) {
					if(!temp.isRemoved()) {
						root.getChildren().remove(temp.getSprite());
						temp.setRemoved(true);
						this.removeBrick(gameArray,temp);
					}
				}

				Image imgDeadPlayer;

				switch (token) {
					case 1:
						imgDeadPlayer = new Image("/images/player1SpriteDead.png");
						break;
					case 2:
						imgDeadPlayer = new Image("/images/player2SpriteDead.png");
						break;
					case 3:
						imgDeadPlayer = new Image("/images/player3SpriteDead.png");
						break;
					case 4:
						imgDeadPlayer = new Image("/images/player4SpriteDead.png");
						break;
					default:
						imgDeadPlayer = new Image("/images/player1SpriteDead.png");
						break;
				}
				//Change Player sprite colour
				obj.getShape().setFill(new ImagePattern(imgDeadPlayer));
			}
		}
	}

	/**
	 * removeBrick will remove the remaining bricks from the game
	 * @param gameArray - the game array of the object; arraylist
	 * @param temp - the temporary brick; Brick
	 */
	private void removeBrick (ArrayList<gameObject> gameArray, Brick temp) {
		for(int i = 0; i < gameArray.size(); i++) {
			//Check to see if object is of Brick type
			if(gameArray.get(i).getObj() instanceof Brick) {
				Brick currentBrick = (Brick) gameArray.get(i).getObj();
				if(currentBrick.getID() == temp.getID()) {
					gameArray.remove(i);
					return;
				}
			}
		}
	}

	//Add powerup to game

	/**
	 * addPowerUp adds powers to the game
	 * @param root - the group o the game; Group
	 * @param gameArray - the game array of object; arraylist
	 * @param timer - a timer; double
	 * @param render - the renderview of the game; RenderView
	 */
	public void addPowerUp (Group root, ArrayList<gameObject> gameArray, double timer, RenderView render) {

		if((118 < timer) &&  (timer < 119) && firstLoop) {

			Random rand = new Random();
			int minX = 360, maxX = 634;
			int minY = 250, maxY = 478;
			int x = rand.nextInt((maxX - minX) + 1) + minX;
			int y = rand.nextInt((maxY - minY) + 1) + minY;

			int powerUpWidth = 30;
			int powerUpHeight= 30;

			int randomPower = rand.nextInt( 3) + 1;

			PowerUps.Power powerToAdd = FREEZE;

			switch(randomPower) {
				case 1:
					powerToAdd = FREEZE;
					break;
				case 2:
					powerToAdd = PowerUps.Power.SHRINK;
					break;
				case 3:
					powerToAdd = PowerUps.Power.INVIS;
					break;
			}

			PowerUps pUp = new PowerUps(powerUpWidth, powerUpHeight, powerToAdd, 10);

			pUp.setCurrentPos(new Point(x, y));


			Rectangle pRect = render.getPURender(powerToAdd);
			pRect.setLayoutX(x);
			pRect.setLayoutY(y);

			System.out.println(powerToAdd);

			root.getChildren().add(pRect);

			gameObject newPowerUp = new gameObject(pRect, pUp);
			gameArray.add(newPowerUp);

			firstLoop = false;
		}
	}

	//Reset powerUp effect

	/**
	 * checkAndRemovePowerUps will check and remove the power ups
	 * @param players - the players in the game
	 */
	public void checkAndRemovePowerUps(ArrayList<Player> players) {

		//Loop through each player's paddle
		for (int i = 0; i < players.size(); i++) {
			Paddle tempPaddle = players.get(i).getPlayerPaddle();

			//Check if other players are effected by the powerup
			if(tempPaddle.hasPowerUp()) {
				//If they do, check the powerup duration
				if(tempPaddle.getPower().getDuration() > 0) {
					double diff = (System.nanoTime() - tempPaddle.getPower().getTimeOfPU()) * Math.pow(10, -9);
					tempPaddle.getPower().setDuration(  tempPaddle.getPower().getDuration() - diff );
					tempPaddle.getPower().setTimeOfPU(System.nanoTime());

				} else {
					if (tempPaddle.getPower().getPower() == FREEZE) {
						tempPaddle.setPaddleSpeed(15);
					} else if (tempPaddle.getPower().getPower() == PowerUps.Power.SHRINK) {

						for(int a = 0; a < paddleArray.size(); a++) {
							if(tempPaddle.getID() == ((Paddle) paddleArray.get(a).getObj()).getID()) {
								gameObject objPaddle =  paddleArray.get(a);

								((Paddle) objPaddle.getObj()).setPaddleSize(80);
							}
						}

					} else if (tempPaddle.getPower().getPower() == PowerUps.Power.INVIS) {

					}

					tempPaddle.setPowerUp(false);
					tempPaddle.setPower(null);

				}
			}
		}
	}
}
