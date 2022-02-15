import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class StudentLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	String testcode1,name;
	Connection con;
	Connection con2;
	PreparedStatement pst;
	PreparedStatement pst2;
	private JTextField textField;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/credentials", "root", "");
			con2 = DriverManager.getConnection("jdbc:mysql://localhost/testsdb", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to connect, check your connection!");
			dispose();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		try {
				StudentLogin frame = new StudentLogin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
 //8104561330
	//omkar@1235

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

	public StudentLogin() {
		setResizable(false);
		setTitle("Student Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 30));

		JLabel lblNewLabel_1 = new JLabel("Mobile No.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField1.setColumns(10);

		textField2 = new JPasswordField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField2.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Smob = textField1.getText();
				String Spass = textField2.getText();
				testcode1 = textField.getText();
				//System.out.println(checkdigit(Smob));
				//System.out.println(checklen(Smob));
				if ((checkdigit(Smob)) || (checklen(Smob))) {
					JOptionPane.showMessageDialog(null, "Check mobile number");
				} else if (Smob.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter mobile number");
				} else if (Spass.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter password");
				} else if (testcode1.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter test code");
				} else {
					try {
						pst = con.prepareStatement(
								"Select stname,stphone,stpass from studentcredentials where stphone=? and stpass=?");
						pst.setString(1, Smob);
						pst.setString(2, Spass);
						ResultSet res1 = pst.executeQuery();  //huh jij  iji
						
						String q1 = "SELECT * FROM `$tbl`;";
						String q2 = q1.replace("$tbl", testcode1);
						pst2 = con2.prepareStatement(q2);
						boolean res2 = false;
						try{
							res2 = pst2.execute();					
						}catch(SQLException w) {
							
						}
						
						if(res2) {
							if (res1.next()) {
								JOptionPane.showMessageDialog(null, "Log in Successful!");
								name = res1.getString(1);
								testcode1 = textField.getText();
								textField1.setText("");
								textField2.setText("");
								dispose();
								ExamForm.tcode = testcode1;
								ExamForm.studname = name;
								ExamForm.main(null);
							} else {
								JOptionPane.showMessageDialog(null, "Wrong Mobile number or password!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Invalid test code!");
							textField.setText("");
						}
					
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton.setFont(new Font("Bell MT", Font.BOLD, 23));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Bell MT", Font.BOLD, 23));
		
		JLabel lblNewLabel_2_1 = new JLabel("Test Code");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
								.addComponent(textField2, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(92)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(82))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(4)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
		Connect();
	}
}
