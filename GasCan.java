import javax.swing.*;

/**
 * Created by tommy_000 on 6/4/2015.
 */
public class GasCan extends Body {
    public GasCan(int x, int y, boolean facingLeft) {
        super(x, y, "assets/gas-can_facing-left.jpg");
        if (!facingLeft) {
            setImg(new ImageIcon(this.getClass().getResource("assets/gas-can_facing-right.jpg")).getImage());
        }
    }
}
