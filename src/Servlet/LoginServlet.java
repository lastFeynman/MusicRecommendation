package Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DBUtil;
import Entity.Song;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final int TTL = 60 * 60 * 24;
	private static final int NORMAL = 1;
	private static final int MANAGER = 2;
	private static final String DEFAULT_NAME = "游客";
	private static final String DEFAULT_PASSWORD = "123456";
	
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/*public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			Cookie[] pastCookie = request.getCookies();
			int cookieID = 0;
			for(int i = 0;i < pastCookie.length;i++){
				if(pastCookie[i].getName().equals("EZmusicCookie")){
					cookieID = Integer.parseInt(pastCookie[i].getValue());
					String sql = "select * from users where cookie_id = ?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, cookieID);
					rs = ps.executeQuery();
					
					if(rs.next()){
						System.out.println("该游客已存在，直接游客登录");
						/*
						 * 更新游客登录时间
						 */
						java.util.Date utilDate = new java.util.Date();
						java.sql.Date cookieTime = new java.sql.Date(utilDate.getTime());
						
						Cookie cookie = new Cookie("EZmusicCookie",String.valueOf(cookieID));
						cookie.setMaxAge(TTL);
						
						long t=1000*60L;
						// 记录当前时间，设置TTL时间后执行删除对应Cookie所在记录
						Calendar calender =Calendar.getInstance();
						calender.getTime();//获得当前时间
						calender.add(calender.DAY_OF_YEAR, 5);//时间增加5天
						
						response.addCookie(cookie);
						String userID = rs.getString("users_num");
						String password = rs.getString("users_password");
						int flag = new DBUtil().LoginSearch(userID,password);
						
						if(flag == NORMAL ) {
							System.out.println("游客登录成功，尝试转入下一个页面");
							String sqll="update users set cookie_time=? where cookie_id=?";
							ps.setDate(1, cookieTime);
							ps.setInt(2, cookieID);
							ArrayList<Song> playList = new ArrayList<>();
							session.setAttribute("userID", userID);
							session.setAttribute("playList", playList);
							RequestDispatcher d = request.getRequestDispatcher("/IndexSongInfo");
							d.forward(request,response);
						}
						return;
					}
					
					else{
						break;
					}
				}
			}
			System.out.println("该游客不存在，创建新游客");
			String sql = "select * from users order by cookie_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			boolean newCookie = true;
			for(int i = 0;rs.next();i++){
				if(i != rs.getInt("cookie_id")){
					cookieID = i;
					newCookie = false;
					break;
				}
			}
			
			if(newCookie){
				rs.last();
				cookieID = rs.getInt("cookie_id") + 1;
			}
			
			Cookie cookie = new Cookie("EZmusicCookie",String.valueOf(cookieID));
			cookie.setMaxAge(TTL * 5);						
			response.addCookie(cookie);
			
			long t=1000*60L;
			// 记录当前时间，设置TTL时间后执行删除对应Cookie所在记录
			Calendar calender =Calendar.getInstance();
			calender.getTime();//获得当前时间
			calender.add(calender.DAY_OF_YEAR, 5);//时间增加5天
		
			
			System.out.println("尝试查找游客ID");
			String userID = new String();
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date cookieTime = new java.sql.Date(utilDate.getTime());
			
			sql = "select * from users order by users_num";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			int possibleID;boolean newID = true;
			for(possibleID = 0;rs.next();possibleID++){
				/*
				System.out.println("fuck");
				System.out.println(possibleID);
				System.out.println(Integer.parseInt(rs.getString("users_num")));
				System.out.println("shit");*/
				if(DBUtil.isNumeric(rs.getString("users_num"))){
					if(possibleID != Integer.parseInt(rs.getString("users_num"))){
						userID = String.format("%07d", possibleID);
						System.out.println("用老的用户ID，为" + userID);
						newID = false;
						break;
					}
				}
				else break;
			}
			
			if(newID){
				userID = String.format("%07d", possibleID + 1);
				System.out.println("用新的用户ID，为" + userID);
			}
			
			System.out.println("得到游客id为" + userID);
			sql = "insert into users values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ps.setString(2, DEFAULT_NAME);
			ps.setString(3, DEFAULT_PASSWORD);
			ps.setInt(4, 0);
			ps.setInt(5, cookieID);
			ps.setDate(6, cookieTime);
			rs = ps.executeQuery();
			System.out.println("普通游客尝试插入");
			
			String password = DEFAULT_PASSWORD;
			int flag = new DBUtil().LoginSearch(userID,password);
			
			if(flag == NORMAL ) {
				System.out.println("普通游客登录成功");
				ArrayList<Song> playList = new ArrayList<>();
				session.setAttribute("userID", userID);
				session.setAttribute("playList", playList);
				RequestDispatcher d = request.getRequestDispatcher("/IndexSongInfo");
				d.forward(request,response);
			}
		}catch(Exception e){}
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
				
				String userID = request.getParameter("userID");
				String password = request.getParameter("password");

				Connection conn = DBUtil.getConnection();
				int flag = new DBUtil().LoginSearch(userID,password);
				
				if(flag == NORMAL ) {
					ArrayList<Song> playList = new ArrayList<>();
					session.setAttribute("userID", userID);
					session.setAttribute("playList", playList);
					RequestDispatcher d = request.getRequestDispatcher("/IndexSongInfo");
					d.forward(request,response);
				}
				else if(flag == MANAGER){
					RequestDispatcher d = request.getRequestDispatcher("manage.jsp");
					d.forward(request, response);
				}
				else{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('密码错误，请重新输入'); window.location='login.jsp'</script>");
					out.flush();
					out.close();
				}

				conn.close();
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {
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
