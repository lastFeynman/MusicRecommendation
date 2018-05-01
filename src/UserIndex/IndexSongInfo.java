package UserIndex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;

import DB.DBUtil;
import Entity.Song;

/**
 * Servlet implementation class IndexSongInfo
 */
@WebServlet("/IndexSongInfo")
public class IndexSongInfo extends HttpServlet {
	private static final int DEFAULT_LISTSIZE = 20;
    private static final double CATEGORY_WEIGHT = 0.25;
    private static final double STYLE_WEIGHT = 0.65;
    private static final double SCENE_WEIGHT = 0.15;
    
    Connection conn;
    Connection dbConnection;
	Statement dbStmt,dbStmtFinal,dbStmtAdd;
	PreparedStatement ps;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexSongInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到用户名并传递用户id
		try{
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			String userName = new String();
			String userID = (String)session.getAttribute("userID");
			conn = DBUtil.getConnection();
			String sql = "select users_name from users where users_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			if(rs != null){
				if(rs.next()){
					userName = rs.getString("users_name");
				}
			}
			request.setAttribute("userName", userName);
			
			//歌曲信息数组
			String[][][][] albumCover = new String[3][3][6][4];
			String[][][][] songName = new String[3][3][6][4];
			String[][][][] songSinger = new String[3][3][6][4];
			String[][][][] songID = new String[3][3][6][4];
			
			ResultSet songInfo;
			//推荐部分
			songInfo = Recommend(userID);
			if(songInfo != null){
				for(int j=0;j<4;j++){
					if(songInfo.next()){
						albumCover[0][0][0][j]=songInfo.getString("Song_cover");
						songName[0][0][0][j]=songInfo.getString("Song_name");
						songSinger[0][0][0][j]=songInfo.getString("Song_singer");
						songID[0][0][0][j]=songInfo.getString("Song_Id");
						
						request.setAttribute("albumCover"+"000"+j, albumCover[0][0][0][j]);
						request.setAttribute("songName"+"000"+j, songName[0][0][0][j]);
						request.setAttribute("songSinger"+"000"+j, songSinger[0][0][0][j]);
						request.setAttribute("songID"+"000"+j, songID[0][0][0][j]);
					}
				}
			}
			
			//分类部分
			//曲风
			for(int i=0;i<6;i++){
				songInfo = getSongInfo(2,"Style_Id",i+1);
				if(songInfo != null){
					for(int j=0;j<4;j++){
						if(songInfo.next()){
							albumCover[1][0][i][j]=songInfo.getString("Song_cover");
							songName[1][0][i][j]=songInfo.getString("Song_name");
							songSinger[1][0][i][j]=songInfo.getString("Song_singer");
							songID[1][0][i][j]=songInfo.getString("Song_Id");
							
							request.setAttribute("albumCover"+10+(i)+j, albumCover[1][0][i][j]);
							request.setAttribute("songName"+10+(i)+j, songName[1][0][i][j]);
							request.setAttribute("songSinger"+10+(i)+j, songSinger[1][0][i][j]);
							request.setAttribute("songID"+10+(i)+j, songID[1][0][i][j]);
						}
					}
				}
			}
			//语种
			for(int i=0;i<4;i++){
				songInfo = getSongInfo(2,"Category_Id",i+1);
				if(songInfo != null){
					for(int j=0;j<4;j++){
						if(songInfo.next()){
							albumCover[1][1][i][j]=songInfo.getString("Song_cover");
							songName[1][1][i][j]=songInfo.getString("Song_name");
							songSinger[1][1][i][j]=songInfo.getString("Song_singer");
							songID[1][1][i][j]=songInfo.getString("Song_Id");
							
							request.setAttribute("albumCover"+11+(i)+j, albumCover[1][1][i][j]);
							request.setAttribute("songName"+11+(i)+j, songName[1][1][i][j]);
							request.setAttribute("songSinger"+11+(i)+j, songSinger[1][1][i][j]);
							request.setAttribute("songID"+11+(i)+j, songID[1][1][i][j]);
						}
					}
				}
			}
			//场景
			for(int i=0;i<4;i++){
				songInfo = getSongInfo(2,"Scene_Id",i+1);
				if(songInfo != null){
					for(int j=0;j<4;j++){
						if(songInfo.next()){
							albumCover[1][2][i][j]=songInfo.getString("Song_cover");
							songName[1][2][i][j]=songInfo.getString("Song_name");
							songSinger[1][2][i][j]=songInfo.getString("Song_singer");
							songID[1][2][i][j]=songInfo.getString("Song_Id");
							
							request.setAttribute("albumCover"+12+(i)+j, albumCover[1][2][i][j]);
							request.setAttribute("songName"+12+(i)+j, songName[1][2][i][j]);
							request.setAttribute("songSinger"+12+(i)+j, songSinger[1][2][i][j]);
							request.setAttribute("songID"+12+(i)+j, songID[1][2][i][j]);
						}
					}
				}
			}
			
