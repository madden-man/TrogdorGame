import javax.swing.*;
import java.awt.*;

/**
 * Created by tommy_000 on 5/14/2015.
 */
public class StartPanel extends JPanel {
    public StartPanel() {
        super();
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 800, 600);

        g.drawImage(new ImageIcon(this.getClass().getResource("assets/background.jpg")).getImage(), 0, 0, 800, 600, null);
        g.drawImage(new ImageIcon(this.getClass().getResource("assets/button.jpg")).getImage(), 350, 400, null);
    }
}
