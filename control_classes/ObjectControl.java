package control_classes;
import model_classes.*;

import java.lang.Math;

public class ObjectControl {
	
	public ObjectControl() {
		//Add some constructor information
	}
	
	//Need to add:
	/**
	 * movePaddle is a function that will move the paddle in a specific direction determined by keyboard inputs
	 * @param currentPaddle - paddle to be moved
	 * @param direction - direction of movement; determined via keyboard inputs (1 = right, 0 = left)
	 */
	public void movePaddle(Paddle currentPaddle, int direction) {
		//TODO: move paddle is a function that will move the paddle according to the inputs
		int newX, newY;
		int paddleSpeed = (int)currentPaddle.getPaddleSpeed();
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

		for (int i = 2; i < 3; i++) {
			if (token == 1) {
				//Move player 1 paddle
				if (direction == 1) {
					// Move to the right
					if (curPos.getY() == boundPos.getY()) {
						// Horizontal
						if (curPos.getX() + paddleSpeed <= boundPos.getX()) {
							// Get new x coordinates
							newX = curPos.getX() + paddleSpeed;
							newY = boundPos.getY();
						} else {
							newX = boundPos.getX();
							newY = boundPos.getY() - ((curPos.getX() + paddleSpeed) - boundPos.getX());
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
						if ((curPos.getY() + paddleSpeed) <= boundPos.getY()) {
							// Paddle moving down
							newX = boundPos.getX();
							newY = curPos.getY() + paddleSpeed;
						} else {
							// Paddle is moving down and across
							newX = boundPos.getX() - ((curPos.getY() + paddleSpeed) - boundPos.getY());
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
			}

			// Set the newY and newX coordinates and switch to old coordinates
			currentPaddle.setPreviousPos(currentPaddle.getCurrentPos());
			currentPaddle.getCurrentPos().setX(newX);
			currentPaddle.getCurrentPos().setY(newY);
		}
	}
	
	//MoveBall:
	/**
	 * moveBall is a function that will move the ball when called. Direction of ball movement is dependent on the previous direction
	 * and the angle as well as the speed of the ball
	 * @param currentBall - the ball to be moved
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

		// Check if there was a collision
		if(finalPoint != null) {//IF INTERSECTS WORKS BUT THEN BALL WONT COLLIDE (line is from centre)

			//Since collision has occured, account for it by updating current and previous ball coordinates

			if ((newX > finalPoint.getX()) && (wallLeft == true)) { //Left wall collision
				newX = finalPoint.getX() - (newX - finalPoint.getX());
				updatePrevX = newX + xDel;
			} else if ((newX < finalPoint.getX()) && (wallRight == true)) { //Right wall collision
				newX = finalPoint.getX() + (finalPoint.getX() - newX);
				updatePrevX = newX - xDel;
			}

			if ((newY > finalPoint.getY()) && (wallTop == true)) { //Top wall collision
				newY = finalPoint.getY() - (newY - finalPoint.getY());
				updatePrevY = newY + yDel;
			} else if ((newY < finalPoint.getY()) && (wallBottom == true)) { //Bottom wall collision
				newY = finalPoint.getY() + (finalPoint.getY() - newY);
				updatePrevY = newY - yDel;
			}

			//Update previous coordinates
			currentBall.getPreviousPos().setX((int)updatePrevX);
			currentBall.getPreviousPos().setY((int)updatePrevY);

			// Set the new point with the new x and y coordinate
			currentBall.getCurrentPos().setX((int)Math.round(newX));
			currentBall.getCurrentPos().setY((int)Math.round(newY));
		} else {

			//Object collision hasnt occured so check boundary collision
			// Then account for it by updating current and previous ball coordinates
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

			//Update previous coordinates
			currentBall.getPreviousPos().setX((int)updatePrevX);
			currentBall.getPreviousPos().setY((int)updatePrevY);

			// Set the new point with the new x and y coordinate
			currentBall.getCurrentPos().setX((int)Math.round(newX));
			currentBall.getCurrentPos().setY((int)Math.round(newY));

		}

	}
}
