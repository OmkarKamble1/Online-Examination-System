import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.*;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class StudentSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField textField_3;
	static StudentSignUp frame = new StudentSignUp();

	Connection con;
	PreparedStatement pst;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/credentials", "root", "");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to connect, check your connection!");
			dispose();
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	boolean checkdigit(String str1) {
		if (Pattern.matches("[0-9]+", str1)) {
			return false;
		} else {
			return true;
		}
	}

	boolean checklen(String str1) {
		if (str1.length() < 10) {
			return true;
		} else if (str1.length() > 10) {
			return true;
		} else {
			return false;
		}
	}

	public StudentSignUp() {
		setResizable(false);
		setTitle("Student Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel label1 = new JLabel("Name");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel label2 = new JLabel("Mobile No.");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel label3 = new JLabel("Email");
		label3.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel label4 = new JLabel("Password");
		label4.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_4 = new JLabel("Student Sign Up");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Bell MT", Font.BOLD, 30));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setToolTipText("Optional");
		textField_2.setForeground(Color.BLACK);
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);

		textField_3 = new JPasswordField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("Sign Up");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Sname = textField.getText();
				String Smob = textField_1.getText();
				String Smail = textField_2.getText();
				String Spass = textField_3.getText();

				System.out.println(checkdigit(Smob));
				System.out.println(checklen(Smob));

				if ((checkdigit(Smob)) || (checklen(Smob))) {
					JOptionPane.showMessageDialog(null, "Check mobile number");
				} else if (Smob.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter mobile number");
				} else if (Sname.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter name");
				} else if (Smail.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter email id");
				} else if (Spass.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter password");
				} else {
					try {
						pst = con.prepareStatement(
								"insert into studentcredentials(stname,stphone,stmail,stpass)values(?,?,?,?)");
						pst.setString(1, Sname);
						pst.setString(2, Smob);
						pst.setString(3, Smail);
						pst.setString(4, Spass);

						int res1 = pst.executeUpdate();
						if (res1 == 1) {
							JOptionPane.showMessageDialog(null, "Signed Up Successfully!");
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Server error!");
						}
					} catch (SQLException e1) {
						if (e1.getErrorCode() == 1062) {
							JOptionPane.showMessageDialog(null, "User already exists, Try to log in");
						}
					}

				}
			}
		});
		btnNewButton.setFont(new Font("Bell MT", Font.BOLD, 23));

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HomePage.main(null);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(112))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(160))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(66, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label2)
						.addComponent(label1)
						.addComponent(label3)
						.addComponent(label4))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_3)
						.addComponent(textField_2)
						.addComponent(textField_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label3)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label4)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnNewButton))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		Connect();
	}
}
