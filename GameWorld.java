import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by tommy_000 on 5/4/2015.
 */
public class GameWorld {
    private int howOften;
    private int step;
    private GamePanel panel;
    public static ArrayList<Body> bodies;
    private javax.swing.Timer timer;
    private static boolean isAlive;
    public static int numSharksKilled;

    public GameWorld(int howOften, GamePanel panel) {
        this.howOften = howOften;
        this.panel = panel;
        bodies = new ArrayList<Body>();
        setUp();
        step = 0;
        isAlive = true;
        numSharksKilled = 0;
    }

    public void begin() {
        Trogdor trog = new Trogdor(300, 300);
        bodies.add(trog);
        timer = new javax.swing.Timer((int) howOften, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
                step();
            }
        });
        timer.start();
    }

    public void step() {
        step++;
        checkBounds();
        if (isAlive) {
            for (int i = 0; i < bodies.size(); i++) {
                bodies.get(i).act();
            }
            if (step % 50 == 0) {
                sendRandomShark();
            } if (step % 100 == 0) {
               sendRandomGasCan();
            }
            checkCollisions();
        } else {
            if (step % 50 == 0) {
                bodies.add(new Body((int)(Math.random() * 700) + 50, (int)(Math.random() * 500) + 50, "haha-you-lost2.jpg"));
            } if (step == 250) {
                timer.stop();
                for (int i = 0; i < bodies.size(); i++) {
                    bodies.remove(i);
                }
                Driver.switchToStartMenu();
            }
        }
    }

    public void sendRandomShark() {
        Shark shark;
        if (Math.random() < 0.5) {
            int x = -100;
            int y = (int)(Math.random() * 600);
            shark = new Shark(x, y, true);
            shark.setXVelocity(20);
            shark.setYVelocity((int) (Math.random() * 10) - 5);
        } else {
            int x = 900;
            int y = (int)(Math.random() * 600);
            shark = new Shark(x, y, false);
            shark.setXVelocity(-20);
            shark.setYVelocity((int)(Math.random() * 10) - 5);
        }
        bodies.add(shark);
    }

    public void sendRandomGasCan() {
        GasCan gasCan;
        int x = (int)(Math.random() * 750) + 50;
        int y = (int)(Math.random() * 550) + 50;
        if (Math.random() > .5) {
            gasCan = new GasCan(x, y, true);
        } else {
            gasCan = new GasCan(x, y, false);
        }
        bodies.add(gasCan);
    }

    private void checkCollisions() {
        int bodiesSize = bodies.size();
        for (int i = 1; i < bodiesSize; i++) {
            Rectangle trogdorBounds = bodies.get(0).getBounds();
            Rectangle otherBounds = bodies.get(i).getBounds();
            if (trogdorBounds.intersects(otherBounds)) {
                Trogdor trogdor = (Trogdor)(bodies.get(0));
                if (bodies.get(i) instanceof Shark) {
                    Shark shark = (Shark)(bodies.get(i));
                    if (trogdor.getFireState() && shark.isAlive() && trogdor.getDirection() == trogdor.isFacing(shark)) {
                        shark.kill();
                        numSharksKilled++;
                    } else {
                        bodies.remove(i);
                        trogdor.hurt();
                        bodiesSize--;
                        if (trogdor.getHealth() <= 0) {
                            this.end();
                            bodiesSize = 0;
                        }
                    }
                } else if (bodies.get(i) instanceof GasCan) {
                    GasCan gasCan = (GasCan)(bodies.get(i));
                    bodies.remove(i);
                    bodiesSize--;
                    trogdor.addFire(20);
                }
            }
        }
        for (int i = 1; i < bodiesSize; i++) {
            for (int j = 2; j < bodiesSize; j++) {
                Rectangle firstBounds = bodies.get(i).getBounds();
                Rectangle secondBounds = bodies.get(j).getBounds();
                if (i != j && firstBounds.intersects(secondBounds)) {
                    if (bodies.get(i) instanceof Shark && bodies.get(j) instanceof GasCan) {
                        Shark shark = (Shark)(bodies.get(i));
                        shark.kill();
                        bodies.remove(j);
                        bodiesSize--;
                    } else if (bodies.get(i) instanceof GasCan && bodies.get(j) instanceof Shark) {
                        Shark shark = (Shark)(bodies.get(j));
                        shark.kill();
                        bodies.remove(i);
                        bodiesSize--;
                    }
                }
            }
        }
    }

    public void checkBounds() {
        for (int i = 1; i < bodies.size(); i++) {
            Body body = bodies.get(i);
            if (body.getX() < -200 || body.getX() > 1000) {
                bodies.remove(i);
            }
        }
    }

    public void end() {
        isAlive = false;
        for (int i = 0; i < bodies.size(); i++) {
            bodies.remove(i);
        }
        step = 0;
        bodies.add(new Body(250, 375, "haha-you-lost1.jpg"));
    }

    private void setUp() {
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isAlive) {
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        bodies.get(0).setYVelocity(5);
                    } else if (e.getKeyCode() == KeyEvent.VK_A) {
                        bodies.get(0).setXVelocity(-5);
                    } else if (e.getKeyCode() == KeyEvent.VK_S) {
                        bodies.get(0).setYVelocity(-5);
                    } else if (e.getKeyCode() == KeyEvent.VK_D) {
                        bodies.get(0).setXVelocity(5);
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        Trogdor trog = (Trogdor) (bodies.get(0));
                        trog.setFireOn(true);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (isAlive) {
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                        bodies.get(0).setYVelocity(0);
                    } else if (e.getKeyCode() == KeyEvent.VK_A) {
                        bodies.get(0).setXVelocity(0);
                    } else if (e.getKeyCode() == KeyEvent.VK_S) {
                        bodies.get(0).setYVelocity(0);
                    } else if (e.getKeyCode() == KeyEvent.VK_D) {
                        bodies.get(0).setXVelocity(0);
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        Trogdor trog = (Trogdor) (bodies.get(0));
                        trog.setFireOn(false);
                    }
                }
            }
        };
        Driver.frame.addKeyListener(listener);
    }
}
