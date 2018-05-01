package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	static Connection conn = null;
	private static String username = "system";
	private static String password = "123456";
	
	private static final int WRONG_PASSWORD = -2;
	private static final int NO_USER = -1;
	private static final int FAILED = 0;
	private static final int NORMAL= 1;
	private static final int MANAGER = 2;
	
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:MusicTest";

	public static Connection getConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	public int LoginSearch(String userID, String password)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		String str = "select * from users where users_num=?";
		PreparedStatement ps;
		int flag = FAILED;
		boolean temp = false;
		Connection conn = getConnection();

		if (conn != null) {
			try {
				//ps是conn连接中准备执行的sql语句
				ps = conn.prepareStatement(str);
				
				//将sql语句中为“？”的部分替换成用户名和密码
				ps.setString(1, userID);

				//rs是结果集，flag是登陆情况
				ResultSet rs = ps.executeQuery();
				temp = rs.next();

				if(temp){
					if(rs.getString("users_password").equals(password)){
						if(rs.getInt("users_managerflag") == 1){
							flag = MANAGER;
						}
						else{
							flag = NORMAL;
						}
					}	
					else{
						flag = WRONG_PASSWORD;
					}
				}
				else{
					flag = NO_USER;
				}
				ps.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if(conn != null)conn.close();
		return flag;
	}

	public static boolean isNumeric(String str){  
		  for (int i = str.length();--i>=0;){    
			  if (!Character.isDigit(str.charAt(i))){  
				  return false;  
			  }  
		  }  
		  return true;  
	} 
}
