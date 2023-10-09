import javax.swing.*;

public class RockPaperScissorsRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RockPaperScissorsFrame().setVisible(true);
            }
        });
    }
}

