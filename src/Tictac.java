import javax.swing.*;
import java.awt.*;

public class Tictac extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private boolean isPlayer1Turn = true;

    public Tictac() {
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        panel.setBackground(Color.WHITE);

        // Create buttons and add them to the panel
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setBackground(Color.LIGHT_GRAY);
                buttons[row][col].setFocusPainted(false);
                int finalRow = row;
                int finalCol = col;
                buttons[row][col].addActionListener(e -> buttonClicked(finalRow, finalCol));
                panel.add(buttons[row][col]);
            }
        }

        add(panel);
        setVisible(true);
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().isEmpty()) {
            buttons[row][col].setText(isPlayer1Turn ? "X" : "O");
            checkWinner();
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private void checkWinner() {
        String[][] board = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }
        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) {
                declareWinner(board[i][0]);
                return;
            }
            if (checkLine(board[0][i], board[1][i], board[2][i])) {
                declareWinner(board[0][i]);
                return;
            }
        }
        if (checkLine(board[0][0], board[1][1], board[2][2]) || checkLine(board[0][2], board[1][1], board[2][0])) {
            declareWinner(board[1][1]);
            return;
        }
    }

    private boolean checkLine(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    private void declareWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetGame();
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        isPlayer1Turn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tictac::new);
    }
}
