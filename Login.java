import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        frame.setLayout(new GridLayout(3, 2));
        frame.add(userLabel);
        frame.add(usernameField);
        frame.add(passLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
                    String line;
                    boolean found = false;

                    while ((line = br.readLine()) != null) {
                        String[] credentials = line.split(":");
                        if (credentials[0].equals(username) && credentials[1].equals(password)) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        frame.dispose();
                        ChatWindow.startChat(username);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading user data.");
                }
            }
        });

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ChatWindow {
    public static void startChat(String username) {
        JFrame frame = new JFrame("Chat - " + username);
        JTextArea chatArea = new JTextArea();
        JTextField chatInput = new JTextField();
        JButton sendButton = new JButton("Send");

        chatArea.setEditable(false);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(chatInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            String message = chatInput.getText().trim();
            if (!message.isEmpty()) {
                chatArea.append(username + ": " + message + "\n");
                chatInput.setText("");
            }
        });

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
