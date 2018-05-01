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

	static Connection conn = null;// ����һ�����ݿ�����
	static PreparedStatement pre = null;// ����һ��Ԥ����������,һ�㶼�������������Statement
	static ResultSet result = null;// ����һ�����������
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:" + "thin:@localhost:1521:orcl";
	// private static String url="jdbc:oracle:"+"thin:localhost:1521:oracle";
	private static String username = "MUSIC";// �û���
	private static String password = "123456";// ����

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
	
		//����������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//��ȡ�û�������Ϣ
		String us = request.getParameter("username");
		String pw = request.getParameter("password");
		String pw1 = request.getParameter("password1");
		System.out.println(us);
		System.out.println(pw);
		System.out.println(pw1);
                // ���ȳ���20������ʾ
		if (us.length() > 20 || pw.length() > 20) {
			String LengthOver = "�û��������벻�ܳ���20λ";
			response.setContentType("text/html;charset=utf-8");
			request.setAttribute("tishi", "<script>alert('" + LengthOver + "')</script>");
			request.getRequestDispatcher("Sign.jsp").forward(request, response);
		}

		Cookie[] pastCookie = request.getCookies();
		int cookieID = 0;

		for (int i = 0; i < pastCookie.length; i++) {
			//cookie�������
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
						//System.out.print("���ο��Ѿ����ڣ�ֱ�Ӹ����û���");
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
								String tishi = "ע��ɹ������ס���ĵ�¼�˺�Ϊ��" + User_Num;
								response.setContentType("text/html;charset=utf-8");
					

								request.setAttribute("tishi", "<script>alert('"+tishi+"')</script>");
								request.getRequestDispatcher("Sign.jsp").forward(request, response);
								//request.setAttribute("userID", User_Num);
								//request.getRequestDispatcher("/IndexSongInfo").forward(request, response);
								
								System.out.println("ע��ɹ�,�˻�Ϊ��" + User_Num);
								break;
							} else {
								System.out.println("�������벻��ͬ");
								String ts="�������벻��ͬ";
								request.setAttribute("tishi", "<script>alert('"+ts+"')</script>");
								request.getRequestDispatcher("Sign.jsp").forward(request, response);
							}
						} else {
							System.out.println("��������Ϣ");
						
							String ts="��������Ϣ";
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

			// û�ж�Ӧcookie��ʱ��
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
						//�������ɵļ�¼cookie���͸������
						Cookie cookie = new Cookie("EZmusicCookie", String.valueOf(cookieID));
						String User_Num = String.format("%07d", cookieID + 1);
						cookie.setMaxAge(60 * 60 * 24 * 5);
						response.addCookie(cookie);
						String sql_add = "insert into Users values(?,?,?,?,?)";//���ݱ�������޸�
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
							String tishi = "ע��ɹ������ס���ĵ�¼�˺�Ϊ��" + User_Num;
							response.setContentType("text/html;charset=utf-8");
				

							request.setAttribute("tishi", "<script>alert('"+tishi+"')</script>");
							request.getRequestDispatcher("Sign.jsp").forward(request, response);
							//request.setAttribute("userID", User_Num);
							//request.getRequestDispatcher("/IndexSongInfo").forward(request, response);
							System.out.println("ע��ɹ����˻�Ϊ:" + User_Num);

							break;
						} catch (SQLException | InstantiationException | IllegalAccessException
								| ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						System.out.println("�������벻��ͬ");
						String ts="�������벻��ͬ";
						request.setAttribute("tishi", "<script>alert('"+ts+"')</script>");
						request.getRequestDispatcher("Sign.jsp").forward(request, response);
					}

				} else {
					System.out.println("��������Ϣ");
					String ts="��������Ϣ";
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
