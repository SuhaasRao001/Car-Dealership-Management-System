package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRecordSale extends JFrame {

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
					EmployeeRecordSale frame = new EmployeeRecordSale();
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
	public EmployeeRecordSale() {
		
	}
	public EmployeeRecordSale(String eid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIN");
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(121, 138, 123, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(386, 128, 155, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		SpinnerDateModel model = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(model);
        dateSpinner.setFont(new Font("Garamond", Font.BOLD, 20));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd-MMM-yyyy");
        dateSpinner.setEditor(editor);
		dateSpinner.setBounds(386, 206, 308, 48);
		contentPane.add(dateSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Visiting Date:");
		lblNewLabel_1.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel_1.setBounds(86, 205, 232, 48);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Record");
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(269, 336, 163, 54);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
		        String username = "system";
		        String password = "Suhaas@28";
		        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
                String formattedDate = sdf.format((java.util.Date)dateSpinner.getValue());
		        String query = "insert into sells(vin, employee_id, sale_date) values("+textField.getText()+","+eid+",'"+formattedDate+"')";
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
		btnNewButton_1.setBounds(30, 37, 98, 40);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDash ed = new EmployeeDash(eid);
				ed.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_1);
	}

}
