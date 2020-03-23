import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BankTeller extends JFrame implements ActionListener {

    private JButton submit = new JButton("Submit");
    private JPanel blankPanel1 = new JPanel();
    private JLabel name1 = new JLabel("Name: ");
    private JTextField tb_name1 = new JTextField(20);
    private JLabel accountNum1 = new JLabel("Account Number: ");
    private JTextField tb_accountNum1 = new JTextField(20);
    private JLabel transaction1 = new JLabel("Transaction: ");
    private JTextField tb_amountToAdd1 = new JTextField(20);
    private JLabel sep1 = new JLabel("------------------------------------------------------------");
    private JLabel sep2 = new JLabel("------------------------------------------------------------");
    private JLabel name2 = new JLabel("2nd Name: ");
    private JLabel tb_name2 = new JLabel();
    private JLabel accountNum2 = new JLabel("2nd Account Number: ");
    private JLabel tb_accountNum2 = new JLabel();
    private JLabel transaction2 = new JLabel("2nd Transaction: ");
    private JLabel tb_transaction2 = new JLabel();

    BankAccount newCustomer = new BankAccount();

			
	private Container content = getContentPane();
	
	public BankTeller() {
		setSize(500,500);
		content.setBackground(Color.white);
        content.setLayout(new GridLayout(8,2));
        

        content.add(name1);
        content.add(tb_name1);

        content.add(accountNum1);
        content.add(tb_accountNum1);

        content.add(transaction1);
        content.add(tb_amountToAdd1);

        content.add(submit);
		submit.addActionListener(this);
        content.add(blankPanel1);

        content.add(sep1);
        content.add(sep2);

        content.add(name2);
        content.add(tb_name2);

        content.add(accountNum2);
        content.add(tb_accountNum2);

        content.add(transaction2);
        content.add(tb_transaction2);
               

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent evt) {
        
		
		if (evt.getSource() == submit) {
            
            String tempName = tb_name1.getText();
            int tempAccountNum = Integer.parseInt(tb_accountNum1.getText());
            double tempTransaction = Double.parseDouble(tb_amountToAdd1.getText());                    
            
            newCustomer.setAccountName(tempName);
            newCustomer.setAccountNumber(tempAccountNum);
            newCustomer.setAccountTransaction(tempTransaction);

            String bankAccount_Name = newCustomer.getAccountName();
            String bankAccount_AccountNumber = Integer.toString((newCustomer.getAccountNumber()));
            String bankAccount_AccountBalance = Double.toString(newCustomer.getAccountBalance());

            tb_name2.setText(bankAccount_Name);
            tb_accountNum2.setText(bankAccount_AccountNumber);
            tb_transaction2.setText(bankAccount_AccountBalance);
            
		}
		
	}



	public static void main(String[] args) {
		BankTeller myFrame = new BankTeller();
		myFrame.setVisible(true);
	}

}
