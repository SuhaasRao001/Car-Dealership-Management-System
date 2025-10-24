package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerSignup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSignup frame = new CustomerSignup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerSignup() {
		setTitle("Customer Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 17));
		lblNewLabel.setBounds(92, 39, 122, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Garamond", Font.BOLD, 17));
		lblPassword.setBounds(92, 133, 122, 37);
		contentPane.add(lblPassword);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setFont(new Font("Garamond", Font.BOLD, 17));
		lblEmailId.setBounds(92, 180, 122, 37);
		contentPane.add(lblEmailId);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setFont(new Font("Garamond", Font.BOLD, 17));
		lblPhoneNumber.setBounds(92, 227, 122, 37);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Garamond", Font.BOLD, 17));
		lblAddress.setBounds(92, 274, 122, 37);
		contentPane.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Garamond", Font.BOLD, 17));
		lblGender.setBounds(92, 321, 122, 37);
		contentPane.add(lblGender);
		
		JLabel lblCreditScore = new JLabel("Credit Score");
		lblCreditScore.setFont(new Font("Garamond", Font.BOLD, 17));
		lblCreditScore.setBounds(92, 368, 122, 37);
		contentPane.add(lblCreditScore);
		
		JLabel lblNoOfPurchases = new JLabel("No. of purchases");
		lblNoOfPurchases.setFont(new Font("Garamond", Font.BOLD, 17));
		lblNoOfPurchases.setBounds(92, 415, 122, 37);
		contentPane.add(lblNoOfPurchases);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Garamond", Font.BOLD, 17));
		lblName.setBounds(92, 86, 122, 37);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(276, 49, 129, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(276, 96, 129, 27);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(276, 180, 129, 27);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(276, 227, 129, 27);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(276, 274, 129, 27);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(276, 321, 129, 27);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(276, 368, 129, 27);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(276, 421, 129, 27);
		contentPane.add(textField_7);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(276, 143, 129, 27);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.setBounds(190, 506, 158, 46);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
		        String username = "system";
		        String password = "Suhaas@28";
		        String cid = textField.getText();
		        String name = textField_1.getText();
		        String pass = new String(passwordField.getPassword());
		        String email_id = textField_2.getText();
		        String phone_no = textField_3.getText();
		        String address = textField_4.getText();
		        String gender = textField_5.getText();
		        String credit_score = textField_6.getText();
		        String no_of_purchases = textField_7.getText();
		        String query = "insert into customer(customer_id,password,email_id,phone_no,address,name,gender,credit_score,no_of_purchases) values("+cid+", '"+pass+"' , '"+email_id+"' , "+phone_no+" , '"+address+"' , '"+name+"' , '"+gender+"' , "+credit_score+" , "+no_of_purchases+")";
		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection connection = DriverManager.getConnection(url, username, password);
		            Statement statement = connection.createStatement();
		            ResultSet resultSet = statement.executeQuery(query);
		            resultSet.close();
		            statement.close();
		            connection.close();
		            CustomerLoginPage clp = new CustomerLoginPage();
		            clp.setVisible(true);
		            dispose();

		        } catch (ClassNotFoundException e) {
		            System.out.println("Oracle JDBC Driver not found!");
		            e.printStackTrace();
		        } catch (SQLException e) {
		            System.out.println("Connection failed!");
		            e.printStackTrace();
		        }
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("←");
		btnNewButton_1.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 8, 70, 27);
		contentPane.add(btnNewButton_1);
		
	}
}
