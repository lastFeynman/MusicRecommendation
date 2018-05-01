import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date; 
import java.util.Random;

import DB.DBUtil;
public class History {
	public static void main(String args[]){
		try {
			Random rand = new Random();
			Connection conn = DBUtil.getConnection();
			String sql = "insert into history values(?,?,?)";
			for(int i = 1;i < 7;i++){
				for(int j = 1;j < 5;j++){
					for(int k = 1;k < 5;k++){
						for(int count = 0;count < 6;count++){
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setString(1, "user001");
							ps.setString(2, i + "" + j + "" + k + "" + "000" +count);
							ps.setInt(3, rand.nextInt(9999) + 1);
							ps.executeQuery();
							ps.close();
						}
					}
				}
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
