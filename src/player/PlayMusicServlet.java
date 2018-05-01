package player;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DBUtil;
import Entity.Song;

/**
 * Servlet implementation class PlayMusicServlet
 */
@WebServlet("/PlayMusicServlet")
public class PlayMusicServlet extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayMusicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			//session用于在此次会话中保存播放列表
			HttpSession session = request.getSession();
			
			//oldPlayList为此次servlet执行前 播放列表的实例
			ArrayList<Song> oldPlayList = (ArrayList<Song>)session.getAttribute("playList");
			
			//得到songID用于对播放列表的操作,userID用于添加history记录
			request.setCharacterEncoding("UTF-8");
			String userID = (String)session.getAttribute("userID");
			String songID = request.getParameter("songID");
			
			//addFlag为判断点击的曲目是否存在于当前播放列表的flag
			//currentIndex是当前播放曲目的编号,用于切换播放至所点击的曲目
			boolean addFlag = true;
			int currentIndex = 0;
			
			//连接数据库获得歌曲信息
			Connection conn = DBUtil.getConnection();
			String songSql = "select * from song where song_id = ?";
			String historySql = new String();
			PreparedStatement ps;
			ps = conn.prepareStatement(songSql);
			ps.setString(1,songID);
			
			//歌曲实例化
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs != null);
			Song song = new Song();
			song.setId(rs.getString("song_id"));
			song.setName(rs.getString("song_name"));
			song.setSinger(rs.getString("song_singer"));
			song.setAlbum(rs.getString("song_album"));
			song.setUrl(rs.getString("song_url"));
			song.setCover(rs.getString("song_cover"));
			song.setLrc(rs.getString("song_lrc"));
			song.setStyle(rs.getInt("style_id"));
			song.setCategory(rs.getInt("category_id"));
			song.setScene(rs.getInt("scene_id"));
			
			//播放列表不重复及获得currentIndex
			for(int i = 0;i<oldPlayList.size();i++){
				if(song.getId().equals(oldPlayList.get(i).getId())){
					addFlag = false;
					currentIndex = i;
				}
			}
			if(addFlag){
				oldPlayList.add(song);
				currentIndex = oldPlayList.size() - 1;
			}
			
			//varPlayList是播放页面所需的String类型参数
			String varPlayList = PlayMusicServlet.ArrayListToVar(oldPlayList);
			
			//参数设置及播放列表更新
			session.setAttribute("playList", oldPlayList);
			request.setAttribute("varPlayList", varPlayList);
			request.setAttribute("currentIndex", currentIndex);
			
			//history数据库更新
			historySql = "select * from history where users_num = ? and song_id = ?";
			ps = conn.prepareStatement(historySql);
			ps.setString(1, userID);
			ps.setString(2, songID);
			rs = ps.executeQuery();
			//history存在，则次数+1
			if(rs.next()){
				int times = rs.getInt("history_times");
				historySql = "update history set history_times = ? where users_num = ? and song_id = ?";
				ps = conn.prepareStatement(historySql);
				ps.setInt(1, times + 1);
				ps.setString(2, userID);
				ps.setString(3, songID);
				ps.executeUpdate();
			}
			//history不存在，则添加记录，次数为1
			else {
				historySql = "insert into history values(?,?,1)";
				ps = conn.prepareStatement(historySql);
				ps.setString(1, userID);
				ps.setString(2, songID);
				ps.executeUpdate();
			}
			
			
			//参数传递
			RequestDispatcher d = request.getRequestDispatcher("/musicPlay.jsp");
			d.forward(request,response);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//varPL是varPlayList的缩写,该方法用于将ArrayList存储的播放列表转换为播放器需要的参数
	protected static String ArrayListToVar(ArrayList<Song>playList){
		String varPL = "[";
		int size = playList.size();
		for(int i = 0;i < size;i++){
			String music = "{title:'" + playList.get(i).getName() + "',singer:'" 
				+ playList.get(i).getSinger() + "',audio:'" + playList.get(i).getUrl()
				+ "',thumbnail:'" + playList.get(i).getCover() +"',lyric:'" + playList.get(i).getLrc()+"'},";
			varPL += music;
		}
		varPL += "]";
		return varPL;
	}

}
