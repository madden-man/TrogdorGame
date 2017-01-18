import javax.swing.*;
import java.awt.*;

/**
 * Created by tommy_000 on 5/4/2015.
 */
public class Body {
    protected int[] position;
    protected double[] velocity;
    protected double[] acceleration;
    protected final int X = 0;
    protected final int Y = 1;
    protected Image image;
    protected String name;

    public Body(int x, int y, String path) {
        position = new int[2];
        velocity = new double[2];
        acceleration = new double[2];
        position[X] = x;
        position[Y] = y;
        velocity[X] = 0;
        velocity[Y] = 0;
        acceleration[X]=0;
        acceleration[Y]=0;
        image = new ImageIcon(path).getImage();
//        setName("Body");
    }

    public void act() {
        velocity[X] += acceleration[X];
        velocity[Y] += acceleration[Y];
        position[X] += velocity[X];         // -= because top left is the origin of the frame, not the bottom left
        position[Y] -= velocity[Y];         // += because top left is the origin of the frame, not the bottom left
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) { position[X] = x;}

    public int getX() { return position[X]; }

    public void setY(int y) { position[Y] = y;}

    public int[] getPosition() { return position; }

    public void setPosition(int xPosition, int yPosition) {
        position[X] = xPosition;
        position[Y] = yPosition;
    }

    public double[] getVelocity() { return velocity; }

    public void setVelocity(double xVelocity, double yVelocity) {
        velocity[X] = xVelocity;
        velocity[Y] = yVelocity;
    }

    public void setXVelocity(double xVelocity) {
        velocity[X] = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        velocity[Y] = yVelocity;
    }

    public double[] getAcceleration() { return acceleration; }

    public void setAcceleration(double xAcceleration, double yAcceleration) {
        acceleration[X] = xAcceleration;
        acceleration[Y] = yAcceleration;
    }

    public Image getImg() {
        return image;
    }

    public void setImg(Image img) {
        image = img;
    }

    public Rectangle getBounds() {
        return new Rectangle(position[X], position[Y], image.getWidth(null), image.getHeight(null));
    }
}