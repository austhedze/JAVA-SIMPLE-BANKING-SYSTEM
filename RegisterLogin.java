/*This class displays the register and login fields,
 * it uses the concept of hash maps, wheereby data such as of type string are temporarily
 * stored within its array list of elements
 * the basic working was adapted from the following:
 *  [https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/]
 * https://www.youtube.com/watch?v=Hiv3gwJC5kw&pp=ygUQbG9naW4gc3dpbmcgamF2YQ%3D%3D
 * https://www.youtube.com/watch?v=H62Jfv1DJlU&pp=ygUVc3dpbmcgbG9oaW4gaGFzaCBtYXBz
 */

//add necessary imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class RegisterLogin extends JFrame {
    private HashMap<String, String> users;

    private JTextField usernameField;
    private JTextField accountNumberField;
    private JPasswordField passwordField;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    public RegisterLogin() {
        users = new HashMap<>();

        setTitle("Create Account And Login");
        setResizable(false);
        setSize(750, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
        setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel(new BorderLayout());
        registerPanel.setBackground(Color.BLACK);
        registerPanel.setPreferredSize((new Dimension(1920, 200)));
        
        JPanel registerDetailsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel accountNumberLabel = new JLabel("AcctNumber:");
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        usernameField = new JTextField();
        usernameLabel.setForeground(Color.WHITE);
        accountNumberField = new JTextField();
        accountNumberLabel.setForeground(Color.WHITE);
        passwordLabel.setBackground(Color.WHITE);
        passwordField = new JPasswordField();
        registerDetailsPanel.add(usernameLabel);
        registerDetailsPanel.setBackground(new Color(8, 20, 97));
        registerDetailsPanel.add(usernameField);
        registerDetailsPanel.add(accountNumberLabel);
        registerDetailsPanel.add(accountNumberField);
        registerDetailsPanel.add(passwordLabel);
        registerDetailsPanel.add(passwordField);
        registerPanel.add(registerDetailsPanel, BorderLayout.CENTER);

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(Color.BLACK);
        JPanel loginDetailsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        loginDetailsPanel.setBackground(new Color(8, 20, 97));
        JLabel loginUsernameLabel = new JLabel("Username:");
        JLabel loginPasswordLabel = new JLabel("Password:");
        loginUsernameField = new JTextField();
        loginUsernameField.setPreferredSize(new Dimension(150, 24));
        loginPasswordLabel.setForeground(Color.WHITE);
        loginUsernameLabel.setForeground(Color.WHITE);
        loginPasswordField = new JPasswordField();
        loginDetailsPanel.add(loginUsernameLabel);
        loginDetailsPanel.add(loginUsernameField);
        loginDetailsPanel.add(loginPasswordLabel);
        loginDetailsPanel.add(loginPasswordField);
        loginPanel.add(loginDetailsPanel, BorderLayout.CENTER);

        //increse the register  and login fields  font-size

loginUsernameField.setFont(new Font("Consolas", Font.PLAIN, 20));
loginPasswordField.setFont(new Font("Consolas", Font.PLAIN, 20));
loginUsernameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
loginPasswordLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
usernameField.setFont(new Font("Consolas", Font.PLAIN, 20));
passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
accountNumberField.setFont(new Font("Consolas", Font.PLAIN, 20));
usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
accountNumberLabel.setFont(new Font("Consolas", Font.PLAIN, 20));



JButton registerButton = new JButton("Register");
registerButton.setFont(new Font("null", Font.ITALIC, 20));
registerButton.setBackground(Color.BLACK);
registerButton.setForeground(Color.WHITE);
registerButton.setFocusable(false);
registerButton.setPreferredSize(new Dimension(100, 30));
registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String accountNumber = accountNumberField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || accountNumber.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    users.put(username, password);
                    JOptionPane.showMessageDialog(null, "User registered successfully!");
                    //clear the register fields when register button is clicked
                    usernameField.setText("");
                    accountNumberField.setText("");
                    passwordField.setText("");
                }
            }
        });
        registerPanel.add(registerButton, BorderLayout.SOUTH);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("null", Font.ITALIC, 21));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);
        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginUsernameField.getText();
                String password = new String(loginPasswordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else if (users.containsKey(username) && users.get(username).equals(password)) {
                   // JOptionPane.showMessageDialog(null, "Login successful!");
                    App mainApp = new App();
                    mainApp.showMainFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
            }
        });
        loginPanel.add(loginButton, BorderLayout.SOUTH);

        add(registerPanel);
        add(loginPanel);
    }
}
