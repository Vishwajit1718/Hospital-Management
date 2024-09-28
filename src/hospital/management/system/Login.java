package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton loginButton, cancelButton;
    JLabel background;
    JPanel loginPanel;

    Login() {
        // Frame settings
        setTitle("Hospital Management System - Login");
        setSize(800, 500);
        setLocationRelativeTo(null); // Centers the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Full-screen background
        background = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon/texture_background.jpg"));
        Image img = imageIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, 800, 500);
        add(background);

        // Centered login form panel
        loginPanel = new JPanel();
        loginPanel.setSize(360, 250);
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(255, 255, 255, 180));  // Semi-transparent background
        loginPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1)); // Black border

        // Dynamically position the login panel in the center
        positionLoginPanel();

        background.add(loginPanel);

        // Username label
        JLabel namelabel = new JLabel("Username:");
        namelabel.setBounds(30, 30, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        namelabel.setForeground(Color.BLACK);
        loginPanel.add(namelabel);

        // Username text field
        textField = new JTextField();
        textField.setBounds(150, 30, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setBackground(new Color(240, 240, 240));
        loginPanel.add(textField);

        // Password label
        JLabel password = new JLabel("Password:");
        password.setBounds(30, 90, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setForeground(Color.BLACK);
        loginPanel.add(password);

        // Password text field
        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 90, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jPasswordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPasswordField.setBackground(new Color(240, 240, 240));
        loginPanel.add(jPasswordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(40, 170, 120, 35);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        loginButton.setOpaque(true);
        loginPanel.add(loginButton);

        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(190, 170, 120, 35);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(this);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cancelButton.setOpaque(true);
        loginPanel.add(cancelButton);

        // Listen for window resize events
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Adjust the background size and center the login panel on resize
                Image img = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(img));
                background.setBounds(0, 0, getWidth(), getHeight());
                positionLoginPanel(); // Reposition the login panel to the center
            }
        });

        setVisible(true);
    }

    // Method to dynamically center the login panel
    private void positionLoginPanel() {
        int panelX = (getWidth() - loginPanel.getWidth()) / 2;
        int panelY = (getHeight() - loginPanel.getHeight()) / 2;
        loginPanel.setBounds(panelX, panelY, loginPanel.getWidth(), loginPanel.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                conn c = new conn();
                String user = textField.getText();
                String pass = new String(jPasswordField.getPassword());

                String query = "select * from login where ID = '" + user + "' and PW = '" + pass + "'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
