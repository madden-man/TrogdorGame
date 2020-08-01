/**
 * Created by tommy_000 on 5/14/2015.
 */
public class LivingBeing extends Body {
    protected boolean[] attributes;

    public LivingBeing(int x, int y, boolean facingRight, String path) {
        super(x, y, path);
        attributes = new boolean[2];
        attributes[0] = facingRight;           // boolean facingRight = true
        attributes[1] = true;                  // boolean isAlive = true
    }

    @Override
    public void setXVelocity(double xVelocity) {
        velocity[X] = xVelocity;
        if (velocity[X] < 0 && attributes[0]) {
            attributes[0] = false;
        } if (velocity[X] > 0 && !attributes[0]) {
            attributes[0] = true;
        }
    }

    public boolean isAlive() {
        return attributes[1];
    }

    public void kill() {
        attributes[1] = false;
    }

    public boolean getDirection() {
        return attributes[0];
    }

    public boolean isFacing(LivingBeing being) {
        if (being.getX() - getX() > 0) {
            return true;
        } else {
            return false;
        }
    }
}