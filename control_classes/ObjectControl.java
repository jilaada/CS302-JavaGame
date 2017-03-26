package control_classes;
import model_classes.Ball;
import model_classes.Paddle;
import model_classes.Player;
import model_classes.Point;

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

		// Determine if the paddle is in between;
		//	1. start and middle
		//	2. middle and end
		//	3. at middle

		// Specifying at least one sustained position
		newX = curPos.getX();
		newY = curPos.getY();
		if ((curPos.getX() == boundPos.getX()) && (curPos.getY() == boundPos.getY())) {
			// Right in the middle
			// Left to start, right to end
			//TODO: Add to methods to simplify(?)
			if (direction == 1) {
				// Determine if adding or subtracting
				if (endPos.getX() > boundPos.getX()) {
					// Add length to x
					newX = curPos.getX() + paddleSpeed;
				} else if (endPos.getY() > boundPos.getY()) {
					// Add length to y
					newY = curPos.getY() + paddleSpeed;
				} else if (endPos.getY() < boundPos.getY()){
					// Subtract length from y
					newY = curPos.getY() - paddleSpeed;
				}
			} else {
				// To start
				if (startPos.getX() < boundPos.getX()) {
					// Subtract from X
					newX = curPos.getX() - paddleSpeed;
				} else if (startPos.getY() < boundPos.getY()) {
					// Subtract from Y
					newY = curPos.getY() - paddleSpeed;
				} else if (startPos.getY() > boundPos.getY()) {
					// Add to Y
					newY = curPos.getY() + paddleSpeed;
				}
			}
		} else if ((curPos.getX() == startPos.getX()) || (curPos.getY() == startPos.getY())) {
			// Moving between start and middle
			if (direction == 1) {
				// Moving to right
				if (startPos.getX() < boundPos.getX()) {
					// Horizontal movement
					newY = boundPos.getY();
					if (curPos.getX() > (boundPos.getX()-paddleSpeed)) {
						newX = boundPos.getX();
					} else {
						newX = curPos.getX() + paddleSpeed;
					}
				} else if (startPos.getY() < boundPos.getY()) {
					// Vertical movement
					newX = boundPos.getX();
					if (curPos.getY() > (boundPos.getY()-paddleSpeed)) {
						newY = boundPos.getY();
					} else {
						newY = curPos.getY() + paddleSpeed;
					}
				} else if (startPos.getY() > boundPos.getY()) {
					//Vertical movement
					newX = boundPos.getX();
					if (curPos.getY() > (boundPos.getY()+paddleSpeed)) {
						newY = boundPos.getY();
					} else {
						newY = curPos.getY() + paddleSpeed;
					}
				}
			} else {
				// Moving to left
				if (startPos.getX() < boundPos.getX()) {
					// Subtract from x
					newX = curPos.getX() - paddleSpeed;
				} else if (startPos.getY() < boundPos.getY()) {
					// Subtract from y
					newY = curPos.getY() - paddleSpeed;

				} else if (startPos.getY() > boundPos.getY()) {
					// Add to y
					newY = curPos.getY() + paddleSpeed;
				}
			}
		} else if ((curPos.getY() == endPos.getY()) || (curPos.getY() == endPos.getY())) {
			// Moving between middle and end
			if (direction == 1) {
				// Moving to the right
				if (endPos.getY() < boundPos.getY()) {
					// Subtract from y
					newY = curPos.getY() - paddleSpeed;
				} else if (endPos.getY() > boundPos.getY()) {
					// Add to y
					newY = curPos.getY() + paddleSpeed;
				} else if (endPos.getX() > boundPos.getX()) {
					// Add to x
					newX = curPos.getX() + paddleSpeed;
				}
			} else {
				// Moving to the left
				if (endPos.getY() < boundPos.getY()) {
					// Move vertical
					if (curPos.getY() > (boundPos.getY() - paddleSpeed)) {
						// Equals y bounds
						newY = boundPos.getY();
					} else {
						// Add to y
						newY = curPos.getY() + paddleSpeed;
					}
				} else if (endPos.getY() > boundPos.getY()) {
					if (curPos.getY() < (boundPos.getY() + paddleSpeed)) {
						// Equals y bounds
						newY = boundPos.getY();
					} else {
						// Subtract to y
						newY = curPos.getY() - paddleSpeed;
					}
				} else if (endPos.getX() > boundPos.getX()) {
					if (curPos.getX() > (boundPos.getX() + paddleSpeed)) {
						// Equals x bounds
						newX = boundPos.getX();
					} else {
						// Subtract from x
						newX = curPos.getX() - paddleSpeed;
					}
				}
			}
		}

		// Check if the vertical boundaries have been breached
		if (newX < startPos.getX()) {
			newX = startPos.getX();
		} else if (newX < endPos.getX()) {
			newX = endPos.getX();
		}

		// check if the horizontal boundaries have been breached
		if ((startPos.getY() < boundPos.getY()) || (endPos.getY() < boundPos.getY())) {
			if (newY < endPos.getY()) {
				newY = endPos.getY();
			} else if (newY < startPos.getY()) {
				newY = startPos.getY();
			}
		} else if ((startPos.getY() > boundPos.getY()) || (endPos.getY() > boundPos.getY())) {
			if (newY > endPos.getY()) {
				newY = endPos.getY();
			} else if (newY > startPos.getY()) {
				newY = startPos.getY();
			}
		}

		// Set the newY and newX coordinates and switch to old coordinates
		currentPaddle.setPreviousPos(currentPaddle.getCurrentPos());
		currentPaddle.getCurrentPos().setX(newX);
		currentPaddle.getCurrentPos().setY(newY);
	}
	
	//MoveBall:
	/**
	 * moveBall is a function that will move the ball when called. Direction of ball movement is dependent on the previous direction
	 * and the angle as well as the speed of the ball
	 * @param currentBall - the ball to be moved
	 */
	public void moveBall(Ball currentBall) {
		//TODO: moveBall is a function that will move the ball to the next position with respect
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
