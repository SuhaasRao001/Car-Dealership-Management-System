package org.eclipse.wb.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class CustomerService extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerService frame = new CustomerService();
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
	public CustomerService() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Book Service");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(82, 114, 226, 70);
		contentPane.add(btnNewButton);
		
		JButton btnCancelService = new JButton("Cancel Service");
		btnCancelService.setBackground(SystemColor.activeCaption);
		btnCancelService.setFont(new Font("Garamond", Font.BOLD, 23));
		btnCancelService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelService.setBounds(463, 114, 226, 70);
		contentPane.add(btnCancelService);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Garamond", Font.BOLD, 18));
		scrollPane.setBounds(82, 301, 607, 92);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Service Type", "Booking Date"
				}
			);
		table.setModel(model);
	}
	public CustomerService(String cid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Book Service");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerServiceSchedule css = new CustomerServiceSchedule(cid);
				css.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(82, 114, 226, 70);
		contentPane.add(btnNewButton);
		
		JButton btnCancelService = new JButton("Cancel Service");
		btnCancelService.setBackground(SystemColor.activeCaption);
		btnCancelService.setFont(new Font("Garamond", Font.BOLD, 23));
		btnCancelService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerServiceCancel csc = new CustomerServiceCancel(cid);
				csc.setVisible(true);
				dispose();
			}
		});
		btnCancelService.setBounds(463, 114, 226, 70);
		contentPane.add(btnCancelService);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Garamond", Font.BOLD, 18));
		scrollPane.setBounds(82, 301, 607, 92);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Service Type", "Booking Date"
				}
			);
		table.setModel(model);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "Suhaas@28";
        String query = "select s.service_type, b.booking_date from servicing s, booking b where s.service_id = b.service_id and b.customer_id ="+cid;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String service_type = resultSet.getString("service_type");
                String booking_date = resultSet.getString("booking_date").substring(0,10);
                model.addRow(new Object[] {service_type,booking_date});
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
	}
}
