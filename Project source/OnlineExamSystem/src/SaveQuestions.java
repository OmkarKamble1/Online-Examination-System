import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SaveQuestions {
	static Connection con;
	static PreparedStatement pst;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/responsesdb", "root", "");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to connect, check your connection!");
		}
	}
	
	public SaveQuestions(){
		Connect();
	}

	static void responses(int len, String qts[], String randname) {
		//String qs[] = {"Question 1", "Question 2", "Question 3" };
		//String ra = "aaaw13w";
		System.out.println(len + randname);
		System.out.println(qts);
		 try {
				String q1 = "CREATE TABLE $tblname ("
						+ "id INT AUTO_INCREMENT PRIMARY KEY,"
						+ "stname VARCHAR(255));";
				String q2 = q1.replace("$tblname", randname);
				pst = con.prepareStatement(q2);
				pst.executeUpdate();
				System.out.println("table created");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			String q3 = "ALTER TABLE $tblname ADD `$col` VARCHAR(255);";	
			String q4 = q3.replace("$tblname", randname);
			for(int i=0; i < len; i++) {
				String q5 = q4.replace("$col", qts[i]);
				try {
					pst = con.prepareStatement(q5);
					pst.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}				
			System.out.println("cols added");
	}
	public static void main(String[] args) {
		SaveQuestions object = new SaveQuestions();
	}

}
