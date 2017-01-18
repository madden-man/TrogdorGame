import javax.swing.*;

/**
 * Created by tommy_000 on 5/8/2015.
 */
public class Shark extends LivingBeing {
    public Shark(int x, int y, boolean facingRight) {
        super(x, y, facingRight, "shark_facing-right.jpg");
        updateImage();
        setName("Shark");
    }

    public void updateImage() {
        String ans = "shark_";
        if (attributes[0] == true) {
            ans += "facing-right_";
        } else {
            ans += "facing-left_";
        } if (isAlive()) {
            ans += "flame-off.jpg";
        } else {
            ans += "flame-on.jpg";
        }
        setImg(new ImageIcon(ans).getImage());
    }

    public void kill() {
        super.kill();
        setXVelocity(-getVelocity()[X]);
        updateImage();
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
}