
/*This class contains the business logic of our application, including money deposits, withdrawal
transfer, payments of various bills and even displaying the transaction History for every activity
Some of the fundamental concepts for business  logic implementaion such as deposits, withdrawals were
adapted from [https://codewithcurious.com/projects/banking-application-using-java-gui/]
 * 
 */

//Add neccessary imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class App implements ActionListener {
    private JFrame mainFrame;
    private JLabel balanceLabel;
    private int balance = 0;
    private ArrayList<String> transactionHistory;

    public App () {
      
        // Initialize main frame components
        transactionHistory = new ArrayList<>();
    }


//Create an actionPerfomed method to listen and handle clicks fro the whole interface

@Override
public void actionPerformed(ActionEvent e) {

    //create a variable action to be used for each specific action performed
    String action = e.getActionCommand();

     if (action.equals("Deposit") || action.equals("Transfer")) {
        String input = JOptionPane.showInputDialog(mainFrame, "Enter amount:");
        if (input != null && !input.isEmpty()) {
            int amount = Integer.parseInt(input);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(mainFrame, "Dear customer enter amount above MWK0.00.");
            } else {
                // Proceed with deposit or transfer logic...
                if (action.equals("Deposit")) {
                    balance += amount;
                    addToTransactionHistory("Deposit", amount);
                } else if (action.equals("Transfer")) {
                    // Transfer logic...


                    String recipient = JOptionPane.showInputDialog(mainFrame, "ENTER THE ACCOUNT NUMBER OF THE RECIPIENT:");
                    if (recipient != null && !recipient.isEmpty()) {
                        // Assuming a simple transfer from balance to another account
                        if (amount <= balance) {
                          balance = balance - amount;
                            addToTransactionHistory("Transfer to " + recipient, amount);
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "Insufficient balance for transfer!");
                        }
                    }    


                }
                balanceLabel.setText("CURRENT BALANCE: MWK " + balance  + ".00");
            }
        }
    } else if (action.equals("Bill Payment")) {
        // Bill payment logic...
        String[] options = {"Electricity Bill", "Water Bill", "School Fees",
          "MANEB",  "DSTV Subscription",  "City Rates", 
          "RENT",  "Medicine", "INTERNET"};
        String selectedOption = (String) JOptionPane.showInputDialog(mainFrame, "Select bill to pay:", "Bill Payment", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedOption != null) {
            String input = JOptionPane.showInputDialog(mainFrame, "Enter amount for " + selectedOption + ":");
            if (input != null && !input.isEmpty()) {
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Dear Customer you can not pay for less than MWK0.00.");
                } else {
                    // Proceed with bill payment logic...
                    if (amount <= balance) {
                        balance -= amount;
                        addToTransactionHistory("Bill Payment - " + selectedOption, amount);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Insufficient balance for bill payment!");
                    }
                    balanceLabel.setText("CURRENT BALANCE: MWK " + balance + ".00");
                }
            }
        }
    } else if (action.equals("Withdraw")) {
        String input = JOptionPane.showInputDialog(mainFrame, "Enter amount:");
        if (input != null && !input.isEmpty()) {
            int amount = Integer.parseInt(input);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(mainFrame, "Dear Customer you can only withdraw amount above MWK0.00.");
            } else {
                // Proceed with withdrawal logic...
                if (amount <= balance) {
                    balance -= amount;
                    addToTransactionHistory("Withdraw", amount);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Insufficient balance!");
                }
                balanceLabel.setText("CURRENT BALANCE: MWK " + balance + ".00");
            }
        }
    } else if (action.equals("Transaction History")) {
        // Display transaction history
        showTransactionHistory();
    }
}

 void showMainFrame() {
    // Create main application frame
    mainFrame = new JFrame("Simple Bank Application");
    mainFrame.setVisible(true);
    mainFrame.setSize(1920, 1080); // Resized the frame to 800x600
    mainFrame.setFont(new Font("Consolas", Font.BOLD, 20));
    mainFrame.setLayout(new BorderLayout()); // Change layout to BorderLayout
    mainFrame.setLocationRelativeTo(null);
   // mainFrame.getContentPane().setBackground(new Color(204, 51, 139)); // Set background color to orange
   mainFrame.getContentPane().setBackground(Color.BLACK);
    balanceLabel = new JLabel("CURRENT BALANCE: MWK " + balance  + ".00");
    balanceLabel.setForeground(new Color(255, 200, 203));
    

    balanceLabel.setFont(new Font("Consolas", Font.BOLD, 35));

    // Create the welcoming text panel
    JPanel welcomingTextPanel = new JPanel(new BorderLayout());
    JLabel myWelcomeText = new JLabel("Welcome to Financial Dashboard");
    
    myWelcomeText.setFont(new Font("Consolas", Font.PLAIN, 25));
  
    
    //signOutButton.setPreferredSize(new Dimension(100, 10));
    myWelcomeText.setForeground(Color.WHITE);
    myWelcomeText.setHorizontalAlignment(SwingConstants.CENTER);
    welcomingTextPanel.add(myWelcomeText, BorderLayout.CENTER);
    welcomingTextPanel.setForeground(Color.WHITE);
    welcomingTextPanel.setBackground(Color.BLACK);
    JLabel iconLabel = new JLabel();
    ImageIcon img=new ImageIcon("bank3.png");
    iconLabel.setIcon(img);
    iconLabel.setBounds(40, 500, 60, 180);
   // iconLabel.setPreferredSize(new Dimension(180, 180));
    welcomingTextPanel.add(iconLabel, BorderLayout.WEST);
//welcomingTextPanel.setIcon(new ImageIcon("pic1.png"), BorderLayout.WEST);

    // Increase the height of the welcoming text panel
    welcomingTextPanel.setPreferredSize(new Dimension(800, 150)); // Set the preferred height

    // Create a panel for the horizontal navigation bar
    JPanel navBarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // FlowLayout with horizontal alignment and smaller gaps
    navBarPanel.setBackground(new Color(8, 20, 97)); // Set background color to match main frame*********
    JButton depositButton = new JButton("Deposit");
    JButton withdrawButton = new JButton("Withdraw");
    JButton transferButton = new JButton("Transfer");
    JButton billPaymentButton = new JButton("Bill Payment");
    JButton historyButton = new JButton("Transaction History");
    
    // Set preferred size for buttons to make dialogue boxes smaller
    Dimension buttonSize = new Dimension(120, 30);
    depositButton.setPreferredSize(buttonSize);
    depositButton.setFocusable(false);
    depositButton.setBackground(new Color(0,0,0));
    depositButton.setForeground(Color.WHITE);
    depositButton.setPreferredSize(new Dimension(180, 50));
    withdrawButton.setPreferredSize(new Dimension(180, 50));//
    withdrawButton.setFocusable(false);
    transferButton.setPreferredSize(new Dimension(180, 50));//
    transferButton.setFocusable(false);
    billPaymentButton.setPreferredSize(new Dimension(180, 50));//
    billPaymentButton.setFocusable(false);
    historyButton.setPreferredSize(new Dimension(180, 50));//
    historyButton.setFocusable(false);
   // withdrawButton.setPreferredSize(buttonSize);
    withdrawButton.setForeground(Color.WHITE);
    withdrawButton.setBackground(new Color(0,0,0));
  //  transferButton.setPreferredSize(buttonSize);
    transferButton.setBackground(new Color(0,0,0));
    transferButton.setForeground(Color.WHITE);
 //   billPaymentButton.setPreferredSize(buttonSize);
    billPaymentButton.setBackground(new Color(0,0,0));
    billPaymentButton.setForeground(Color.WHITE);
   // historyButton.setPreferredSize(buttonSize);
    historyButton.setBackground(new Color(0,0,0));
    historyButton.setForeground(Color.WHITE);
    
    navBarPanel.add(depositButton);
    navBarPanel.add(withdrawButton);
    navBarPanel.add(transferButton);
    navBarPanel.add(billPaymentButton);
    navBarPanel.add(historyButton);

    //ADD THE LOGOUT PANEL TO THE LEFT
    JPanel logoutPanel = new JPanel();
    navBarPanel.add(logoutPanel, BorderLayout.EAST);
    logoutPanel.setPreferredSize(new Dimension(100, 500));
    logoutPanel.setLayout(new GridLayout(1, 1, 5, 5));
    logoutPanel.setBackground(new Color(8, 20, 97));

    mainFrame.add(welcomingTextPanel, BorderLayout.NORTH); // Add welcoming text panel to the top
    mainFrame.add(navBarPanel, BorderLayout.CENTER); // Add navigation bar panel to the center
    mainFrame.add(balanceLabel, BorderLayout.SOUTH); // Add balance label to the bottom
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //helps to close the application

    depositButton.addActionListener(this);
    withdrawButton.addActionListener(this);
    transferButton.addActionListener(this);
    billPaymentButton.addActionListener(this);
    historyButton.addActionListener(this);

    
}

 //AFTER CREATING LOGIN-SCREENS NOW ADD BUSINESS LOGIC 
//Create a addToTransactionHistory method
    private void addToTransactionHistory(String action, int amount) {
//store date, action, and amount in a transcation variable
        String transaction = new Date() + " - " + action + " : " + amount;

        transactionHistory.add(transaction);
    }


    //Crreate a method to displayTransactionHistory by use of a string builder
    private void showTransactionHistory() {

        StringBuilder history = new StringBuilder("Transaction History:\n");
        for (String transaction : transactionHistory) {

            history.append(transaction).append("\n"); //add transaction to the end of the list
        }

        JOptionPane.showMessageDialog(mainFrame, history.toString()); //display transaction history list
    
    }

   
}
