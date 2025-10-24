package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
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

public class CustomerLoginPage extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	private JPasswordField passwordField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLoginPage frame = new CustomerLoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerLoginPage(){
		setTitle("Customer Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Vehicle Dealership");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 32));
		lblNewLabel.setBounds(0, 10, 560, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel_1.setBounds(81, 116, 164, 46);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(282, 125, 164, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel_2.setBounds(81, 188, 164, 46);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(282, 198, 164, 36);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = "system";
				String password = "Suhaas@28";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String query = "select customer_id, password from customer";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection connection = DriverManager.getConnection(url,username,password);
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next()) {
						String un = String.valueOf(resultSet.getString("customer_id"));
						String un1 = textField.getText();
						String pass = resultSet.getString("password");
						String pass1 = new String(passwordField.getPassword());
						if(un.equals(un1) && pass.equals(pass1)) {
							CustomerDash cd = new CustomerDash(un);
							cd.setVisible(true);
							dispose();
							break;
						}
					}
					resultSet.close();
					statement.close();
					connection.close();
				}
				catch(ClassNotFoundException e1) {
					System.out.println("Class not found");
					e1.printStackTrace();
				}
				catch(SQLException e1) {
					System.out.println("Class not found");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(185, 286, 123, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("←Back");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setFont(new Font("Garamond", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = new MainFrame();
				setVisible(false);
				mf.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 66, 108, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Garamond", Font.BOLD, 23));
		btnSignUp.setBackground(SystemColor.activeCaption);
		btnSignUp.setBounds(185, 363, 123, 46);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSignup csg = new CustomerSignup();
				csg.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnSignUp);
	}
}
