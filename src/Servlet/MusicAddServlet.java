package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DBUtil;
import Servlet.UploadServlet;


@WebServlet(name = "MusicAddServlet", urlPatterns = "/MusicAddServlet")
public class MusicAddServlet extends HttpServlet {

	public MusicAddServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
				Connection conn = DBUtil.getConnection();
				PreparedStatement ps;
				
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String singer = request.getParameter("singer");
				String album = request.getParameter("album");
				
				String url = UploadServlet.UPLOAD_RELATIVE_DIRECTORY + "/" +request.getParameter("url");
				String cover = UploadServlet.UPLOAD_RELATIVE_DIRECTORY + "/" + request.getParameter("cover");
				String lrc = UploadServlet.UPLOAD_RELATIVE_DIRECTORY + "/" + request.getParameter("lrc");
				
				int style = Integer.parseInt(request.getParameter("style"));
				int scene = Integer.parseInt(request.getParameter("scene"));
				int category = Integer.parseInt(request.getParameter("category"));
				
				Date dNow = new Date( );
			    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			    String date = ft.format(dNow);
			    
			    int repeatFlag = 0;
			    String sql = "select song_id from song where song_id = ?";
			    ps = conn.prepareStatement(sql);
			    ps.setString(1, id);
			    ResultSet rs = ps.executeQuery();
			    if(rs != null){
			    	if(rs.next()){
			    		repeatFlag = 1;
			    		rs.close();
			    	}
			    }
			    
			    if(repeatFlag == 1){
			    	response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('该歌曲ID已被占用，请尝试使用其他ID'); window.location='addMusic.jsp'</script>");
					out.flush();
					out.close();
					ps.close();
					conn.close();
					return;
			    }
			    
			    sql = "Insert into song values(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";
			    String[] RestParams = new String[]{name,singer,album,url,cover,lrc,date};
				
				
				if (conn != null) {
						ps = conn.prepareStatement(sql);
						ps.setString(1, id);
						ps.setInt(2, category);
						ps.setInt(3, style);
						ps.setInt(4, scene);
						
						int i = 5;
						for(String param : RestParams){
							ps.setString(i, param);
							i++;
						}
						int consequence = ps.executeUpdate();
						
						ps.close();
						conn.close();
						if(consequence > 0){
							response.setContentType("text/html;charset=utf-8");
							PrintWriter out = response.getWriter();
							out.print("<script>alert('添加音乐记录成功'); window.location='addMusic.jsp'</script>");
							out.flush();
							out.close();
						}
						else{
							response.setContentType("text/html;charset=utf-8");
							PrintWriter out = response.getWriter();
							out.print("<script>alert('添加音乐记录失败'); window.location='addMusic.jsp'</script>");
							out.flush();
							out.close();
						}
					}
				}catch (Exception e) {
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
