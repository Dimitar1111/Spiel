package GUI.Rennspiel1;

import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.*;
 import java.awt.event.ActionListener;

public class Rennspiel {
    private static JLabel HG;
    private static JFrame frm;
    private static JLabel Auto;
    private static JButton start;
    private static JButton pause;
    private static JLabel Strecke;

    static String Spielstatus = "POS";
    static int x_diff_Auto;
    static int y_diff_Auto;
    static boolean schneller = false;

    public static void main(String[] args) {

        // Fenster erzeugen
        frm = new JFrame();
        frm.setTitle("Rennspiel");
        frm.setSize(1920, 1080);
        frm.setLocation(0, 0);
        frm.setResizable(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(null);

        //Fenstepopup
        JOptionPane.showMessageDialog(null, "Hallo, und willkommen zum Street Racer", "", JOptionPane.INFORMATION_MESSAGE);

        //Hintergrund
        //...
        //Auto
        //...
        //Tastenbedienung Pfeiltasten
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

                int code = e.getKeyCode();

                int x = Auto.getX();
                int y = Auto.getY();

                switch (code) {
                    case KeyEvent.VK_DOWN: // VK_KP_DOWN
                        y = y + 10;
                        break;
                    case KeyEvent.VK_UP:
                        y = y - 10;
                        break;
                    default:
                        break;
                }
                frm.setVisible(true);
            }
        };
        //Spielstatus
        //...

            //+Testenbedienung Leertaste
            Timer timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Spielstatus == "RUN") {
                        timer.setDelay(10);
                    }
                }
            });
            timer.start();
            frm.addKeyListener(new

            KeyListener() {
                @Override
                public void keyTyped (KeyEvent e){
                }

                @Override
                public void keyPressed (KeyEvent e){
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && Spielstatus == "RUN") {
                        timer.setDelay(2);
                    }
                }
                @Override
                public void keyReleased (KeyEvent e){
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && Spielstatus == "RUN") {
                        timer.setDelay(10);
                    }
                }
            });
    }
}
