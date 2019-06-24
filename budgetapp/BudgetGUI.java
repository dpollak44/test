
package budgetapp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author david
 */
public class BudgetGUI extends JFrame {

    JTextField tf;
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JTextField tf4;
    JTextField output;
    JTextField maaserOutput;
    String s = "budgetApp";
    char c = '$';

    myAccount myAct = new myAccount();
    maaserAccount maaserAct = new maaserAccount();

    public BudgetGUI() {

        setup();
    }

    public void setup() {
        setSize(new Dimension(800, 400));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.blue);

        JPanel displayPanel = new JPanel();
        JPanel maaserPanel = new JPanel();
        displayPanel.setBackground(Color.blue);
        JLabel dispLabel = new JLabel("Balance:");
        displayPanel.add(dispLabel);
        output = new JTextField(20);
        displayPanel.add(output);
        output.setText(c + Double.toString(myAct.getBalance()));

        JButton setBalButton = new JButton("SetBalance");
        setBalButton.setActionCommand("SetBalance");
        JButton addButton = new JButton("Add");
        addButton.setActionCommand("Add");
        JButton subtButton = new JButton("Subtract");
        subtButton.setActionCommand("Subtract");
        JButton depMaaserButton = new JButton("DepositAndMaaser");
        depMaaserButton.setActionCommand("DepositAndMaaser");

        tf = new JTextField(20);
        if (myAct == null) {
            tf.setEnabled(false);
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);

        buttonPanel.add(tf);

        buttonPanel.add(setBalButton);
        buttonPanel.add(addButton);
        buttonPanel.add(subtButton);
        buttonPanel.add(depMaaserButton);

        TransactionListener tl1 = new TransactionListener();
        addButton.addActionListener(tl1);
        setBalButton.addActionListener(tl1);
        subtButton.addActionListener(tl1);
        depMaaserButton.addActionListener(tl1);

        Dimension preferredSize = setBalButton.getPreferredSize();
        addButton.setPreferredSize(preferredSize);
        subtButton.setPreferredSize(preferredSize);

        JButton pMasButton = new JButton("PaidMaaser");
        pMasButton.setActionCommand("PaidMaaser");
        JButton masStButton = new JButton("MaaserStatement");
        masStButton.setActionCommand("MaaserStatement");

        tf1 = new JTextField(20);
        tf2 = new JTextField(40);
        tf3 = new JTextField(10);
        tf4 = new JTextField(10);

        JLabel maaserLabel1 = new JLabel("Maaser Owed:");
         JLabel maaserLabel2 = new JLabel("Organization:");
          JLabel maaserLabel3 = new JLabel("From Date:");
           JLabel maaserLabel4 = new JLabel("To Date:");
        maaserPanel.add(maaserLabel1);
        maaserOutput = new JTextField(20);
        maaserPanel.add(maaserOutput);
        maaserOutput.setText(c + Double.toString(maaserAct.getBalance()));
        maaserPanel.add(pMasButton);
        maaserPanel.add(tf1);
        maaserPanel.add(maaserLabel2);
        maaserPanel.add(tf2);
        maaserPanel.add(masStButton);
        maaserPanel.add(maaserLabel3);
        maaserPanel.add(tf3);
        maaserPanel.add(maaserLabel4);
        maaserPanel.add(tf4);

        MasTransactionListener tl2 = new MasTransactionListener();
        pMasButton.addActionListener(tl2);
        masStButton.addActionListener(tl2);

        contentPane.add(displayPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(maaserPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("BudgetApp");

    }

    public class TransactionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            String text = tf.getText();
            double amt = Double.parseDouble(text);
            if (actionCommand.equals("Subtract")) {
                myAct.withdraw(amt);
            } else if (actionCommand.equals("DepositAndMaaser")) {
                myAct.deposit(amt);
                maaserAct.deposit(amt);

            } else {
                myAct.deposit(amt);
            }

            output.setText(c + Double.toString(myAct.getBalance()));
            maaserOutput.setText(c + Double.toString(maaserAct.getBalance()));
            tf.setText("");

        }

    }

    public class MasTransactionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            String text = tf1.getText();
            double amt = Double.parseDouble(text);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            String org = tf2.getText();
            String date1 = tf3.getText();
            String date2 = tf4.getText();

            if (actionCommand.equals("PaidMaaser")) {
                maaserAct.withdraw(amt,org);

            } else {
                try {
                    Output.fileWriter(BudgetJDBC.getMaaserInfo(date1,date2));
                } catch (IOException ex) {
                    Logger.getLogger(BudgetGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            maaserOutput.setText(c + Double.toString(maaserAct.getBalance()));
            tf1.setText("");
            tf2.setText("");
        }

    }

    public static void main(String[] args) {
        new BudgetGUI().setVisible(true);
    }

}
