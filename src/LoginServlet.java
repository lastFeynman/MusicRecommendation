
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import Entity.User;


@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final int NORMAL = 1;
	private static final int MANAGER = 2;
	
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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

				
				
				String username = request.getParameter("username");
				String password = request.getParameter("password");

				Connection conn = DBUtil.getConnection();
				int flag = new DBUtil().LoginSearch(username,password);
				
				if(flag == NORMAL ) {
					session.setAttribute("loginName", username);
					RequestDispatcher d = request.getRequestDispatcher("mainPage.jsp");
					d.forward(request,response);
				}
				else if(flag == MANAGER){
					session.setAttribute("loginName", username);
					RequestDispatcher d = request.getRequestDispatcher("manage.jsp");
					d.forward(request, response);
				}
				else{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('√‹¬Î¥ÌŒÛ£¨«Î÷ÿ–¬ ‰»Î'); window.location='login.jsp'</script>");
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
