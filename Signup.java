import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Signup {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sign Up");
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton signupButton = new JButton("Sign Up");

        frame.setLayout(new GridLayout(3, 2));
        frame.add(userLabel);
        frame.add(usernameField);
        frame.add(passLabel);
        frame.add(passwordField);
        frame.add(signupButton);

        signupButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            } else {
                try (FileWriter fw = new FileWriter("users.txt", true)) {
                    fw.write(username + ":" + password + "\n");
  
