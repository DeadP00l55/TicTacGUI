import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI {
    private static char[][] board = new char[3][3];
    private static JLabel[][] labels = new JLabel[3][3];
    private static JLabel statusLabel;
    static boolean gameOver = false;
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font font = new Font("SansSerif", Font.BOLD, 50);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                labels[i][j] = new JLabel(" ", JLabel.CENTER);
                labels[i][j].setFont(font);
                labels[i][j].setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());
                labels[i][j].addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent e) {
                	    JLabel label = (JLabel) e.getSource();
                	    int row = Integer.parseInt(label.getName().substring(0, 1));
                	    int col = Integer.parseInt(label.getName().substring(1, 2));
                	    if (board[row][col] == '\u0000' && !gameOver) {
                	        label.setText(Character.toString(currentPlayer));
                	        board[row][col] = currentPlayer;
                	        if (checkWin(currentPlayer)) {
                	            setStatus("Player " + currentPlayer + " wins!");
                	            gameOver = true;
                	        } else if (checkDraw()) {
                	            setStatus("It's a draw!");
                	            gameOver = true;
                	        } else {
                	            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                	            setStatus("Player " + currentPlayer + "'s turn");
                	        }
                	    }
                	}
                });
                labels[i][j].setName(Integer.toString(i) + Integer.toString(j));
                panel.add(labels[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        panel.add(new JLabel());
        panel.add(statusLabel);
        panel.add(new JLabel());

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void setStatus(String message) {
        statusLabel.setText(message);
    }

    public static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    public static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }
}

