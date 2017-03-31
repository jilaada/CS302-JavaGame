package Test_Root;

/**
 * Created by Jilada on 1/04/17.
 */
public class PaddleAdapter implements IPaddle {

    private int xPos, yPos;

    public PaddleAdapter() {
        this.xPos = 0;
        this.yPos = 0;
    }
    @Override
    public void setXPos(int x) {
        this.xPos = x;
    }

    @Override
    public void setYPos(int y) {
        this.yPos = y;
    }

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
    }
}
