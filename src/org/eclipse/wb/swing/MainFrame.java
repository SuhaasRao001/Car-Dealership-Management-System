package org.eclipse.wb.swing;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 418);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setLocation(new Point(100, 200));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Vehicle Dealership");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 32));
		lblNewLabel.setBounds(0, 10, 607, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Employee");
		btnNewButton.setBackground(new Color(153, 180, 209));
		btnNewButton.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpLoginPage elp = new EmpLoginPage();
				elp.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(90, 159, 143, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Customer");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setFont(new Font("Garamond", Font.BOLD, 23));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLoginPage clp = new CustomerLoginPage();
				clp.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(372, 162, 143, 53);
		contentPane.add(btnNewButton_1);
	}
}
