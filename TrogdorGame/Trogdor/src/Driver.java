import javax.swing.*;
import java.awt.*;

/**
 * Created by tommy_000 on 5/4/2015.
 */
public class Driver {
    public static JFrame frame;

    // For JAR and Desktop Run Configurations
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.setUpFrame();
        Driver.switchToStartMenu();
    }

    /* For Applet Configuration
    public void init() {

    }
     */

    public static void switchToGame() {
        frame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.revalidate();
        GameWorld gameWorld = new GameWorld(25, gamePanel);
        gameWorld.begin();
    }

    public static void switchToStartMenu() {
        frame.getContentPane().removeAll();
        StartPanel startPanel = new StartPanel();
        frame.add(startPanel);
        frame.revalidate();
        StartWorld startWorld = new StartWorld(20, startPanel);
        startWorld.begin();
    }

    private void setUpFrame() {
        frame = new JFrame("TROGDOR!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setName("TROGDOR!");
        frame.setSize(new Dimension(800, 600));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width / 2) - (frame.getWidth() / 2), (dim.height / 2) - frame.getHeight() / 2);      // Set in the middle of the screen    }
        frame.repaint();
    }
}