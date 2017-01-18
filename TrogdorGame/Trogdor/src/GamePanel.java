import javax.swing.*;
import java.awt.*;

/**
 * Created by tommy_000 on 5/4/2015.
 */
public class GamePanel extends JPanel {
    public GamePanel() {
    }

    public void updateStatusBars(Graphics g) {
        Body fireBar;
        Trogdor trogdor = (Trogdor)(GameWorld.bodies.get(0));
        if (trogdor.getFire() >= 100) {
            fireBar = new Body(0, 0, "fire-100.jpg");
        } else if (trogdor.getFire() >= 90) {
            fireBar = new Body(0, 0, "fire-90.jpg");
        } else if (trogdor.getFire() >= 80) {
            fireBar = new Body(0, 0, "fire-80.jpg");
        } else if (trogdor.getFire() >= 70) {
            fireBar = new Body(0, 0, "fire-70.jpg");
        } else if (trogdor.getFire() >= 60) {
            fireBar = new Body(0, 0, "fire-60.jpg");
        } else if (trogdor.getFire() >= 50) {
            fireBar = new Body(0, 0, "fire-50.jpg");
        } else if (trogdor.getFire() >= 40) {
            fireBar = new Body(0, 0, "fire-40.jpg");
        } else if (trogdor.getFire() >= 30) {
            fireBar = new Body(0, 0, "fire-30.jpg");
        } else if (trogdor.getFire() >= 20) {
            fireBar = new Body(0, 0, "fire-20.jpg");
        } else if (trogdor.getFire() >= 10) {
            fireBar = new Body(0, 0, "fire-10.jpg");
        } else if (trogdor.getFire() >= 0) {
            fireBar = new Body(0, 0, "fire-0.jpg");
        } else {
            fireBar = new Body(0, 0, "fire-100.jpg");
        }
        g.drawImage(fireBar.getImg(), fireBar.getPosition()[0], fireBar.getPosition()[1], null);
        Body healthBar;
        if (trogdor.getHealth() >= 100) {
            healthBar = new Body(550, 0, "health-100.jpg");
        } else if (trogdor.getHealth() >= 90) {
            healthBar = new Body(550, 0, "health-90.jpg");
        } else if (trogdor.getHealth() >= 80) {
            healthBar = new Body(550, 0, "health-80.jpg");
        } else if (trogdor.getHealth() >= 70) {
            healthBar = new Body(550, 0, "health-70.jpg");
        } else if (trogdor.getHealth() >= 60) {
            healthBar = new Body(550, 0, "health-60.jpg");
        } else if (trogdor.getHealth() >= 50) {
            healthBar = new Body(550, 0, "health-50.jpg");
        } else if (trogdor.getHealth() >= 40) {
            healthBar = new Body(550, 0, "health-40.jpg");
        } else if (trogdor.getHealth() >= 30) {
            healthBar = new Body(550, 0, "health-30.jpg");
        } else if (trogdor.getHealth() >= 20) {
            healthBar = new Body(550, 0, "health-20.jpg");
        } else if (trogdor.getHealth() >= 10) {
            healthBar = new Body(550, 0, "health-10.jpg");
        } else if (trogdor.getHealth() >= 0) {
            healthBar = new Body(550, 0, "health-0.jpg");
        } else {
            healthBar = new Body(550, 0, "health-100.jpg");
        }
        g.drawImage(healthBar.getImg(), healthBar.getPosition()[0], healthBar.getPosition()[1], null);
        updateSharkCounter(g);
    }

    public void updateSharkCounter(Graphics g) {
        Body sharksKilled = new Body(300, 0, "sharks-killed.jpg");
        g.drawImage(sharksKilled.getImg(), sharksKilled.getPosition()[0], sharksKilled.getPosition()[1], null);
        int numSharksKilled = GameWorld.numSharksKilled;
        if (numSharksKilled < 10) {
            String path = numSharksKilled + ".jpg";
            g.drawImage(new Body(0, 0, path).getImg(), 462, 3, null);
        } else {
            int tens = numSharksKilled / 10;
            int ones = numSharksKilled % 10;
            String tensPath = tens + ".jpg";
            String onesPath = ones + ".jpg";
            g.drawImage(new Body(0, 0, onesPath).getImg(), 472, 3, null);
            g.drawImage(new Body(0, 0, tensPath).getImg(), 455, 3, null);
        }
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 800, 600);
        for (int i = 0; i < GameWorld.bodies.size(); i++) {
            Body body = GameWorld.bodies.get(i);
            g.drawImage(body.getImg(), body.getPosition()[0], body.getPosition()[1], null);
        }
        if (GameWorld.bodies.get(0) instanceof Trogdor) {
            updateStatusBars(g);
        }
    }
}