package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

/**
 * Servlet implementation class SignInServlet
 */
/** @WebServlet(name="SignInServlet",urlPatterns="/SignInServlet") **/
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Connection conn = null;// 创建一个数据库连接
	static PreparedStatement pre = null;// 创建一个预编译语句对象,一般都是用这个而不用Statement
	static ResultSet result = null;// 创建一个结果集对象
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:" + "thin:@localhost:1521:orcl";
	// private static String url="jdbc:oracle:"+"thin:localhost:1521:oracle";
	private static String username = "MUSIC";// 用户名
	private static String password = "123456";// 密码

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//中文乱码解决
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取用户输入信息
		String us = request.getParameter("username");
		String pw = request.getParameter("password");
		String pw1 = request.getParameter("password1");
		System.out.println(us);
		System.out.println(pw);
		System.out.println(pw1);
                // 长度超过20给予提示
		if (us.length() > 20 || pw.length() > 20) {
			String LengthOver = "用户名和密码不能超过20位";
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("tishi", "<script>alert('" + LengthOver + "')</script>");
			request.getRequestDispatcher("Sign.jsp").forward(request, response);
		}

		Cookie[] pastCookie = request.getCookies();
		int cookieID = 0;

		for (int i = 0; i < pastCookie.length; i++) {
			//cookie存在情况
			if (pastCookie[i].getName().equals("EZmusicCookie")) {
				cookieID = Integer.parseInt(pastCookie[i].getValue());
				String sql = "select * from users where cookie_id = ?";
				try {
					Class.forName(driver).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					pre = conn.prepareStatement(sql);
					pre.setInt(1, cookieID);
					result = pre.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					if (result.next()) {
						String User_Num = result.getString("Users_Num");
						//System.out.print("该游客已经存在，直接更新用户表");
						if (us != null && pw != null && pw1 != null && !"".equals(us) && !"".equals(pw)
								&& !"".equals(pw1)) {
							if (pw.equals(pw1)) {
								String sql_update = "update Users set users_Name= ?,users_password=? where cookie_id=?";
								PreparedStatement pr = null;

								pr = conn.prepareStatement(sql_update);
								pr.setString(1, us);
								pr.setString(2, pw);
								pr.setInt(3, cookieID);
								int re = pr.executeUpdate();
								PrintWriter out = response.getWriter();
								String tishi = "注册成功，请记住您的登录账号为：" + User_Num;
								response.setContentType("text/html;charset=utf-8");
					

								request.setAttribute("tishi", "<script>alert('"+tishi+"')</script>");
								request.getRequestDispatcher("Sign.jsp").forward(request, response);
								//request.setAttribute("userID", User_Num);
								//request.getRequestDispatcher("/IndexSongInfo").forward(request, response);
								
								System.out.println("注册成功,账户为：" + User_Num);
								break;
							} else {
								System.out.println("两次密码不相同");
								String ts="两次密码不相同";
								request.setAttribute("tishi", "<script>alert('"+ts+"')</script>");
								request.getRequestDispatcher("Sign.jsp").forward(request, response);
							}
						} else {
							System.out.println("请输入信息");
						
							String ts="请输入信息";
							request.setAttribute("tishi", "<script>alert('"+ts+"')</script>");
							request.getRequestDispatcher("Sign.jsp").forward(request, response);
							break;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			// 没有对应cookie的时候
			else {
				if (us != null && pw != null && pw1 != null && !"".equals(us) && !"".equals(pw) && !"".equals(pw1)) {
					if (pw.equals(pw1)) {
						try {
							Class.forName(driver).newInstance();
							conn = DriverManager.getConnection(url, username, password);
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
						String sql = "select * from Users order by cookie_id";
						try {
							pre = conn.prepareStatement(sql);
							result = pre.executeQuery();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						boolean newCookie = true;
						System.out.println("0.0");
						try {
							for (int j = 0; result.next(); j++) {
								if (j != result.getInt("cookie_id")) {
									cookieID = j;
									newCookie = false;
									break;
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (newCookie) {
							try {
								result.last();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								cookieID = result.getInt("cookie_id") + 1;

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						//将新生成的记录cookie发送给浏览器
						Cookie cookie = new Cookie("EZmusicCookie", String.valueOf(cookieID));
						String User_Num = String.format("%07d", cookieID + 1);
						cookie.setMaxAge(60 * 60 * 24 * 5);
						response.addCookie(cookie);
						String sql_add = "insert into Users values(?,?,?,?,?)";//根据表需添加修改
						System.out.println("0.0");
						PreparedStatement pr_add = null;

						try {
							Class.forName(driver).newInstance();
							conn = DriverManager.getConnection(url, username, password);
							pr_add = conn.prepareStatement(sql_add);
							pr_add.setString(1, User_Num);
							pr_add.setString(2, us);
							pr_add.setString(3, pw);
							int flag=0;
							pr_add.setInt(4,flag);
							pr_add.setInt(5, cookieID);
							int re_add = pr_add.executeUpdate();
							String tishi = "注册成功，请记住您的登录账号为：" + User_Num;
							response.setContentType("text/html;charset=utf-8");
				

							request.setAttribute("tishi", "<script>alert('"+tishi+"')</script>");
							request.getRequestDispatcher("Sign.jsp").forward(request, response);
							//request.setAttribute("userID", User_Num);
							//request.getRequestDispatcher("/IndexSongInfo").forward(request, response);
							System.out.println("注册成功，账户为:" + User_Num);

							break;
						} catch (SQLException | InstantiationException | IllegalAccessException
								| ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						System.out.println("两次密码不相同");
						String ts="两次密码不相同";
						request.setAttribute("tishi", "<script>alert('"+ts+"')</script>");
						request.getRequestDispatcher("Sign.jsp").forward(request, response);
					}

				} else {
					System.out.println("请输入信息");
					String ts="请输入信息";
					request.setAttribute("tishi", "<script>alert('"+ts+"')</script>");
					request.getRequestDispatcher("Sign.jsp").forward(request, response);
					break;
				}
				break;
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
