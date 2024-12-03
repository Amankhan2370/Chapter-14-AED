package edu.neu.mgen;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginSignupGUI {
    private JFrame frame = new JFrame("Login/Signup");
    private HashMap<String, String> userCredentials = new HashMap<>();

    public LoginSignupGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(e -> {
            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            if (userCredentials.containsKey(user) && userCredentials.get(user).equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                frame.dispose(); // Close login window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials!");
            }
        });
        panel.add(loginButton);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(180, 80, 80, 25);
        signupButton.addActionListener(e -> {
            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            if (!userCredentials.containsKey(user)) {
                userCredentials.put(user, password);
                JOptionPane.showMessageDialog(null, "Signup successful!");
            } else {
                JOptionPane.showMessageDialog(null, "User already exists!");
            }
        });
        panel.add(signupButton);
    }

    public static void main(String[] args) {
        new LoginSignupGUI();
    }
}
