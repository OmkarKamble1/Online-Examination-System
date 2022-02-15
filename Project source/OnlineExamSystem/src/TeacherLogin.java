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

public class TeacherLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;

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
					TeacherLogin frame = new TeacherLogin();
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

	public TeacherLogin() {
		setResizable(false);
		setTitle("Teacher Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Teacher Login");
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
				System.out.println(checkdigit(Smob));
				System.out.println(checklen(Smob));
				if ((checkdigit(Smob)) || (checklen(Smob))) {
					JOptionPane.showMessageDialog(null, "Check mobile number");
				} else if (Smob.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter mobile number");
				} else if (Spass.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter password");
				} else {
					try {
						pst = con.prepareStatement(
								"Select stphone,stpass from teachercredentials where stphone=? and stpass=?");
						pst.setString(1, Smob);
						pst.setString(2, Spass);

						ResultSet res1 = pst.executeQuery();
						if (res1.next()) {
							TestBuilderForm.main(null);
							dispose();
							textField1.setText("");
							textField2.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Wrong Mobile number or password!");
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField1, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(textField2, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(56))))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
					.addGap(55))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		Connect();
	}
}
