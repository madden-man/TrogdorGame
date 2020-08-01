import java.awt.event.*;

/**
 * Created by tommy_000 on 5/15/2015.
 */
public class StartWorld {
    private int howOften;
    public static StartPanel panel;
    public static javax.swing.Timer timer;

    public StartWorld(int howOften, StartPanel panel) {
        this.howOften = howOften;
        this.panel = panel;
        setUp();
    }

    public void begin() {
        timer = new javax.swing.Timer(howOften, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });
        timer.start();
    }

    private void setUp() {
        Driver.frame.add(panel);
        Driver.frame.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 350 && e.getX() < 450 && e.getY() > 400 && e.getY() < 500) {
                    timer.stop();
                    Driver.switchToGame();
                }
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