			//热门部分
			songInfo = getSongInfo(3,null,0);
			if(songInfo != null){
				for(int j=0;j<4;j++){
					if(songInfo.next()){
						albumCover[2][0][0][j]=songInfo.getString("Song_cover");
						songName[2][0][0][j]=songInfo.getString("Song_name");
						songSinger[2][0][0][j]=songInfo.getString("Song_singer");
						songID[2][0][0][j]=songInfo.getString("Song_Id");
									
						request.setAttribute("albumCover"+200+j, albumCover[2][0][0][j]);
						request.setAttribute("songName"+200+j, songName[2][0][0][j]);
						request.setAttribute("songSinger"+200+j, songSinger[2][0][0][j]);
						request.setAttribute("songID"+200+j, songID[2][0][0][j]);
					}		
				}
			}
			
					dbConnection.close();
					dbStmt.close();
					dbStmtFinal.close();
					dbStmtAdd.close();
					ps.close();
					conn.close();
			RequestDispatcher d = request.getRequestDispatcher("/UserIndex.jsp");
			d.forward(request, response);		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	protected ResultSet Recommend(String userID){
		
		ResultSet rs = null;
		try{
			String sql = new String();
			conn = DBUtil.getConnection();
			
			ResultSet userResult = null;
			int favSongCategory = 1,favSongStyle = 1,favSongScene = 1;
			ResultSet songInfo = null;
			int listSize = DEFAULT_LISTSIZE;
			
			String pointID = null; //距离最小的四首ID
			int pointCategory = 0,pointStyle = 0,pointScene = 0;  //距离最小的四首距离
			double pointSum = 0;
			
			sql = "select Song_Id from History where Users_Num = ? order by History_times desc";//找到最常听音乐ID
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, userID);
			userResult = ps.executeQuery();
			int songAmount;
			
			if(userResult.next()){
				userResult.last();
				songAmount = userResult.getRow();
			}
			else{
				songAmount = 0;
			}
			
			//history不空，根据历史记录推荐
			if(songAmount >= 4){
				userResult.first();
				
				//找到最常听音乐坐标
				sql = "select Style_id,category_id,scene_id,history_times from history left join song on history.song_id = song.song_id where users_num = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, userID);
				songInfo = ps.executeQuery();
				
				//historyCount初始化为0
				int historyCount[][][] = new int[6][4][4];
				for(int i = 0;i < 6;i++){
					for(int j = 0;j < 4;j++){
						for(int k = 0;k < 4;k++){
							historyCount[i][j][k] = 0;
						}
					}
				}
				
				//统计各种类歌曲历史记录
				int styleID,sceneID,categoryID;
				int times = 0;
				while(songInfo.next()){
					styleID = songInfo.getInt("style_id");
					sceneID = songInfo.getInt("scene_id");
					categoryID = songInfo.getInt("category_id");
					historyCount[styleID - 1][sceneID - 1][categoryID - 1] += songInfo.getInt("history_times");
					times++;
				}
				
				//找到听的次数最多的一类歌曲
				int maxTimes = 0;
				for(int i = 0;i < 6;i++){
					for(int j = 0;j < 4;j++){
						for(int k = 0;k < 4;k++){
							if(historyCount[i][j][k] > maxTimes){
								maxTimes = historyCount[i][j][k];
								favSongStyle = i;
								favSongScene = j;
								favSongCategory = k;
							}
						}
					}
				}
				
				System.out.println("Style:" + (favSongStyle + 1));
				System.out.println("Category:" + (favSongCategory + 1));
				System.out.println("Scene:" + (favSongScene + 1));
				sql = "select * from Song";
				ps = conn.prepareStatement(sql);
				songInfo = ps.executeQuery();
	
				ArrayList<Song> songList = new ArrayList<Song>();
				
				//计算所有歌曲的point值，并将歌曲存入一个List中，准备排序
				while(songInfo.next()){
					Song song = new Song();
					
					pointCategory = songInfo.getInt("Category_Id") - 1;
					pointStyle = songInfo.getInt("Style_Id") - 1;
					pointScene = songInfo.getInt("Scene_Id") - 1;
					pointSum = CATEGORY_WEIGHT*((double)((pointCategory-favSongCategory)*(pointCategory-favSongCategory)))+STYLE_WEIGHT*((double)((pointStyle-favSongStyle)*(pointStyle-favSongStyle)))+SCENE_WEIGHT*((double)((pointScene-favSongScene)*(pointScene-favSongScene)));
					pointID = songInfo.getString("Song_Id");
					
					song.setId(pointID);
					song.point = pointSum;
					songList.add(song);
				}
				
				//所有歌曲按point值排序
				double minPoint = 1000;
				Song temp = new Song();
				for(int i = songList.size();i > 0;i--){
					for(int j = 0;j < i - 1;j++){
						if(songList.get(j).point > songList.get(j + 1).point){
							temp = songList.get(j);
							songList.set(j, songList.get(j + 1));
							songList.set(j + 1, temp);
						}
					}
					minPoint = songList.get(songList.size() - 1).point;
				}
				
				if(songList.size() < listSize){
					listSize = songList.size();
				}
				
				//得到4个小于listSize的随机数，使推荐列表多样化
				int randomID[] = randomGet(listSize,4);
				
				sql = "select * from Song where Song_Id = ? or Song_Id = ? or Song_Id = ? or Song_Id = ?";
				ps = conn.prepareStatement(sql);
				for(int i = 0;i < 4;i++){
					ps.setString(i + 1, songList.get(randomID[i]).getId());
				}
				rs = ps.executeQuery();
			}
			
			//历史记录为空，根据歌曲的新鲜度（生日）排序;
			else{
				sql = "select * from Song order by song_updateTime DESC";
				ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				rs.last();
				
				if(rs.getRow() < DEFAULT_LISTSIZE){
					listSize = rs.getRow();
				}
				else{
					listSize = DEFAULT_LISTSIZE;
				}
				
				rs.first();
				String candidateID[] = new String[listSize];
				for(int i = 0;i < listSize;i++){
					candidateID[i] = rs.getString("song_id");
					rs.next();
				}
				
				//得到4个小于候选ID个数的随机数，使推荐列表多样化
				int randomID[] = randomGet(listSize,4);
				String recommendID[] = new String[4];
				for(int i = 0;i < 4;i++){
					recommendID[i] = candidateID[randomID[i]];
				}
				
				sql = "select * from song where song_id = ? or song_id = ? or song_id = ? or song_id = ?";
				ps = conn.prepareStatement(sql);
				for(int i = 0;i < 4;i++){
					ps.setString(i + 1, recommendID[i]);
				}
				rs = ps.executeQuery();
			}
		}catch(Exception e){}
		
