package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CustomerVisitCancel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerVisitCancel frame = new CustomerVisitCancel();
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
	public CustomerVisitCancel() {
		
	}
	public CustomerVisitCancel(String cid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Garamond", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Reddy Cars", "Agarwal Cars", "Arora Cars"}));
		comboBox.setBounds(386, 144, 392, 38);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Choose Dealership:");
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel.setBounds(75, 143, 229, 38);
		contentPane.add(lblNewLabel);
		
		
		 SpinnerDateModel model = new SpinnerDateModel();
         JSpinner dateSpinner = new JSpinner(model);
         dateSpinner.setFont(new Font("Garamond", Font.BOLD, 20));

         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
         JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd-MMM-yyyy");
         dateSpinner.setEditor(editor);
		dateSpinner.setBounds(386, 275, 392, 48);
		contentPane.add(dateSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Visiting Date:");
		lblNewLabel_1.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel_1.setBounds(72, 271, 232, 48);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Cancel Visit");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
		        String username = "system";
		        String password = "Suhaas@28";
		        String query1 = "select dealership_id from dealership where name='"+(String)comboBox.getSelectedItem()+"'";
		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection connection = DriverManager.getConnection(url, username, password);
		            Statement statement = connection.createStatement();
		            ResultSet resultSet1 = statement.executeQuery(query1);
		            if (resultSet1.next()) {
		                String did = resultSet1.getString("dealership_id");
		                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		                String formattedDate = sdf.format((java.util.Date)dateSpinner.getValue());
		                String query2 = "delete from visits where customer_id = " 
		                                + cid + " and dealership_id = " + did + "and visit_date = '" + formattedDate + "'";

			            ResultSet resultSet2 = statement.executeQuery(query2);
			            resultSet2.close();
		                
		            }
		            resultSet1.close();
		            statement.close();
		            connection.close();
		            CustomerVisit cv = new CustomerVisit(cid);
		            cv.setVisible(true);
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
		btnNewButton.setBounds(271, 410, 229, 75);
		contentPane.add(btnNewButton);
	}
}
