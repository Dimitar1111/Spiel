package GUI.Rennspiel1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

public class Rennspiel {

    private static JLabel Auto; // Car
    private static JButton start;
    private static JButton pause;
    private static JLabel Strecke;
    static String Spielstatus = "POS";
    static int x_diff_Auto;
    static int y_diff_Auto;
    static boolean schneller = false;

    public static void main(String[] args) {

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
        start.setBounds(100,20,100,50);
        frm.add(start);
        start.addActionListener(e ->
                {
                    Spielstatus = "RUN";
                });

        //Pause Button
        pause = new JButton("Pause");
        pause.setFocusable(false);
        pause.setBounds(220,20,100,50);
        frm.add(pause);
        pause.addActionListener(e ->
        {
            Spielstatus = "POS";
        });
        System.out.println(Spielstatus);

        // willkommensfenster
        JOptionPane.showMessageDialog(null, "Hallo, und willkommen zum Street Racer", "", JOptionPane.INFORMATION_MESSAGE);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, frm.getWidth(), frm.getHeight());
        frm.add(backgroundPanel); //background panel erzeugen

        // Auto erzeugen
        Auto = new JLabel();
        Auto.setBounds(323, 110, 323, 110); // Initial position
        Auto.setIcon(new ImageIcon("C:\\Users\\Viktor K\\IdeaProjects\\1Project\\src\\GUI\\car (1).png")); // Add your car image here
        frm.add(Auto); // Add car to the frame

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

                switch (code) {
                    case KeyEvent.VK_DOWN:
                        y = y + 10;
                        break;
                    case KeyEvent.VK_UP:
                        y = y - 10;
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

        // Timer to update game state
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Spielstatus.equals("RUN")) {

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
                    timer.setDelay(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && Spielstatus.equals("RUN")) {
                    timer.setDelay(10);
                }
            }
        });

        // Make the frame visible
        frm.setVisible(true);
    }

    // Custom JPanel for background rendering
    static class BackgroundPanel extends JPanel {
        private ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\Viktor K\\IdeaProjects\\1Project\\src\\GUI\\HG (1).jpg");
        private int x = 0;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image backgroundImage = backgroundIcon.getImage();

            // Draw the background image twice to create the scrolling effect
            g.drawImage(backgroundImage, x, 0, getWidth(), getHeight(), this);
            g.drawImage(backgroundImage, x + getWidth(), 0, getWidth(), getHeight(), this);

            // Move background left
            x -= 2;

            // Reset position when background moves off screen
            if (x <= -getWidth()) {
                x = 0;
            }

            repaint(); // Keep the background moving
        }
    }

}
