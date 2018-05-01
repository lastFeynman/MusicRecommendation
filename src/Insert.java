import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date; 

import DB.DBUtil;

public class Insert {
	public static void main(String args[]){
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "insert into song values(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";
			for(int i = 1;i < 7;i++){
				for(int j = 1;j < 5;j++){
					for(int k = 1;k < 5;k++){
						for(int count = 0;count < 6;count++){
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setString(1,i + "" + j + "" + k + "" + "000" +count);
							ps.setInt(2, j);
							ps.setInt(3, i);
							ps.setInt(4, k);
							ps.setString(5, "song" + i + "" + j + "" + k + "" + "000" +count);
							ps.setString(6, "singer");
							ps.setString(7,"album");
							ps.setString(8, "hehe");
							ps.setString(9, "keke");
							ps.setString(10, "haha");
							String randomDate = randomDate("2000-01-01", "2017-01-01");  
							ps.setString(11, randomDate);
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
	
    private static String randomDate(String beginDate, String endDate) {  
        try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            Date start = format.parse(beginDate);// ���쿪ʼ����  
            Date end = format.parse(endDate);// �����������  
            // getTime()��ʾ������ 1970 �� 1 �� 1 �� 00:00:00 GMT ������ Date �����ʾ�ĺ�������  
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
            String date1 = format.format(date);
            
            return date1;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    private static long random(long begin, long end) {  
        long rtn = begin + (long) (Math.random() * (end - begin));  
        // ������ص��ǿ�ʼʱ��ͽ���ʱ�䣬��ݹ���ñ������������ֵ  
        if (rtn == begin || rtn == end) {  
            return random(begin, end);  
        }  
        return rtn;  
    }  
	
}
