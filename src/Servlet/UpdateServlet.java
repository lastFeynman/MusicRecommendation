package Servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

@WebServlet(name = "UpdateServlet", urlPatterns = "/UpdateServlet")
public class UpdateServlet extends HttpServlet{
	
	public UpdateServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try{
			request.setCharacterEncoding("UTF-8");
			
			String songid = request.getParameter("songID");

			int style = Integer.parseInt(request.getParameter("style"));//曲风编号
			int category =  Integer.parseInt(request.getParameter("category"));//语种编号
			int scene =  Integer.parseInt(request.getParameter("scene"));//场景编号
			
			String songName = request.getParameter("songName");//歌曲名
			String singer = request.getParameter("singer");//歌手
			String album = request.getParameter("album");//专辑名
			String url = UploadServlet.UPLOAD_RELATIVE_DIRECTORY + "/" + request.getParameter("url");//歌曲文件
			String cover = UploadServlet.UPLOAD_RELATIVE_DIRECTORY + "/" + request.getParameter("cover");//封面文件
			String lrc = UploadServlet.UPLOAD_RELATIVE_DIRECTORY + "/" + request.getParameter("lrc");//歌词文件
			
			
			String sql="update song set category_id=?,style_id=?,scene_id=?,"
					+ "song_name=?,song_singer=?,song_album=?,song_url=?,song_cover=?,"
					+ "song_lrc=? where song_id=?";
			String [] params=new String[]{songName,singer,album,url,cover,lrc,songid};
			
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps=null;
			
			
			if(conn!=null){
				ps=conn.prepareStatement(sql);
				ps.setInt(1, category);
				ps.setInt(2, style);
				ps.setInt(3, scene);
				
				int i=4;
				for(String param:params){
					ps.setString(i, param);
					i++;
				}
				
				int temp = ps.executeUpdate();
				ps.close();
				conn.close();
				
				if(temp > 0){
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('修改成功！');history.back();</script>");
					out.flush();
					out.close();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request,response);
	}
		public void init() throws ServletException {
			// Put your code here
		}


}
