package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class EmployeeAssignSerWork extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeAssignSerWork frame = new EmployeeAssignSerWork();
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
	public EmployeeAssignSerWork() {}
	public EmployeeAssignSerWork(String eid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID:");
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel.setBounds(151, 93, 144, 28);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(361, 93, 165, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblServiceType = new JLabel("Service ID:");
		lblServiceType.setFont(new Font("Garamond", Font.BOLD, 23));
		lblServiceType.setBounds(151, 176, 144, 28);
		contentPane.add(lblServiceType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Garamond", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"701", "702", "703", "704", "705"}));
		comboBox.setBounds(361, 176, 227, 28);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Assign");
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(257, 301, 207, 46);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
		        String username = "system";
		        String password = "Suhaas@28";
		        
		        String query = "insert into works_on(employee_id,service_id) values("+textField.getText()+","+(String)comboBox.getSelectedItem()+")";
		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection connection = DriverManager.getConnection(url, username, password);
		            Statement statement = connection.createStatement();
		            ResultSet resultSet = statement.executeQuery(query);
		            resultSet.close();
		            statement.close();
		            connection.close();
		            EmployeeDash ed = new EmployeeDash(eid);
		            ed.setVisible(true);
		            dispose();

		        } catch (ClassNotFoundException e1) {
		            System.out.println("Oracle JDBC Driver not found!");
		            e1.printStackTrace();
		        } catch (SQLException e1) {
		            System.out.println("Connection failed!");
		            e1.printStackTrace();
		        }
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("←");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(24, 26, 109, 37);
		contentPane.add(btnNewButton_1);
	}
}
