package Rennspiel1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Rennspiel {

    private static JLabel Auto; // Car
    private static JButton start;
    private static JButton pause;
    private static JLabel Strecke;
    static String Spielstatus = "POS";
    static boolean schneller = false;
    static Timer timer;
    static int baseSpeed = 10;
    static int currentSpeed = baseSpeed;

    static JButton titel;

    static int maxSpeed = 25;

    public static void main(String[] args) {

        // JFrame titel = new JFrame();
        //titel.setTitle("Wilkommen zu Streetracer");
        //titel.setSize(100, 100);
        //titel.setLocation(0, 0);
        //titel.setResizable(true);
        //titel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //titel.setLayout(null);

        // Fenster erzeugen
        JFrame frm = new JFrame();
        frm.setTitle("Rennspiel");
        frm.setSize(1920, 1080);
        frm.setLocation(0, 0);
        frm.setResizable(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(null);

        // Add a MouseMotionListener to track mouse movement
        frm.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Get the x and y coordinates of the mouse
                int x = e.getX();
                int y = e.getY();

                // Print the x and y coordinates to the console
                System.out.println("Mouse Position: X = " + x + ", Y = " + y);
            }
        });

        //Start Button
        start = new JButton("Start");
        start.setFocusable(false);
        start.setBounds(180,20,100,50);
        start.setOpaque(true);
        frm.add(start);
        start.addActionListener(e -> Spielstatus = "RUN");

        //Pause Button
        pause = new JButton("Pause");
        pause.setOpaque(true);
        pause.setFocusable(false);
        pause.setBounds(60,20,100,50);
        frm.add(pause);
        pause.addActionListener(e -> Spielstatus = "POS");
        System.out.println(Spielstatus);
        BufferedImage autoimage;
        try {
            autoimage = ImageIO.read(new File("Z:\\IT(s)\\Intellig\\HelloWorld\\src\\Rennspiel1\\spr_formula_0.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Auto = new JLabel(new ImageIcon((Image) autoimage));
        Auto.setBounds(323, 305, autoimage.getWidth(), autoimage.getHeight()); // Initial position
        //uto.setIcon(new ImageIcon("C:\\Users\\jonas\\Desktop\\Development\\java\\general\\JTest\\src\\main\\resources\\car_1.png")); // Add your car image here
        Auto.setOpaque(false);
        frm.add(Auto); // Add car to the frame

        // willkommensfenster
        //JOptionPane.showMessageDialog(null, "Hallo, und willkommen zum Street Racer", "", JOptionPane.INFORMATION_MESSAGE);

        // Background Panel erstellen
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setOpaque(true);
        backgroundPanel.setBounds(0, 0, frm.getWidth(), frm.getHeight());
        frm.add(backgroundPanel); // background panel erzeugen

        // Tastenbedienung
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                int x = Auto.getX();
                int y = Auto.getY();
                if (!(Spielstatus == "RUN"))
                    return;

                switch (code) {
                    case KeyEvent.VK_DOWN:
                        y = y + 125;
                        if (y >= 305 + 125 * 4){
                            return;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        y = y - 125;
                        if (y < 305){
                            return;
                        }
                        break;

                }

                // Update car's position
                Auto.setLocation(x, y);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used
            }
        };
        frm.addKeyListener(keyListener);

        // Timer to update game state
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Spielstatus.equals("RUN")) {
                    frm.repaint();
                }
            }
        });
        timer.start();

        // Key listener for space key to adjust speed
        frm.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && Spielstatus.equals("RUN")) {
                    currentSpeed = baseSpeed + 3;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && Spielstatus.equals("RUN")) {
                    if (!((baseSpeed +2) > maxSpeed)) {
                        baseSpeed += 2;
                        currentSpeed = baseSpeed;
                    }


                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && Spielstatus.equals("RUN")) {
                    if (!((baseSpeed -2) < 10)) {
                        baseSpeed -= 2;
                        currentSpeed = baseSpeed;
                    }

                }


            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && Spielstatus.equals("RUN")) {
                    currentSpeed = baseSpeed;
                }
            }
        });



        // Make the frame visible
        frm.setVisible(true);
    }


    // Custom JPanel for background rendering
    static class BackgroundPanel extends JPanel {
        private ImageIcon backgroundIcon = new ImageIcon("Z:\\IT(s)\\Intellig\\HelloWorld\\src\\Rennspiel1\\Hg.png");
        private int x = 0;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image backgroundImage = backgroundIcon.getImage();

            // Draw the background image twice to create the scrolling effect
            g.drawImage(backgroundImage, x, 0, getWidth(), getHeight(), this);
            g.drawImage(backgroundImage, x + getWidth(), 0, getWidth(), getHeight(), this);

            // Move background left
            x -= currentSpeed;

            // Reset position when background moves off screen
            if (x <= -getWidth()) {
                x = 0;
            }
            //KMH anzeige
            Font font = g.getFont().deriveFont( 50.0f );
            g.setColor(Color.BLACK);
            g.drawString((currentSpeed * 7.5f) +" kmh",200,200);

        }
    }

}