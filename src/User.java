import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class User implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = null;
        char[] password = null;

        while (true) {
            id = JOptionPane.showInputDialog(null, "Enter ID:");
            if (id == null || id.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            } else {
                break;
            }
        }

        while (true) {
            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                password = passwordField.getPassword();
                if (password == null || password.length == 0) {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
                } else {
                    break;
                }
            } else {
                return;
            }
        }

        String passwordString = new String(password);
        JOptionPane.showMessageDialog(null, "ID: " + id.trim() + "\nPassword: " + "*".repeat(password.length), "User Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new User().actionPerformed(null);
        });
    }
}
