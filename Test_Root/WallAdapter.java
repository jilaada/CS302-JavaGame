package Test_Root;

/**
 * Created by Jilada on 1/04/17.
 */
public class WallAdapter implements IWall {

    private int xPos, yPos;
    private boolean destroyedWall;

    public WallAdapter() {
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

    public int getYPos() {
        return this.yPos;
    }

    public int getXPos() {
        return this.xPos;
    }

    public void setDestroyedWall(boolean des) {
        this.destroyedWall = des;
    }

    @Override
    public boolean isDestroyed() {
        return destroyedWall;
    }
}
