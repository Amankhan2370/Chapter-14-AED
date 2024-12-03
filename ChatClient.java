package edu.neu.mgen;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private BufferedReader in;
    private PrintWriter out;
    private JFrame frame = new JFrame("Chat Application");
    private JTextArea messageArea = new JTextArea(20, 40);
    private JTextField inputField = new JTextField(40);

    public ChatClient() {
        messageArea.setEditable(false);
        inputField.addActionListener(e -> {
            out.println(inputField.getText());
            inputField.setText("");
        });
        frame.getContentPane().add(new JScrollPane(messageArea), BorderLayout.CENTER);
        frame.getContentPane().add(inputField, BorderLayout.SOUTH);
        frame.pack();
    }

    private void connect() throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    messageArea.append(message + "\n");
                }
            } catch (IOException e) {
                System.out.println("Connection lost.");
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.connect();
    }
}
