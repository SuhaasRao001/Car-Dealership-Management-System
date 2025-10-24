package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class EmployeeDash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDash frame = new EmployeeDash();
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
	public EmployeeDash() {
		setTitle("Employee Dash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Assign Service Workers");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.setBounds(444, 70, 285, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Record Sale");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton_1.setBounds(69, 70, 285, 60);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeRecordSale ers = new EmployeeRecordSale("101");
				ers.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 215, 640, 93);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"VIN", "Sale Date"
				}
			);
		table.setModel(model);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(88, 366, 634, 103);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Service Type"
			}
		));
	}
	public EmployeeDash(String eid) {
		setTitle("Employee Dash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Assign Service Workers");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.setBounds(444, 70, 285, 60);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeAssignSerWork easw = new EmployeeAssignSerWork(eid);
				easw.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Record Sale");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton_1.setBounds(69, 70, 285, 60);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeRecordSale ers = new EmployeeRecordSale(eid);
				ers.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 215, 640, 93);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"VIN", "Sale Date"
				}
			);
		table.setModel(model);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "Suhaas@28";
        String query = "SELECT vin,sale_date from sells where employee_id = "+eid;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String vin = resultSet.getString("vin");
                String sale_date = resultSet.getString("sale_date");
                model.addRow(new Object[] {vin,sale_date});
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(88, 366, 634, 103);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		DefaultTableModel model1 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Employee ID", "Service Type"
				}
			);
		table_1.setModel(model1);
		String query1 = "SELECT w.employee_id, s.service_type from works_on w, servicing s where s.service_id = w.service_id";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {
                String eidd = resultSet.getString("employee_id");
                String service_type = resultSet.getString("service_type");
                model1.addRow(new Object[] {eidd,service_type});
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Oraacle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
	}
}
