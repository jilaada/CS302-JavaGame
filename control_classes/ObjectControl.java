package control_classes;
import model_classes.Ball;
import model_classes.Paddle;
import model_classes.Point;

public class ObjectControl {

	/**
	 * ObjectControl constructor that will allow the game to control objects
	 */
	public ObjectControl() {
		//Add some constructor information
		//TODO: Add additional attribute storage
	}
	
	//Need to add:
	/**
	 * movePaddle is a function that will move the paddle in a specific direction determined by keyboard inputs
	 * @param currentPaddle - paddle to be moved; Paddle
	 * @param direction - direction of movement; int determined via keyboard inputs (1 = right, 0 = left)
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
						// Paddle is horizontal
						if (curPos.getX() + paddleSpeed <= boundPos.getX()) {
							newX = curPos.getX() + paddleSpeed;
							newY = boundPos.getY();
						} else {
							newX = boundPos.getX();
							newY = boundPos.getY() - ((curPos.getX() + paddleSpeed) - boundPos.getX());
						}
					} else {
						// Paddle is vertical
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
						// Paddle is vertical
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
						// Paddle is horizontal
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
			//TODO: Implement the paddle movement for player 2, 3 and 4

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
	 * @param currentBall - the ball to be moved; Ball
	 */
	public void moveBall(Ball currentBall) {
		//TODO: change so angle can be changed randomly
		// to the current position and previous position

		Point previousPos = currentBall.getPreviousPos();
		Point currentPos = currentBall.getCurrentPos();
		double ballAngle;
		double ballSpeed = currentBall.getBallSpeed();

		//Find changes in x and y
		double xDel = Math.abs(previousPos.getX() - currentPos.getX());
		double yDel = Math.abs(previousPos.getY() - currentPos.getY());

		double hypot = Math.pow((Math.pow(xDel, 2) + Math.pow(yDel, 2)), 0.5);
		ballAngle = Math.atan(xDel / hypot);


		double newX, newY;
		// Determine direction X
		if ((previousPos.getX() - currentPos.getX()) > 0) {
			// Moving to the left
			newX = currentPos.getX() - Math.cos(ballAngle) * ballSpeed;
		} else if ((previousPos.getX() - currentPos.getX()) < 0) {
			// Moving to the right
			newX = Math.cos(ballAngle) * ballSpeed + currentPos.getX();
		} else {
			newX = currentPos.getX();
		}

		// Determine direction Y
		if ((previousPos.getY() - currentPos.getY()) > 0) {
			// Moving up
			newY = currentPos.getY() - Math.sin(ballAngle) * ballSpeed;
		} else if ((previousPos.getY() - currentPos.getY()) < 0) {
			newY = Math.sin(ballAngle) * ballSpeed + currentPos.getY();
		} else {
			newY = currentPos.getY();
		}

		//Create variables to update previous positions
		double updatePrevX = currentPos.getX();
		double updatePrevY = currentPos.getY();


		// Check to see if the new position will collide with the bounds
		// Update previous position when collision is hit so angle is accurate
		// Change the angle
		if (newX > 1024) {
			newX = 1024 - (newX - 1024);
			updatePrevX = newX + xDel;
		} else if (newX < 0) {
			newX = Math.abs(newX);
			updatePrevX = newX - xDel;
		}

		if (newY > 768) {
			newY = 768 - (newY - 768);
			updatePrevY = newY + yDel;
		} else if (newY < 0) {
			newY = Math.abs(newY);
			updatePrevY = newY - yDel;
		}

		//Update previous coordinates
		previousPos.setX((int)updatePrevX);
		previousPos.setY((int)updatePrevY);

		// Set the new point with the new x and y coordinate
		currentPos.setX((int)Math.round(newX));
		currentPos.setY((int)Math.round(newY));
	}
}
