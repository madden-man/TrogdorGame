import javax.swing.*;

/**
 * Created by tommy_000 on 5/4/2015.
 */
public class Trogdor extends LivingBeing {
    private int health;
    private int fireLeft;
    private boolean fireOn;

    public Trogdor(int x, int y) {
        super(x, y, true, "assets/trogdor_facing-right_fireoff.jpg");
        health = 100;
        fireLeft = 100;
        fireOn = false;
        setName("Trogdor");
    }

    @Override
    public void setXVelocity(double xVelocity) {
        velocity[X] = xVelocity;
        if (velocity[X] < 0 && attributes[0] == true) {
            attributes[0] = false;
            updateImage();
        } if (velocity[X] > 0 && attributes[0] == false) {
            attributes[0] = true;
            updateImage();
        }
    }

    public void setFireOn(boolean fireOn) {
        this.fireOn = fireOn;
        if (fireLeft <= 0 && fireOn == true) {
            this.fireOn = false;
        }
        updateImage();
    }

    public void hurt() {
        int randomAmount = (int)(Math.random() * 20);
        health -= randomAmount;
    }

    public void act() {
        super.act();
        if (fireOn && fireLeft > 0) {
            fireLeft -= 1;
        } else if (fireLeft <= 0) {
            fireOn = false;
        }
    }

    public void updateImage() {
        String ans = "assets/trogdor_";
        if (attributes[0] == true) {
            ans += "facing-right_";
        } else {
            ans += "facing-left_";
        } if (fireOn) {
            ans += "fireon.jpg";
        } else {
            ans += "fireoff.jpg";
        }
        setImg(new ImageIcon(this.getClass().getResource(ans)).getImage());
    }

    public int getHealth() {
        return health;
    }

    public int getFire() {
        return fireLeft;
    }

    public boolean getFireState() { return fireOn; }

    public void addFire(int fire) { fireLeft += fire; }
}
