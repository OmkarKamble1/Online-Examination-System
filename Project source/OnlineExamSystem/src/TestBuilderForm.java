import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TestBuilderForm extends JFrame {
	Connection con;
	Connection con2;
	PreparedStatement pst;
	PreparedStatement pst2;
	Statement stmt;
	static int ind = 0;
	String qs[] = new String[20];
	String o1[] = new String[20];
	String o2[] = new String[20];
	String o3[] = new String[20];
	String o4[] = new String[20];
	String ans[] = new String[20];
	private JPanel contentPane;
	JRadioButton rdbtnNewRadioButton = new JRadioButton("Option 1");
	JRadioButton rdbtnOption = new JRadioButton("Option 2");
	JRadioButton rdbtnOption_1 = new JRadioButton("Option 3");
	JRadioButton rdbtnOption_2 = new JRadioButton("Option 4");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JButton Next = new JButton("Next");
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private static String getRand(){
		
		Random r = new Random();
		int r1 = r.nextInt(10);
		String id = (String.valueOf(System.currentTimeMillis())
				.substring(11,13))
				.concat(String.valueOf(r1));
		char c = (char) ('a' + r.nextInt(26));
		char c2 = (char) ('a' + r.nextInt(26));
		return c+id+c2;
	}
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testsdb", "root", "");
			con2 = DriverManager.getConnection("jdbc:mysql://localhost/responsesdb", "root", "");
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
					TestBuilderForm frame = new TestBuilderForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestBuilderForm() {
		setResizable(false);
		setTitle("Create a test");
		String randname = getRand();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Create Test");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 25));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("Option 1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Option 2");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Option 3");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Option 4");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Answer");
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setColumns(10);
		
		
		Next.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Finish");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Next.doClick();
				Next.setEnabled(false);
				btnNewButton.setEnabled(false);
				try {
					String q1 = "CREATE TABLE $tableName("
							+ "id INT AUTO_INCREMENT, "
							+ "qname VARCHAR(255),"
							+ "qop1 VARCHAR(255),"
							+ "qop2 VARCHAR(255),"
							+ "qop3 VARCHAR(255),"
							+ "qop4 VARCHAR(255),"
							+ "qans VARCHAR(255),"
							+ "primary key (id));";
					String q2 =q1.replace("$tableName", randname );
					pst = con.prepareStatement(q2);
					pst.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				ArrayList len = new ArrayList(Arrays.asList(qs));
				len.removeAll(Collections.singleton(null)); 

				for(int i=0;i<len.size();i++){
					try {
						String q3 = ("INSERT INTO $tableName (`qname`, `qop1`, `qop2`, `qop3`, `qop4`, `qans`) VALUES (?,?,?,?,?,?);");
						String q4 = q3.replace("$tableName", randname );
						pst = con.prepareStatement(q4);
						pst.setString(1, qs[i]);
						pst.setString(2, o1[i]);
						pst.setString(3, o2[i]);
						pst.setString(4, o3[i]);
						pst.setString(5, o4[i]);
						pst.setString(6, ans[i]);
						pst.executeUpdate();
						System.out.println(randname);
						dispose();
						JOptionPane.showMessageDialog(null, "Test Added!!");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Insert error");
						e1.printStackTrace();
					}
				}
				
				SaveQuestions.main(null);
				SaveQuestions.responses(len.size(), qs, randname);
				
				TestCodePage.main(null,randname);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		buttonGroup.add(rdbtnOption);
		rdbtnOption.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
	
		buttonGroup.add(rdbtnOption_1);
		rdbtnOption_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		buttonGroup.add(rdbtnOption_2);
		rdbtnOption_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(200)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(109)
					.addComponent(Next)
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_2))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(textField_1))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_2_1_1_2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(rdbtnNewRadioButton)
								.addGap(26)
								.addComponent(rdbtnOption, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(rdbtnOption_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(rdbtnOption_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(203)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(192))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2_1_1_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(rdbtnNewRadioButton))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnOption_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(rdbtnOption_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(rdbtnOption, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(Next)
							.addGap(13))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(2))))
		);
		contentPane.setLayout(gl_contentPane);
		
		lblNewLabel_1.setText(String.valueOf(ind+1));

		
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isBlank() && textField_1.getText().isBlank() 
						&& textField_2.getText().isBlank() && textField_3.getText().isBlank()
						&& textField_4.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Please fill blank fields.");
				}
				else {
					qs[ind] = textField.getText();
					o1[ind] = textField_1.getText();
					o2[ind] = textField_2.getText();
					o3[ind] = textField_3.getText();
					o4[ind] = textField_4.getText();
					if(rdbtnNewRadioButton.isSelected()) {
						ans[ind]=textField_1.getText();
						}				
					if(rdbtnOption.isSelected()) {
						ans[ind]=textField_2.getText();
						}
					if(rdbtnOption_1.isSelected()) {
						ans[ind]=textField_3.getText();
						}
					if(rdbtnOption_2.isSelected()) {
						ans[ind]=textField_4.getText();
						}
					ind++;
					lblNewLabel_1.setText(String.valueOf(ind+1));
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
				}
			}
		});
		Connect();
	
	}

		
}
