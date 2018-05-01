package Servlet;

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


@WebServlet(name = "AdminSearchServlet", urlPatterns = "/AdminSearchServlet")
public class AdminSearchServlet extends HttpServlet {

	public AdminSearchServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				HttpSession session = request.getSession();
			try {				
				
				request.setCharacterEncoding("UTF-8");
				String songInfo = (String)request.getAttribute("songInfo");
				
				String sql = "select song_id,song_name,song_singer,song_album from song "
						+ "where song_id = ? or song_name like ? "
						+ "or song_singer like ? or song_album like ?";
				
				PreparedStatement ps;
				Connection conn = DBUtil.getConnection();
				if(conn != null){
					ps = conn.prepareStatement(sql);
					ps.setString(1,songInfo);
					for(int i = 2;i < 5;i++){
						ps.setString(i, "%"+songInfo+"%");
					}
					ResultSet rs = ps.executeQuery();
					List<Song> songList = new ArrayList<Song>();
					if(rs != null){
						while(rs.next()){
							Song song = new Song();
							song.setId(rs.getString("song_id"));
							song.setName(rs.getString("song_name"));
							song.setSinger(rs.getString("song_singer"));
							song.setAlbum(rs.getString("song_album"));
							
							songList.add(song);
						}
					}
					
					request.setAttribute("songInfo", songInfo);
					session.setAttribute("songList", songList);
					RequestDispatcher d = request.getRequestDispatcher("/view.jsp");
					d.forward(request,response);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
