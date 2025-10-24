package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;

public class CustomerVisit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton btnCancelVisit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerVisit frame = new CustomerVisit();
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
	public CustomerVisit() {
	}
	public CustomerVisit(String cid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 332, 517, 123);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Garamond", Font.BOLD, 18));
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Dealership", "Date of Visit"
				}
			);
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(92);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "Suhaas@28";
        String query = "SELECT dealership_id,visit_date from visits where customer_id="+cid;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String did = resultSet.getString("dealership_id");
                String visit_date = resultSet.getString("visit_date");
                model.addRow(new Object[] { did, visit_date });
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
		JLabel lblNewLabel = new JLabel("Scheduled Visits");
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(283, 267, 188, 39);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Schedule Visit");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.setBounds(107, 121, 229, 59);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerVisitSchedule cvs = new CustomerVisitSchedule(cid);
				cvs.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		btnCancelVisit = new JButton("Cancel Visit");
		btnCancelVisit.setBackground(SystemColor.activeCaption);
		btnCancelVisit.setFont(new Font("Garamond", Font.BOLD, 23));
		btnCancelVisit.setBounds(433, 121, 229, 59);
		btnCancelVisit.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerVisitCancel cvc = new CustomerVisitCancel(cid);
				cvc.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnCancelVisit);
		
		JButton btnNewButton_1 = new JButton("←Back");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setFont(new Font("Garamond", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDash cd = new CustomerDash(cid);
				cd.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 46, 108, 40);
		contentPane.add(btnNewButton_1);
	}
}
