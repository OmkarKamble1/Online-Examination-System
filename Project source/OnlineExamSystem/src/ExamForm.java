import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExamForm extends JFrame {
	
	static String studname;
	static String tcode;
	
	private JPanel contentPane;
	ArrayList<String> dbq = new ArrayList<String>();
	ArrayList<String> dbo1 = new ArrayList<String>();
	ArrayList<String> dbo2 = new ArrayList<String>();
	ArrayList<String> dbo3 = new ArrayList<String>();
	ArrayList<String> dbo4 = new ArrayList<String>();
	ArrayList<String> dbans = new ArrayList<String>();
	ArrayList<String> ans = new ArrayList<String>();
	ArrayList<String> finalans = new ArrayList<String>();
	
	JButton btnNext = new JButton("Next");
	JLabel lblNewLabel;
	JTextArea textq;
	JTextArea textop2;
	JTextArea textop3;
	JTextArea textop4;
	JTextArea textop1;
	String name = "name here";
	static int rows = 0;
	int num=0,total=0;
	static Connection con;
	Connection con2;
	static PreparedStatement pst;
	PreparedStatement pst2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
					frame.getTest();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testsdb", "root", "");
			con2 = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to connect, check your connection!");
			System.exit(0);
		}
	}
	
	public void getTest() {
		
		ResultSet r1 = null;
		String q1 = "select * from `$tbl`";
		String q2 = q1.replace("$tbl", tcode);
		try {
			pst = con.prepareStatement(q2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			r1 = pst.executeQuery(q2);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		try {
			while(r1.next()) {
				dbq.add(r1.getString(2));
				dbo1.add(r1.getString(3));
				dbo2.add(r1.getString(4));
				dbo3.add(r1.getString(5));
				dbo4.add(r1.getString(6));
				dbans.add(r1.getString(7));				
				rows++;
			}
		} catch (SQLException e) {
			System.out.println("rows error");
		}
		
		textq.setText(dbq.get(0));
		textop1.setText(dbo1.get(0));
		textop2.setText(dbo2.get(0));
		textop3.setText(dbo3.get(0));
		textop4.setText(dbo4.get(0));
	
	}
		
	public ExamForm() {
		Connect();
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ButtonGroup btngrp = new ButtonGroup();
		
		lblNewLabel = new JLabel("1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 35));
		
		textq = new JTextArea();
		textq.setLineWrap(true);
		textq.setFont(new Font("Century Schoolbook", Font.BOLD, 30));
		textq.setText("SAMPLE QUESTION");
		textq.setWrapStyleWord(true);
		textq.setEditable(false);
		
		JRadioButton rd2 = new JRadioButton("");
		rd2.setFont(new Font("Century Schoolbook", Font.PLAIN, 35));
		
		textop2 = new JTextArea();
		textop2.setWrapStyleWord(true);
		textop2.setEditable(false);
		textop2.setFont(new Font("Century Schoolbook", Font.PLAIN, 25));
		textop2.setText("Option 2");
		
		textop3 = new JTextArea();
		textop3.setText("Option 3");
		textop3.setFont(new Font("Century Schoolbook", Font.PLAIN, 25));
		
		JRadioButton rd3 = new JRadioButton("");
		rd3.setFont(new Font("Century Schoolbook", Font.PLAIN, 35));
		
		textop4 = new JTextArea();
		textop4.setText("Option 4");
		textop4.setFont(new Font("Century Schoolbook", Font.PLAIN, 25));
		
		JRadioButton rd4 = new JRadioButton("");
		rd4.setFont(new Font("Century Schoolbook", Font.PLAIN, 35));
		
		textop1 = new JTextArea();
		textop1.setWrapStyleWord(true);
		textop1.setEditable(false);
		textop1.setText("Option 1");
		textop1.setFont(new Font("Century Schoolbook", Font.PLAIN, 25));
		
		JRadioButton rd1 = new JRadioButton("");
		rd1.setFont(new Font("Century Schoolbook", Font.PLAIN, 35));
		
	
		JButton btnNewButton = new JButton("SUBMIT");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int marks=0;
					for(int i=0;i<ans.size();i++) {
							if(ans.get(i).equals(dbans.get(i)) == true ) {
							marks ++;
						}
						System.out.println(ans.get(i).equals(dbans.get(i)));
					}
					getContentPane().removeAll();
					repaint();
					JLabel label = new JLabel("TOTAL MARKS: "+ marks);
					label.setFont(new Font("Arial", Font.BOLD, 20));
					JOptionPane.showMessageDialog(null, label);
					dispose();
					System.exit(0);
				}
			
			
		});
		btnNewButton.setFont(new Font("Eras Demi ITC", Font.BOLD, 30));
		
		btngrp.add(rd1);
		btngrp.add(rd2);
		btngrp.add(rd3);
		btngrp.add(rd4);
		
		btnNext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(rows == num) {
					btnNext.setEnabled(false);
					System.out.println(ans);
					JOptionPane.showMessageDialog(null, "Test is over,please submit the test");
				}
				if(rows > num) {
					System.out.println(num);
					if(num != 0) {
					System.err.println(num);
					lblNewLabel.setText(String.valueOf(num+1));
					textq.setText(dbq.get(num));
					textop1.setText(dbo1.get(num));
					textop2.setText(dbo2.get(num));
					textop3.setText(dbo3.get(num));
					textop4.setText(dbo4.get(num));
					System.out.println("done");
					}
					
					if(rd1.isSelected()) {
						ans.add(textop1.getText());
					}
					if(rd2.isSelected()) {
						ans.add(textop2.getText());
					}
					if(rd3.isSelected()) {
						ans.add(textop3.getText());
					}
					if(rd4.isSelected()) {
						ans.add(textop4.getText());
					}
					
					num+=1;
					btngrp.clearSelection();
				}
				else {
					System.out.println("end");
				}
				
				System.out.println(ans);
				}		
		});
		System.out.println(dbq);
		btnNext.setFont(new Font("Eras Demi ITC", Font.BOLD, 30));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(145)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rd4, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textop4, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addGap(722))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rd1, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(textop1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(722))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rd3, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(textop3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rd2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(textop2, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
							.addGap(722))
						.addComponent(textq, GroupLayout.PREFERRED_SIZE, 865, GroupLayout.PREFERRED_SIZE))
					.addGap(204))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(464)
					.addComponent(btnNewButton)
					.addGap(102)
					.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(477, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(textq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(125)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(rd1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(textop1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textop2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rd2))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(rd3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(textop3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(rd4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addComponent(textop4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(85)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNext))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setUndecorated(true);
	}
}
