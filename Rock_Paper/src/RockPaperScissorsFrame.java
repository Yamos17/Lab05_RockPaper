import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private int rounds = 0;

    private JTextArea resultsTextArea;
    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    private JLabel gameStatusLabel;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(1, 3));

        JLabel rockLabel = new JLabel(new ImageIcon("src/rock.png"));
        JLabel paperLabel = new JLabel(new ImageIcon("src/small_paper.png"));
        JLabel scissorsLabel = new JLabel(new ImageIcon("src/small_scissors.png"));

        imagePanel.add(rockLabel);
        imagePanel.add(paperLabel);
        imagePanel.add(scissorsLabel);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(1, 3));

        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton quitButton = new JButton("Quit");

        ActionListener gameButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerChoice = e.getActionCommand();
                String computerChoice = generateComputerChoice();
                String result = determineWinner(playerChoice, computerChoice);
                updateStats(result);
                updateResultsTextArea(result);
                checkGameEnd();
            }
        };

        rockButton.addActionListener(gameButtonListener);
        paperButton.addActionListener(gameButtonListener);
        scissorsButton.addActionListener(gameButtonListener);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gamePanel.add(rockButton);
        gamePanel.add(paperButton);
        gamePanel.add(scissorsButton);
        gamePanel.add(quitButton);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(5, 2));

        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");
        gameStatusLabel = new JLabel("Game Status: Running");

        statsPanel.add(playerWinsLabel);
        statsPanel.add(new JLabel());
        statsPanel.add(computerWinsLabel);
        statsPanel.add(new JLabel());
        statsPanel.add(tiesLabel);
        statsPanel.add(new JLabel());
        statsPanel.add(gameStatusLabel);
        statsPanel.add(new JLabel());

        resultsTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

        setLayout(new BorderLayout());

        add(imagePanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private String generateComputerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();
        return choices[rand.nextInt(choices.length)];
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "Tie";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerWins++;
            return "Player wins";
        } else {
            computerWins++;
            return "Computer wins";
        }
    }

    private void updateStats(String result) {
        if (result.equals("Player wins")) {
            playerWinsLabel.setText("Player Wins: " + playerWins);
        } else if (result.equals("Computer wins")) {
            computerWinsLabel.setText("Computer Wins: " + computerWins);
        } else {
            tiesLabel.setText("Ties: " + ties);
        }
    }

    private void updateResultsTextArea(String result) {
        resultsTextArea.append(result + "\n");
        rounds++;
    }

    private void checkGameEnd() {
        if (rounds >= 10) { 
            String winner;
            if (playerWins > computerWins) {
                winner = "Player";
            } else if (computerWins > playerWins) {
                winner = "Computer";
            } else {
                winner = "Tie";
            }
            gameStatusLabel.setText("Game Over! Winner: " + winner);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RockPaperScissorsFrame().setVisible(true);
            }
        });
    }
}
