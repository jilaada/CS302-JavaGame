package control_classes;
import model_classes.Ball;
import model_classes.Paddle;
import model_classes.Player;

public class ObjectControl {
	
	public ObjectControl() {
		//Add some constructor information
	}
	
	//Need to add:
	//MovePaddle:
	
	//MoveBall:
	public void moveBall(Ball currentBall) {
		//TODO: moveBall is a function that will move the ball to the next position with respect 
		// to the current position and previous position
		double newX, newY;
		// Determine direction X
		if ((currentBall.getPreviousPos().getX() - currentBall.getCurrentPos().getX()) > 0) {
			// Moving to the left
			newX = currentBall.getCurrentPos().getX() - Math.cos(currentBall.getBallAngle())*currentBall.getBallSpeed();
		} else if ((currentBall.getPreviousPos().getX() - currentBall.getCurrentPos().getX()) < 0) {
			// Moving to the right
			newX = Math.cos(currentBall.getBallAngle())*currentBall.getBallSpeed() + currentBall.getCurrentPos().getX();
		} else {
			newX = currentBall.getCurrentPos().getX();
		}
		
		// Determine direction Y
		if ((currentBall.getPreviousPos().getY() - currentBall.getCurrentPos().getY()) > 0) {
			// Moving up
			newY = currentBall.getCurrentPos().getY() - Math.sin(currentBall.getBallAngle())*currentBall.getBallSpeed();
		} else if ((currentBall.getPreviousPos().getY() - currentBall.getCurrentPos().getY()) < 0) {
			newY = Math.sin(currentBall.getBallAngle())*currentBall.getBallSpeed() + currentBall.getCurrentPos().getY();
		} else {
			newY = currentBall.getCurrentPos().getY();
		}
		
		// Check to see if the new position will collide with anything - cannot be through the bounds
		// Change the angle 
		if (newX > 1024) {
			newX = 1024;
		} else if (newX < 0) {
			newX = 0;
		}
		
		if (newY > 768) {
			newY = 768;
		} else if (newY < 0) {
			newY = 0;
		}
		
		// Set the new point with the new x and y coordinate
		currentBall.getNextPos().setX((int)newX);
		currentBall.getNextPos().setY((int)newY);
		
		//
	}
}