		return rs;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
	public ResultSet getSongInfo(int position,String sortMethod,int typeID){
		//连接数据库
		ResultSet dbResults = null;
		try{
			dbConnection = DBUtil.getConnection();
			dbStmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			dbStmtFinal = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sqlstr = null;
			
			if(position==2){
				//分类部分
				String maxID1=null,maxID2=null,maxID3=null,maxID4=null,historyID=null; //最高四条记录的Song_Id
				int maxSum1=-1,maxSum2=-1,maxSum3=-1,maxSum4=-1,historySum=-1;  //最高四条记录的记录和
			
				ResultSet sortResult =null; //某分类所有音乐
				ResultSet historySumResult =null; //单首歌记录总数
				sqlstr="select Song_Id from Song where "+sortMethod+" = "+typeID;
				sortResult=dbStmt.executeQuery(sqlstr); //找到该分类所有音乐
				
				while(sortResult.next()){
					Statement dbStmtSum = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					historyID = sortResult.getString("Song_Id");
					
					sqlstr="select sum(History_times) from History where Song_Id = '"+historyID+"'";
					historySumResult = dbStmtSum.executeQuery(sqlstr);
					
					historySumResult.next();
					if(historySumResult.getString(1)==null)
						historySum = 0;
					else
						historySum = historySumResult.getInt(1);
				
					if(historySum>maxSum4){
				
						if(historySum>maxSum3){
						
							if(historySum>maxSum2){
							
								if(historySum>maxSum1){
									maxID4 = maxID3;
									maxID3 = maxID2;
									maxID2 = maxID1;
									maxID1 = historyID;
								
									maxSum4 = maxSum3;
									maxSum3 = maxSum2;
									maxSum2 = maxSum1;
									maxSum1 = historySum;
								}else{
									maxID4 = maxID3;
									maxID3 = maxID2;
									maxID2 = historyID;
								
									maxSum4 = maxSum3;
									maxSum3 = maxSum2;
									maxSum2 = historySum;
								}
							
							}else{
								maxID4 = maxID3;
								maxID3 = historyID;
							
								maxSum4 = maxSum3;
								maxSum3 = historySum;
							}
						
						}else{
							maxID4 = historyID;
							maxSum4 = historySum;
						}
					
					}
					dbStmtSum.close();
				}
			
				sqlstr = "select * from Song where Song_Id = '"+maxID1+"' or Song_Id = '"+maxID2+"' or Song_Id = '"+maxID3+"' or Song_Id = '"+maxID4+"'";
			}else{
				//热门
				ResultSet hotResult=null,hotResultAdd=null;
				String[] maxID=new String[4];
				int rowNum;
				int[] randomNum;
				String sqlstradd;
				dbStmtAdd = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				sqlstr = "select Song_Id,sum(History_times) from History group by Song_Id order by sum(History_times) desc";
				hotResult = dbStmt.executeQuery(sqlstr);
				hotResult.last();
				rowNum = hotResult.getRow();
				hotResult.first();
				
				if(rowNum<4){
					for(int i=0;i<rowNum;i++){
						if(i!=0)
							hotResult.next();
						maxID[i] = hotResult.getString("Song_Id");
					}
					
					if(rowNum==0)
						sqlstradd = "select * from Song order by SONG_UPDATETIME desc";
					else if(rowNum==1)
						sqlstradd = "select * from Song where Song_Id != '"+maxID[0]+"' order by SONG_UPDATETIME desc";
					else if(rowNum==2)
						sqlstradd = "select * from Song where Song_Id != '"+maxID[0]+"' and Song_Id != '"+maxID[1]+"' order by SONG_UPDATETIME desc";
					else if(rowNum==3)
						sqlstradd = "select * from Song where Song_Id != '"+maxID[0]+"' and Song_Id != '"+maxID[1]+"' and Song_Id != '"+maxID[2]+"' order by SONG_UPDATETIME desc";
					else
						sqlstradd = "select * from Song where Song_Id != '"+maxID[0]+"' and Song_Id != '"+maxID[1]+"' and Song_Id != '"+maxID[2]+"' and Song_Id != '"+maxID[3]+"' order by SONG_UPDATETIME desc";
					
					hotResultAdd = dbStmtAdd.executeQuery(sqlstradd);
					
					for(int i=0;i<4-rowNum;i++){
						hotResultAdd.next();
						maxID[rowNum+i] = hotResultAdd.getString("Song_Id");
					}
				}else{
					if(rowNum>20)
						randomNum = randomGet(20,4);
					else
						randomNum = randomGet(rowNum,4);
					for(int i=0;i<4;i++){
						for(int j=0;j<randomNum[i];j++)
							hotResult.next();
						
					maxID[i] = hotResult.getString("Song_Id");
					hotResult.first();
					}
				}
				sqlstr = "select * from Song where Song_Id = '"+maxID[0]+"' or Song_Id = '"+maxID[1]+"' or Song_Id = '"+maxID[2]+"' or Song_Id = '"+maxID[3]+"'";
			}
		
			
			dbResults = dbStmtFinal.executeQuery(sqlstr);
		}catch (Exception e){}
		
		
		return dbResults;
	}
	
public static int[] randomGet(int max, int n){  
		
		final int expand = 100;
		
	    if (n > max) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * max * expand); 
	        if(num == max)
	        	continue;
	        
	        double range = ((-4)*expand+Math.sqrt((double)(16*expand*expand)-(16*(double)expand*num)/max))/(((-4)*(double)expand)/max)+1;
	        num = (int)range;
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}  

}
